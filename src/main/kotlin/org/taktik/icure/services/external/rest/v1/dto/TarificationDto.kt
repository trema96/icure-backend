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

import org.taktik.icure.services.external.rest.v1.dto.base.AppendixTypeDto
import org.taktik.icure.services.external.rest.v1.dto.base.CodeFlagDto
import org.taktik.icure.services.external.rest.v1.dto.base.CodeIdentificationDto
import org.taktik.icure.services.external.rest.v1.dto.base.LinkQualificationDto
import org.taktik.icure.services.external.rest.v1.dto.base.StoredDocumentDto
import org.taktik.icure.services.external.rest.v1.dto.embed.LetterValueDto
import org.taktik.icure.services.external.rest.v1.dto.embed.PeriodicityDto
import org.taktik.icure.services.external.rest.v1.dto.embed.ValorisationDto


data class TarificationDto(
        override val id: String,         // id = type|code|version  => this must be unique
        override val rev: String? = null,
        override val deletionDate: Long? = null,

        override val type: String? = null, //ex: ICD (type + version + code combination must be unique) (or from tags -> CD-ITEM)
        override val code: String? = null, //ex: I06.2 (or from tags -> healthcareelement). Local codes are encoded as LOCAL:SLLOCALFROMMYSOFT
        override val version: String? = null, //ex: 10. Must be lexicographically searchable

        val author: String? = null,
        val regions: Set<String> = setOf(), //ex: be,fr
        val periodicity: List<PeriodicityDto> = listOf(),
        val level: Int? = null, //ex: 0 = System, not to be modified by user, 1 = optional, created or modified by user
        val label: Map<String, String> = mapOf(), //ex: {en: Rheumatic Aortic Stenosis, fr: Sténose rhumatoïde de l'Aorte}
        val links: List<String> = listOf(), //Links towards related codes (corresponds to an approximate link in qualifiedLinks)
        val qualifiedLinks: Map<LinkQualificationDto, List<String>> = mapOf(), //Links towards related codes
        val flags: Set<CodeFlagDto> = setOf(), //flags (like female only) for the code
        val searchTerms: Map<String, Set<String>> = mapOf(), //Extra search terms/ language
        val data: String? = null,
        val appendices: Map<AppendixTypeDto, String> = mapOf(),
        val isDisabled: Boolean = false,
        val valorisations: Set<ValorisationDto> = setOf(),
        val category: Map<String, String> = mapOf(),
        val consultationCode: Boolean? = null,
        val hasRelatedCode: Boolean? = null,
        val needsPrescriber: Boolean? = null,
        val relatedCodes: Set<String> = setOf(),
        val nGroup: String? = null,
        val letterValues: List<LetterValueDto> = listOf()
) : StoredDocumentDto, CodeIdentificationDto {
    override fun withIdRev(id: String?, rev: String) = if (id != null) this.copy(id = id, rev = rev) else this.copy(rev = rev)
    override fun withDeletionDate(deletionDate: Long?) = this.copy(deletionDate = deletionDate)


    override fun normalizeIdentification(): TarificationDto {
        val parts = this.id.split("|").toTypedArray()
        return if (this.type == null || this.code == null || this.version == null) this.copy(
                type = this.type ?: parts[0],
                code = this.code ?: parts[1],
                version = this.version ?: parts[2]
        ) else this
    }
}