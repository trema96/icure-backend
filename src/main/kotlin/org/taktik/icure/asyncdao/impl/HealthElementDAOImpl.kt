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

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import ma.glasnost.orika.MapperFacade
import org.ektorp.ComplexKey
import org.ektorp.support.View
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.taktik.couchdb.queryView
import org.taktik.couchdb.queryViewIncludeDocs
import org.taktik.couchdb.queryViewIncludeDocsNoValue
import org.taktik.icure.asyncdao.HealthElementDAO
import org.taktik.icure.dao.impl.idgenerators.IDGenerator
import org.taktik.icure.entities.HealthElement
import org.taktik.icure.entities.base.Code
import org.taktik.icure.utils.createQuery
import org.taktik.icure.utils.firstOrNull
import java.net.URI

/**
 * Created by aduchate on 18/07/13, 13:36
 */
@ExperimentalCoroutinesApi
@Repository("healthElementDAO")
@View(name = "all", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.HealthElement' && !doc.deleted) emit( doc.patientId, doc._id )}")
internal class HealthElementDAOImpl(@Qualifier("healthdataCouchDbDispatcher") couchDbDispatcher: CouchDbDispatcher, idGenerator: IDGenerator, mapper: MapperFacade) : GenericDAOImpl<HealthElement>(HealthElement::class.java, couchDbDispatcher, idGenerator, mapper), HealthElementDAO {

    override fun findByPatient(dbInstanceUrl: URI, groupId: String, patientId: String): Flow<HealthElement> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)

        return client.queryViewIncludeDocs<String, String, HealthElement>(createQuery<HealthElement>("all").key(patientId).includeDocs(true)).map { it.doc }
    }

    @View(name = "by_patient_and_codes", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.HealthElement' && !doc.deleted) {\n" +
            "  for (var i=0;i<doc.codes.length;i++) {\n" +
            "  emit( [doc.patientId, doc.codes[i].type+':'+doc.codes[i].code], doc._id );\n" +
            "  }}}")
    override fun findByPatientAndCodes(dbInstanceUrl: URI, groupId: String, patientId: String, codes: Set<Code>): Flow<HealthElement> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)

        val keys = codes.map { c -> ComplexKey.of(patientId, c.toString()) }
        return client.queryViewIncludeDocs<ComplexKey, String, HealthElement>(createQuery<HealthElement>("by_patient_and_codes").keys(keys).includeDocs(true)).map { it.doc }
    }

    @View(name = "by_hcparty_and_codes", map = "classpath:js/healthelement/By_hcparty_code_map.js")
    override fun findByHCPartyAndCodes(dbInstanceUrl: URI, groupId: String, healthCarePartyId: String, codeType: String, codeNumber: String): Flow<String> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)

        return client.queryView<Array<String>, String>(createQuery<HealthElement>("by_hcparty_and_codes").key(ComplexKey.of(healthCarePartyId, "$codeType:$codeNumber")).includeDocs(false)).mapNotNull { it.value }
    }

    @View(name = "by_hcparty_and_tags", map = "classpath:js/healthelement/By_hcparty_tag_map.js")
    override fun findByHCPartyAndTags(dbInstanceUrl: URI, groupId: String, healthCarePartyId: String, tagType: String, tagCode: String) = flow<String> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)
        emitAll(client.queryView<Array<String>, String>(createQuery<HealthElement>("by_hcparty_and_tags").key(ComplexKey.of(healthCarePartyId, "$tagType:$tagCode")).includeDocs(false)).mapNotNull { it.value })
   }

    @View(name = "by_hcparty_and_status", map = "classpath:js/healthelement/By_hcparty_status_map.js")
    override fun findByHCPartyAndStatus(dbInstanceUrl: URI, groupId: String, healthCarePartyId: String, status: Int?): Flow<String> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)

        return client.queryView<Array<String>, String>(createQuery<HealthElement>("by_hcparty_and_status").key(ComplexKey.of(healthCarePartyId, status)).includeDocs(false)).mapNotNull { it.value }
    }

    @View(name = "by_planOfActionId", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.HealthElement' && !doc.deleted) {\n" +
            "            for(var i= 0;i<doc.plansOfAction.length;i++) {\n" +
            "        emit([doc.plansOfAction[i].id], doc._id);\n" +
            "    }\n" +
            "}}")
    override suspend fun findHealthElementByPlanOfActionId(dbInstanceUrl: URI, groupId: String, planOfActionId: String): HealthElement? {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)

        return client.queryViewIncludeDocs<ComplexKey, String, HealthElement>(createQuery<HealthElement>("by_planOfActionId").key(planOfActionId).includeDocs(true)).map { it.doc }.firstOrNull()
    }

    override suspend fun getHealthElement(dbInstanceUrl: URI, groupId: String, healthElementId: String): HealthElement? {
        return get(dbInstanceUrl, groupId, healthElementId)
    }

    @View(name = "by_hcparty_patient", map = "classpath:js/healthelement/By_hcparty_patient_map.js")
    override fun findByHCPartySecretPatientKeys(dbInstanceUrl: URI, groupId: String, hcPartyId: String, secretPatientKeys: List<String>): Flow<HealthElement> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)

        val keys = secretPatientKeys.map { fk -> ComplexKey.of(hcPartyId, fk) }

        val result = client.queryViewIncludeDocs<Array<String>, String, HealthElement>(createQuery<HealthElement>("by_hcparty_patient").keys(keys).includeDocs(true)).map { it.doc }
        return result.distinctUntilChangedBy { it.id }
    }

    @View(name = "conflicts", map = "function(doc) { if (doc.java_type == 'org.taktik.icure.entities.HealthElement' && !doc.deleted && doc._conflicts) emit(doc._id )}")
    override fun listConflicts(dbInstanceUrl: URI, groupId: String): Flow<HealthElement> {
        val client = couchDbDispatcher.getClient(dbInstanceUrl, groupId)

        return client.queryViewIncludeDocsNoValue<ComplexKey, HealthElement>(createQuery<HealthElement>("conflicts").includeDocs(true)).map { it.doc }
    }
}