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
package org.taktik.icure.services.external.rest.v1.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.taktik.icure.services.external.rest.v1.dto.base.CodeStubDto
import org.taktik.icure.services.external.rest.v1.dto.base.EncryptableDto
import org.taktik.icure.services.external.rest.v1.dto.base.ICureDocumentDto
import org.taktik.icure.services.external.rest.v1.dto.base.StoredDocumentDto
import org.taktik.icure.services.external.rest.v1.dto.embed.DelegationDto
import org.taktik.icure.utils.InstantDeserializer
import org.taktik.icure.utils.InstantSerializer
import org.taktik.icure.validation.AutoFix
import org.taktik.icure.validation.NotNull
import java.time.Instant


data class AccessLogDto(
        override val id: String,
        override val rev: String? = null,
        @NotNull(autoFix = AutoFix.NOW) override val created: Long? = null,
        @NotNull(autoFix = AutoFix.NOW) override val modified: Long? = null,
        override val author: String? = null,
        override val responsible: String? = null,
        override val medicalLocationId: String? = null,
        override val tags: Set<CodeStubDto> = setOf(),
        override val codes: Set<CodeStubDto> = setOf(),
        override val endOfLife: Long?,
        override val deletionDate: Long? = null,
        val objectId: String? = null,
        val accessType: String? = null,
        val user: String? = null,
        val detail: String? = null,
        @JsonSerialize(using = InstantSerializer::class, include = JsonSerialize.Inclusion.NON_NULL) @JsonDeserialize(using = InstantDeserializer::class)
        val date: Instant? = null,
        @Deprecated("Use cryptedForeignKeys instead") val patientId: String? = null,
        override val secretForeignKeys: Set<String> = setOf(),
        override val cryptedForeignKeys: Map<String, Set<DelegationDto>> = mapOf(),
        override val delegations: Map<String, Set<DelegationDto>> = mapOf(),
        override val encryptionKeys: Map<String, Set<DelegationDto>> = mapOf(),
        override val encryptedSelf: String? = null,
        override val _type: String = AccessLogDto::javaClass.name
) : StoredDocumentDto, ICureDocumentDto, EncryptableDto {
    override fun withIdRev(id: String?, rev: String) = if (id != null) this.copy(id = id, rev = rev) else this.copy(rev = rev)
    override fun withDeletionDate(deletionDate: Long?) = this.copy(deletionDate = deletionDate)
}
