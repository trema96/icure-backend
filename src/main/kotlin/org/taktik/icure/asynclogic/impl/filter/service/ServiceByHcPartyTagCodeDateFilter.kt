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
package org.taktik.icure.asynclogic.impl.filter.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import org.taktik.icure.asynclogic.AsyncICureSessionLogic
import org.taktik.icure.asynclogic.impl.filter.Filter
import org.taktik.icure.asynclogic.impl.filter.Filters
import org.taktik.icure.dto.filter.service.ServiceByHcPartyTagCodeDateFilter
import org.taktik.icure.entities.embed.Service
import org.taktik.icure.asynclogic.ContactLogic
import org.taktik.icure.utils.getLoggedHealthCarePartyId
import java.util.*
import javax.security.auth.login.LoginException

class ServiceByHcPartyTagCodeDateFilter(private val contactLogic: ContactLogic,
                                        private val sessionLogic: AsyncICureSessionLogic) : Filter<String, Service, ServiceByHcPartyTagCodeDateFilter> {
    override suspend fun resolve(filter: ServiceByHcPartyTagCodeDateFilter, context: Filters): Flow<String> {
        try {
            val hcPartyId = if (filter.healthcarePartyId != null) filter.healthcarePartyId else getLoggedHealthCarePartyId(sessionLogic)
            var ids: HashSet<String>? = null
            val patientSFK = filter.patientSecretForeignKey
            val patientSFKList = if (patientSFK != null) listOf(patientSFK) else null
            if (filter.tagType != null && filter.tagCode != null) {
                ids = HashSet(contactLogic.listServiceIdsByTag(
                        hcPartyId,
                        patientSFKList, filter.tagType,
                        filter.tagCode, filter.startValueDate, filter.endValueDate
                ).toSet())
            }
            if (filter.codeType != null && filter.codeCode != null) {
                val byCode = contactLogic.listServiceIdsByCode(
                        hcPartyId,
                        patientSFKList, filter.codeType,
                        filter.codeCode, filter.startValueDate, filter.endValueDate
                ).toSet()
                if (ids == null) {
                    ids = HashSet(byCode)
                } else {
                    ids.retainAll(byCode)
                }
            }
            return (ids ?: HashSet()).asFlow()
        } catch (e: LoginException) {
            throw IllegalArgumentException(e)
        }
    }
}