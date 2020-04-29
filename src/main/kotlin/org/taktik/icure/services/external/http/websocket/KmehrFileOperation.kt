/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.taktik.icure.services.external.http.websocket

import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.apache.commons.logging.LogFactory
import org.springframework.web.reactive.socket.WebSocketSession
import org.taktik.icure.be.ehealth.logic.kmehr.v20131001.KmehrExport
import org.taktik.icure.services.external.api.AsyncDecrypt
import reactor.core.publisher.Mono
import java.io.IOException
import java.io.Serializable
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

class KmehrFileOperation(webSocket: WebSocketSession, gsonMapper: Gson) : BinaryOperation(webSocket, gsonMapper), AsyncDecrypt {
    private val log = LogFactory.getLog(KmehrExport::class.java)
    private val decodingSessions: MutableMap<String?, DecodingSession<*>> = HashMap()
    @Throws(IOException::class)
    override suspend fun <K : Serializable?> decrypt(encrypted: List<K>, clazz: Class<K>): CompletionStage<List<K>> {
        val message: Message<*> = Message("decrypt", clazz.simpleName, UUID.randomUUID().toString(), encrypted)
        val future = CompletableFuture<List<K>>()
        val decodingSession = DecodingSession(future, clazz)
        decodingSessions[message.uuid] = decodingSession
        val jsonMessage = gsonMapper.toJson(message)
        val wsMessage = if (jsonMessage.length > 65000) webSocket.binaryMessage { it.wrap(jsonMessage.toByteArray(Charsets.UTF_8)) } else webSocket.textMessage(gsonMapper.toJson(message))
        webSocket.send(Mono.just(wsMessage)).awaitFirstOrNull()
        return future
    }

    override fun <K : Serializable> handle(message: String?) {
        val dto = try {
            JsonParser.parseString(message).asJsonObject
        } catch(e:Exception) {
            log.error("Cannot parse because of ${e}. Object is: ${message}", e)
            throw(e)
        }
        if (dto["command"].asString == "decryptResponse") {
            val decodingSession = decodingSessions[dto["uuid"].asString] as DecodingSession<K>
            decodingSession.future.complete(
                    dto["body"].asJsonArray.mapNotNull { e ->
                        val jsonObject = e.asJsonObject
                        try {
                            gsonMapper.fromJson<K>(jsonObject, decodingSession.clazz)
                        } catch (ee: Exception) {
                            log.error("Cannot parse because of ${ee}")
                            null
                        }
                    })
        }
    }

    private inner class DecodingSession<K : Serializable?> internal constructor(var future: CompletableFuture<List<K>>, var clazz: Class<K>)
}