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
package org.taktik.icure.asynclogic.impl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import org.ektorp.UpdateConflictException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.taktik.couchdb.DocIdentifier
import org.taktik.icure.asyncdao.FormDAO
import org.taktik.icure.asynclogic.AsyncSessionLogic
import org.taktik.icure.asynclogic.FormLogic
import org.taktik.icure.dao.Option
import org.taktik.icure.dao.impl.idgenerators.UUIDGenerator
import org.taktik.icure.entities.Contact
import org.taktik.icure.entities.Form
import org.taktik.icure.entities.embed.Delegation
import org.taktik.icure.utils.firstOrNull
import org.taktik.icure.validation.aspect.Check

@ExperimentalCoroutinesApi
@Service
class FormLogicImpl(private val formDAO: FormDAO,
                    private val sessionLogic: AsyncSessionLogic,
                    private val uuidGenerator: UUIDGenerator) : GenericLogicImpl<Form, FormDAO>(sessionLogic), FormLogic {

    override suspend fun getForm(id: String): Form? {
        val (dbInstanceUri, groupId) = sessionLogic.getInstanceAndGroupInformationFromSecurityContext()
        return formDAO.get(dbInstanceUri, groupId, id)
    }

    override fun getForms(selectedIds: Collection<String>): Flow<Form> = flow {
        val (dbInstanceUri, groupId) = sessionLogic.getInstanceAndGroupInformationFromSecurityContext()
        emitAll(formDAO.getList(dbInstanceUri, groupId, selectedIds))
    }

    override fun findByHCPartyPatient(hcPartyId: String, secretPatientKeys: List<String>, healthElementId: String?, planOfActionId: String?, formTemplateId: String?): Flow<Form> = flow {
        val (dbInstanceUri, groupId) = sessionLogic.getInstanceAndGroupInformationFromSecurityContext()
        val forms = formDAO.findByHcPartyPatient(dbInstanceUri, groupId, hcPartyId, secretPatientKeys)
        val filteredForms = forms.filter { f ->
            (healthElementId == null || healthElementId == f.healthElementId) &&
                    (planOfActionId == null || planOfActionId == f.planOfActionId) &&
                    (formTemplateId == null || formTemplateId == f.formTemplateId)
        }
        emitAll(filteredForms)
    }

    override suspend fun addDelegation(formId: String, delegation: Delegation): Form? {
        val (dbInstanceUri, groupId) = sessionLogic.getInstanceAndGroupInformationFromSecurityContext()
        val form = getForm(formId)
        return delegation.delegatedTo?.let { healthcarePartyId ->
            form?.let { c -> formDAO.save(dbInstanceUri, groupId, c.copy(delegations = c.delegations + mapOf(
                    healthcarePartyId to setOf(delegation)
            )))}
        } ?: form
    }

    override suspend fun createForm(form: Form): Form? = fix(form) { form ->
        try { // Fetching the hcParty
            createEntities(setOf(form)).firstOrNull()
        } catch (e: Exception) {
            logger.error("createContact: " + e.message)
            throw IllegalArgumentException("Invalid contact", e)
        }
    }

    override fun deleteForms(ids: Set<String>): Flow<DocIdentifier> {
        return try {
            deleteByIds(ids)
        } catch (e: Exception) {
            logger.error(e.message, e)
            return flowOf()
        }
    }

    override suspend fun modifyForm(form: Form) = fix(form) { form ->
        val (dbInstanceUri, groupId) = sessionLogic.getInstanceAndGroupInformationFromSecurityContext()
        try {
            formDAO.save(dbInstanceUri, groupId, if (form.created == null) form.copy(created = getForm(form.id)?.created) else form)
        } catch (e: UpdateConflictException) { //resolveConflict(form, e);
            logger.warn("Documents of class {} with id {} and rev {} could not be merged", form.javaClass.simpleName, form.id, form.rev)
            throw IllegalArgumentException("Invalid form", e)
        } catch (e: Exception) {
            throw IllegalArgumentException("Invalid form", e)
        }
    }

    override fun findByHcPartyParentId(hcPartyId: String, formId: String): Flow<Form> = flow {
        val (dbInstanceUri, groupId) = sessionLogic.getInstanceAndGroupInformationFromSecurityContext()
        emitAll(formDAO.findByHcPartyParentId(dbInstanceUri, groupId, hcPartyId, formId))
    }

    override suspend fun addDelegations(formId: String, delegations: List<Delegation>): Form? {
        val (dbInstanceUri, groupId) = sessionLogic.getInstanceAndGroupInformationFromSecurityContext()
        val form = getForm(formId)
        return form?.let {
            formDAO.save(dbInstanceUri, groupId, it.copy(
                    delegations = it.delegations +
                            delegations.mapNotNull { d -> d.delegatedTo?.let { delegateTo -> delegateTo to setOf(d) } }
            ))
        }
    }

    override fun getGenericDAO(): FormDAO {
        return formDAO
    }

    override suspend fun solveConflicts() {
        val (dbInstanceUri, groupId) = sessionLogic.getInstanceAndGroupInformationFromSecurityContext()
        val formsInConflict = formDAO.listConflicts(dbInstanceUri, groupId).mapNotNull { formDAO.get(dbInstanceUri, groupId, it.id, Option.CONFLICTS) }
        formsInConflict.collect { form ->
            var modifieForm = form
            form.conflicts?.mapNotNull { c: String -> formDAO.get(dbInstanceUri, groupId, form.id, c) }?.forEach { cp ->
                modifieForm = modifieForm.merge(cp)
                formDAO.purge(dbInstanceUri, groupId, cp)
            }
            formDAO.save(dbInstanceUri, groupId, modifieForm)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FormLogicImpl::class.java)
    }
}