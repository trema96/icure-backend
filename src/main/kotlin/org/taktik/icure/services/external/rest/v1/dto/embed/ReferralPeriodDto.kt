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
package org.taktik.icure.services.external.rest.v1.dto.embed

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.taktik.icure.utils.InstantDeserializer
import org.taktik.icure.utils.InstantSerializer
import java.io.Serializable
import java.time.Instant


data class ReferralPeriodDto(
        @JsonSerialize(using = InstantSerializer::class, include = JsonSerialize.Inclusion.NON_NULL)
        @JsonDeserialize(using = InstantDeserializer::class)
        val startDate: Instant? = null,

        @JsonSerialize(using = InstantSerializer::class, include = JsonSerialize.Inclusion.NON_NULL)
        @JsonDeserialize(using = InstantDeserializer::class)
        val endDate: Instant? = null,
        val comment: String? = null
) : Serializable, Comparable<ReferralPeriodDto> {

    override fun compareTo(other: ReferralPeriodDto): Int {
        return when {
            this == other -> 0
            startDate != other.startDate -> {
                if (startDate == null) 1 else if (other.startDate == null) 0 else startDate.compareTo(other.startDate)
            }
            endDate != other.endDate -> {
                if (endDate == null) 1 else if (other.endDate == null) 0 else endDate.compareTo(other.endDate)
            }
            else -> 1
        }
    }
}
