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
package org.taktik.icure.entities

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.ektorp.Attachment
import org.taktik.icure.entities.base.CodeStub
import org.taktik.icure.entities.base.ICureDocument
import org.taktik.icure.entities.base.StoredDocument
import org.taktik.icure.entities.embed.KeywordSubword
import org.taktik.icure.entities.embed.RevisionInfo
import org.taktik.icure.utils.DynamicInitializer
import org.taktik.icure.utils.invoke
import org.taktik.icure.validation.AutoFix
import org.taktik.icure.validation.NotNull
import org.taktik.icure.validation.ValidCode

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Keyword(
        @JsonProperty("_id") override val id: String,
        @JsonProperty("_rev") override val rev: String?,
        @NotNull(autoFix = AutoFix.NOW) override val created: Long?,
        @NotNull(autoFix = AutoFix.NOW) override val modified: Long?,
        @NotNull(autoFix = AutoFix.CURRENTUSERID) override val author: String?,
        @NotNull(autoFix = AutoFix.CURRENTHCPID) override val responsible: String?,
        @ValidCode(autoFix = AutoFix.NORMALIZECODE) override val tags: Set<CodeStub>,
        @ValidCode(autoFix = AutoFix.NORMALIZECODE) override val codes: Set<CodeStub>,
        override val endOfLife: Long?,
        @JsonProperty("deleted") override val deletionDate: Long?,

        val value: String? = null,
        val subWords: Set<KeywordSubword> = setOf(),
        val userId: String? = null,

        @JsonProperty("_attachments") override val attachments: Map<String, Attachment>,
        @JsonProperty("_revs_info") override val revisionsInfo: List<RevisionInfo>,
        @JsonProperty("_conflicts") override val conflicts: List<String>,
        @JsonProperty("rev_history") override val revHistory: Map<String, String>,
        @JsonProperty("java_type") override val _type: String = Keyword::javaClass.name
) : StoredDocument, ICureDocument {
    companion object : DynamicInitializer<Keyword>
    fun merge(other: Keyword) = Keyword(args = this.solveConflictsWith(other))
    fun solveConflictsWith(other: Keyword) = super<StoredDocument>.solveConflictsWith(other) + super<ICureDocument>.solveConflictsWith(other) + mapOf(
            "value" to (this.value ?: other.value),
            "subWords" to (other.subWords + this.subWords),
            "userId" to (this.userId ?: other.userId)
    )
}
