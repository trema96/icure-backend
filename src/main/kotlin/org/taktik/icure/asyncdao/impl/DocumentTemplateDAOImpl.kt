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

package org.taktik.icure.asyncdao.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.output.ByteArrayOutputStream
import org.ektorp.ComplexKey
import org.ektorp.support.View
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.taktik.commons.uti.UTI
import org.taktik.couchdb.queryViewIncludeDocsNoValue
import org.taktik.icure.asyncdao.DocumentTemplateDAO
import org.taktik.icure.dao.impl.idgenerators.IDGenerator
import org.taktik.icure.entities.DocumentTemplate
import org.taktik.icure.spring.asynccache.AsyncCacheManager
import org.taktik.icure.utils.createQuery
import java.io.IOException
import java.net.URI
import java.nio.ByteBuffer

/**
 * Created by aduchate on 02/02/13, 15:24
 */

@Repository("documentTemplateDAO")
@View(name = "all", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.DocumentTemplate' && !doc.deleted) emit(doc._id, null )}")
class DocumentTemplateDAOImpl(@Qualifier("baseCouchDbDispatcher") couchDbDispatcher: CouchDbDispatcher, idGenerator: IDGenerator, @Qualifier("asyncCacheManager") asyncCacheManager: AsyncCacheManager) : CachedDAOImpl<DocumentTemplate>(DocumentTemplate::class.java, couchDbDispatcher, idGenerator, asyncCacheManager), DocumentTemplateDAO {

    @View(name = "by_userId_and_guid", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.DocumentTemplate' && !doc.deleted && doc.owner) emit([doc.owner,doc.guid], null )}")
    override fun findByUserGuid(dbInstanceUrl: URI, groupId: String, userId: String, guid: String?): Flow<DocumentTemplate> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)

        val from = ComplexKey.of(userId, "")
        val to = ComplexKey.of(userId, "\ufff0")
        val viewQuery = createQuery<DocumentTemplate>("by_userId_and_guid").startKey(from).endKey(to).includeDocs(true)
        val documentTemplates = client.queryViewIncludeDocsNoValue<Array<String>, DocumentTemplate>(viewQuery).map { it.doc }

        // invoke postLoad()
        documentTemplates.onEach {
            this.postLoad(dbInstanceUrl, groupId, it)
        }

        return documentTemplates
    }

    @View(name = "by_specialty_code_and_guid", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.DocumentTemplate' && !doc.deleted && doc.specialty) emit([doc.specialty.code,doc.guid], null )}")
    override fun findBySpecialtyGuid(dbInstanceUrl: URI, groupId: String, healthcarePartyId: String, guid: String?): Flow<DocumentTemplate> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)

        val documentTemplates = if (guid != null) {
            val key = ComplexKey.of(healthcarePartyId, guid)
            val viewQuery = createQuery<DocumentTemplate>("by_specialty_code_and_guid").key(key).includeDocs(true)
            client.queryViewIncludeDocsNoValue<Array<String>, DocumentTemplate>(viewQuery).map { it.doc }
        } else {
            val from = ComplexKey.of(healthcarePartyId, "")
            val to = ComplexKey.of(healthcarePartyId, "\ufff0")
            val viewQuery = createQuery<DocumentTemplate>("by_specialty_code_and_guid").startKey(from).endKey(to).includeDocs(true)
            client.queryViewIncludeDocsNoValue<Array<String>, DocumentTemplate>(viewQuery).map { it.doc }
        }

        // invoke postLoad()
        documentTemplates.onEach {
            this.postLoad(dbInstanceUrl, groupId, it)
        }

        return documentTemplates
    }

    @View(name = "by_document_type_code_and_user_id_and_guid", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.DocumentTemplate' && !doc.deleted && doc.documentType ) emit([doc.documentType,doc.owner,doc.guid], null )}")
    override fun findByTypeUserGuid(dbInstanceUrl: URI, groupId: String, documentTypeCode: String, userId: String?, guid: String?): Flow<DocumentTemplate> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)

        val viewQuery = if (userId != null && guid != null) {
            val key = ComplexKey.of(documentTypeCode, userId, guid)
            createQuery<DocumentTemplate>("by_document_type_code_and_user_id_and_guid").key(key).includeDocs(true)
        } else if (userId != null) {
            val from = ComplexKey.of(documentTypeCode, userId, "")
            val to = ComplexKey.of(documentTypeCode, userId, "\ufff0")
            createQuery<DocumentTemplate>("by_document_type_code_and_user_id_and_guid").startKey(from).endKey(to).includeDocs(true)
        } else {
            val from = ComplexKey.of(documentTypeCode, "", "")
            val to = ComplexKey.of(documentTypeCode, "\ufff0", "\ufff0")
            createQuery<DocumentTemplate>("by_document_type_code_and_user_id_and_guid").startKey(from).endKey(to).includeDocs(true)
        }
        val documentTemplates = client.queryViewIncludeDocsNoValue<Array<String>, DocumentTemplate>(viewQuery).map { it.doc }

        // invoke postLoad()
        documentTemplates.onEach {
            this.postLoad(dbInstanceUrl, groupId, it)
        }

        return documentTemplates
    }

    override fun evictFromCache(entity: DocumentTemplate) {
        evictFromCache(entity)
    }

    override suspend fun createDocumentTemplate(dbInstanceUrl: URI, groupId: String, entity: DocumentTemplate): DocumentTemplate {
        super.save(dbInstanceUrl, groupId, true, entity)
        return entity
    }

    override suspend fun beforeSave(dbInstanceUrl: URI, groupId: String, entity: DocumentTemplate) =
            super.beforeSave(dbInstanceUrl, groupId, entity).let { documentTemplate ->
            if (documentTemplate.attachment != null) {
                val newAttachmentId = DigestUtils.sha256Hex(documentTemplate.attachment)

                if (newAttachmentId != documentTemplate.attachmentId && documentTemplate.rev != null && documentTemplate.attachmentId != null) {
                    documentTemplate.attachments?.containsKey(documentTemplate.attachmentId)?.takeIf { it }?.let {
                        documentTemplate.copy(
                                rev = deleteAttachment(dbInstanceUrl, groupId, documentTemplate.id, documentTemplate.rev!!, documentTemplate.attachmentId!!),
                                attachments = documentTemplate.attachments - documentTemplate.attachmentId,
                                attachmentId = newAttachmentId,
                                isAttachmentDirty = true
                        )
                    } ?: documentTemplate.copy(
                            attachmentId = newAttachmentId,
                            isAttachmentDirty = true
                    )
                } else
                    documentTemplate
            } else {
                if (documentTemplate.attachmentId != null && documentTemplate.rev != null) {
                    documentTemplate.copy(
                            rev = deleteAttachment(dbInstanceUrl, groupId, documentTemplate.id, documentTemplate.rev, documentTemplate.attachmentId),
                            attachmentId = null,
                            isAttachmentDirty = false
                    )
                } else documentTemplate
            }
        }

    override suspend  fun afterSave(dbInstanceUrl: URI, groupId: String, entity: DocumentTemplate) =
            super.afterSave(dbInstanceUrl, groupId, entity).let { documentTemplate ->
                if (documentTemplate.isAttachmentDirty && documentTemplate.attachmentId != null && documentTemplate.rev != null && documentTemplate.attachment != null) {
                    val uti = UTI.get(documentTemplate.mainUti)
                    var mimeType = "application/xml"
                    if (uti != null && uti.mimeTypes != null && uti.mimeTypes.size > 0) {
                        mimeType = uti.mimeTypes[0]
                    }
                    createAttachment(dbInstanceUrl, groupId, documentTemplate.id, documentTemplate.attachmentId, documentTemplate.rev, mimeType, flowOf(ByteBuffer.wrap(documentTemplate.attachment))).let {
                        documentTemplate.copy(
                                rev = it,
                                isAttachmentDirty = false
                        )
                    }
                } else documentTemplate
            }


    override suspend fun postLoad(dbInstanceUrl: URI, groupId: String, entity: DocumentTemplate) =
            super.postLoad(dbInstanceUrl, groupId, entity).let { documentTemplate ->
                if (documentTemplate.attachmentId != null) {
                    try {
                        val attachmentIs = getAttachment(dbInstanceUrl, groupId, documentTemplate.id, documentTemplate.attachmentId, documentTemplate.rev)
                        documentTemplate.copy(attachment = ByteArrayOutputStream().use { attachment ->
                            attachmentIs.collect { attachment.write(it.array()) }
                            attachment.toByteArray()
                        })
                    } catch (e: IOException) {
                        documentTemplate //Could not load
                    }
                } else documentTemplate
            }
}
