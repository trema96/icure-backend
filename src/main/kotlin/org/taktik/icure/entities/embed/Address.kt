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
package org.taktik.icure.entities.embed

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import org.taktik.icure.entities.utils.MergeUtil.mergeListsDistinct
import org.taktik.icure.utils.DynamicInitializer
import org.taktik.icure.utils.invoke
import java.io.Serializable

/**
 * Created by aduchate on 21/01/13, 14:43
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Address(
        val addressType: AddressType? = null,
        val descr: String? = null,
        val street: String? = null,
        val houseNumber: String? = null,
        val postboxNumber: String? = null,
        val postalCode: String? = null,
        val city: String? = null,
        val country: String? = null,
        val note: String? = null,
        val telecoms: List<Telecom> = listOf(),
        override val encryptedSelf: String? = null
) : Encrypted, Serializable, Comparable<Address> {
    companion object : DynamicInitializer<Address>

    fun merge(other: Address) = Address(args = this.solveConflictsWith(other))
    fun solveConflictsWith(other: Address) = super.solveConflictsWith(other) + mapOf(
            "addressType" to (this.addressType ?: other.addressType),
            "descr" to (this.descr ?: other.descr),
            "street" to (this.street ?: other.street),
            "houseNumber" to (this.houseNumber ?: other.houseNumber),
            "postboxNumber" to (this.postboxNumber ?: other.postboxNumber),
            "postalCode" to (this.postalCode ?: other.postalCode),
            "city" to (this.city ?: other.city),
            "country" to (this.country ?: other.country),
            "note" to (this.note ?: other.note),
            "telecoms" to mergeListsDistinct(this.telecoms, other.telecoms,
                    { a, b -> a.telecomType?.equals(b.telecomType) ?: false },
                    { a, b -> a.merge(b) })
    )

    override fun compareTo(other: Address): Int {
        return addressType?.compareTo(other.addressType ?: AddressType.other) ?: 0
    }

}
