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

package org.taktik.icure.services.external.rest.v1.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import ma.glasnost.orika.MapperFacade
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.taktik.icure.entities.Keyword
import org.taktik.icure.logic.KeywordLogic
import org.taktik.icure.services.external.rest.v1.dto.KeywordDto
import java.util.*

@RestController
@RequestMapping("/rest/v1/keyword")
@Api(tags = ["keyword"])
class KeywordController(private val mapper: MapperFacade, private val keywordLogic: KeywordLogic) {

    @ApiOperation(nickname = "createKeyword", value = "Create a keyword with the current user", notes = "Returns an instance of created keyword.")
    @PostMapping
    fun createKeyword(@RequestBody c: KeywordDto) =
            keywordLogic.createKeyword(mapper.map(c, Keyword::class.java))?.let { mapper.map(it, KeywordDto::class.java) }
                    ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Keyword creation failed.")

    @ApiOperation(nickname = "getKeyword", value = "Get a keyword", notes = "")
    @GetMapping("/{keywordId}")
    fun getKeyword(@PathVariable keywordId: String) =
            keywordLogic.getKeyword(keywordId)?.let { mapper.map(it, KeywordDto::class.java) }
                    ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Getting keyword failed. Possible reasons: no such keyword exists, or server error. Please try again or read the server log.")


    @ApiOperation(nickname = "getKeywordsByUser", value = "Get keywords by user", notes = "")
    @GetMapping("/byUser/{userId}")
    fun getKeywordsByUser(@PathVariable userId: String) =
            keywordLogic.getKeywordsByUser(userId)?.let { it.map { c -> mapper.map(c, KeywordDto::class.java) } }
                    ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Keywords fetching failed")

    @ApiOperation(nickname = "getKeywords", value = "Gets all keywords", notes = "")
    @GetMapping
    fun getKeywords(): List<KeywordDto> {
        return keywordLogic.allEntities?.let { it.map { c -> mapper.map(c, KeywordDto::class.java) } }
                ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Keywords fetching failed")
    }

    @ApiOperation(nickname = "deleteKeywords", value = "Delete keywords.", notes = "Response is a set containing the ID's of deleted keywords.")
    @DeleteMapping("/{keywordIds}")
    fun deleteKeywords(@PathVariable keywordIds: String): List<String> {
        val ids = keywordIds.split(',')
        if (ids.isEmpty()) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "A required query parameter was not specified for this request.")
        return keywordLogic.deleteKeywords(HashSet(ids))?.toList()
                ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Keyword deletion failed.")
    }

    @ApiOperation(nickname = "modifyKeyword", value = "Modify a keyword", notes = "Returns the modified keyword.")
    @PutMapping
    fun modifyKeyword(keywordDto: KeywordDto): KeywordDto {
        keywordLogic.modifyKeyword(mapper.map(keywordDto, Keyword::class.java))
        return keywordLogic.getKeyword(keywordDto.id)?.let { mapper.map(it, KeywordDto::class.java) }
                ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Keyword modification failed.")
    }
}
