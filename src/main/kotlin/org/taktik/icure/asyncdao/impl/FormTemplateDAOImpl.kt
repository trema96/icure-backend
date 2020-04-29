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
import ma.glasnost.orika.MapperFacade
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.output.ByteArrayOutputStream
import org.ektorp.ComplexKey
import org.ektorp.DocumentNotFoundException
import org.ektorp.support.View
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.taktik.commons.uti.UTI
import org.taktik.couchdb.queryViewIncludeDocsNoValue
import org.taktik.icure.asyncdao.FormTemplateDAO
import org.taktik.icure.dao.impl.idgenerators.IDGenerator
import org.taktik.icure.dao.impl.idgenerators.UUIDGenerator
import org.taktik.icure.entities.DocumentTemplate
import org.taktik.icure.entities.FormTemplate
import org.taktik.icure.spring.asynccache.AsyncCacheManager
import org.taktik.icure.utils.createQuery
import java.io.IOException
import java.net.URI
import java.nio.ByteBuffer

/**
 * Created by aduchate on 02/02/13, 15:24
 */

@Repository("formTemplateDAO")
@View(name = "all", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.FormTemplate' && !doc.deleted) emit(doc._id, null )}")
internal class FormTemplateDAOImpl(private val uuidGenerator: UUIDGenerator, @Qualifier("baseCouchDbDispatcher") couchDbDispatcher: CouchDbDispatcher, idGenerator: IDGenerator, @Qualifier("asyncCacheManager") AsyncCacheManager: AsyncCacheManager, mapper: MapperFacade) : GenericDAOImpl<FormTemplate>(FormTemplate::class.java, couchDbDispatcher, idGenerator, mapper), FormTemplateDAO {

    @View(name = "by_userId_and_guid", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.FormTemplate' && !doc.deleted && doc.author) emit([doc.author,doc.guid], null )}")
    override fun findByUserGuid(dbInstanceUrl: URI, groupId: String, userId: String, guid: String?, loadLayout: Boolean): Flow<FormTemplate> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)

        val from = ComplexKey.of(userId, guid ?: "")
        val to = ComplexKey.of(userId, guid ?: "\ufff0")
        val formTemplates = client.queryViewIncludeDocsNoValue<Array<String>, FormTemplate>(createQuery<FormTemplate>("by_userId_and_guid").startKey(from).endKey(to).includeDocs(true)).map { it.doc }

        // invoke postLoad()
        return if (loadLayout) {
            formTemplates.map {
                this.postLoad(dbInstanceUrl, groupId, it)
                it
            }
        } else formTemplates
    }

    @View(name = "by_guid", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.FormTemplate' && !doc.deleted) emit(doc.guid, null )}")
    override fun findByGuid(dbInstanceUrl: URI, groupId: String, guid: String, loadLayout: Boolean): Flow<FormTemplate> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)

        val formTemplates = client.queryViewIncludeDocsNoValue<String, FormTemplate>(createQuery<FormTemplate>("by_guid").key(guid).includeDocs(true)).map { it.doc }

        return if (loadLayout) {
            formTemplates.map {
                this.postLoad(dbInstanceUrl, groupId, it)
                it
            }
        } else formTemplates
    }

    @View(name = "by_specialty_code_and_guid", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.FormTemplate' && !doc.deleted && doc.specialty) emit([doc.specialty.code,doc.guid], null )}")
    override fun findBySpecialtyGuid(dbInstanceUrl: URI, groupId: String, specialityCode: String, guid: String?, loadLayout: Boolean): Flow<FormTemplate> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)

        val formTemplates = if (guid != null) {
            val key = ComplexKey.of(specialityCode, guid)
            client.queryViewIncludeDocsNoValue<Array<String>, FormTemplate>(createQuery<FormTemplate>("by_specialty_code_and_guid").key(key).includeDocs(true)).map { it.doc }
        } else {
            val from = ComplexKey.of(specialityCode, null)
            val to = ComplexKey.of(specialityCode, ComplexKey.emptyObject())
            client.queryViewIncludeDocsNoValue<Array<String>, FormTemplate>(createQuery<FormTemplate>("by_specialty_code_and_guid").startKey(from).endKey(to).includeDocs(true)).map { it.doc }
        }

        return if (loadLayout) {
            formTemplates.map {
                this.postLoad(dbInstanceUrl, groupId, it)
                it
            }
        } else formTemplates
    }


    override suspend fun createFormTemplate(dbInstanceUrl: URI, groupId: String, entity: FormTemplate): FormTemplate {
        super.save(dbInstanceUrl, groupId, true, entity)
        return entity
    }

    override suspend fun beforeSave(dbInstanceUrl: URI, groupId: String, entity: FormTemplate) =
            super.beforeSave(dbInstanceUrl, groupId, entity).let { formTemplate ->
                if (formTemplate.layout != null) {
                    val newAttachmentId = DigestUtils.sha256Hex(formTemplate.layout)

                    if (newAttachmentId != formTemplate.layoutAttachmentId && formTemplate.rev != null && formTemplate.layoutAttachmentId != null) {
                        formTemplate.attachments?.containsKey(formTemplate.layoutAttachmentId)?.takeIf { it }?.let {
                            formTemplate.copy(
                                    rev = deleteAttachment(dbInstanceUrl, groupId, formTemplate.id, formTemplate.rev!!, formTemplate.layoutAttachmentId!!),
                                    attachments = formTemplate.attachments - formTemplate.layoutAttachmentId,
                                    layoutAttachmentId = newAttachmentId,
                                    isAttachmentDirty = true
                            )
                        } ?: formTemplate.copy(
                                layoutAttachmentId = newAttachmentId,
                                isAttachmentDirty = true
                        )
                    } else
                        formTemplate
                } else {
                    if (formTemplate.layoutAttachmentId != null && formTemplate.rev != null) {
                        formTemplate.copy(
                                rev = deleteAttachment(dbInstanceUrl, groupId, formTemplate.id, formTemplate.rev, formTemplate.layoutAttachmentId),
                                layoutAttachmentId = null,
                                isAttachmentDirty = false
                        )
                    } else formTemplate
                }
            }

    override suspend  fun afterSave(dbInstanceUrl: URI, groupId: String, entity: FormTemplate) =
            super.afterSave(dbInstanceUrl, groupId, entity).let { formTemplate ->
                if (formTemplate.isAttachmentDirty && formTemplate.layoutAttachmentId != null && formTemplate.rev != null && formTemplate.layout != null) {
                    createAttachment(dbInstanceUrl, groupId, formTemplate.id, formTemplate.layoutAttachmentId, formTemplate.rev, "application/json", flowOf(ByteBuffer.wrap(formTemplate.layout))).let {
                        formTemplate.copy(
                                rev = it,
                                isAttachmentDirty = false
                        )
                    }
                } else formTemplate
            }


    override suspend fun postLoad(dbInstanceUrl: URI, groupId: String, entity: FormTemplate) =
            super.postLoad(dbInstanceUrl, groupId, entity).let { formTemplate ->
                if (formTemplate.layoutAttachmentId != null) {
                    try {
                        val attachmentIs = getAttachment(dbInstanceUrl, groupId, formTemplate.id, formTemplate.layoutAttachmentId, formTemplate.rev)
                        formTemplate.copy(layout = ByteArrayOutputStream().use { attachment ->
                            attachmentIs.collect { attachment.write(it.array()) }
                            attachment.toByteArray()
                        })
                    } catch (e: IOException) {
                        formTemplate //Could not load
                    }
                } else formTemplate
            }

    companion object {
        val log: Logger = LoggerFactory.getLogger(FormTemplateDAOImpl::class.java)
    }
}