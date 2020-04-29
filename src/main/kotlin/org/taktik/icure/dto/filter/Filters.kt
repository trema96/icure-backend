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
package org.taktik.icure.dto.filter

import org.taktik.icure.entities.base.Identifiable
import org.taktik.icure.services.external.rest.handlers.JsonPolymorphismRoot
import java.io.Serializable

interface Filters {
    @JsonPolymorphismRoot(Filter::class)
    interface ConstantFilter<T : Serializable, O : Identifiable<T>> : Filter<T, O> {
        fun getConstant(): Set<T>
    }

    @JsonPolymorphismRoot(Filter::class)
    interface UnionFilter<T : Serializable, O : Identifiable<T>> : Filter<T, O> {
        fun getFilters(): List<Filter<T, O>>
    }

    @JsonPolymorphismRoot(Filter::class)
    interface IntersectionFilter<T : Serializable, O : Identifiable<T>> : Filter<T, O> {
        fun getFilters(): List<Filter<T, O>>
    }

    @JsonPolymorphismRoot(Filter::class)
    interface ComplementFilter<T : Serializable, O : Identifiable<T>> : Filter<T, O> {
        fun getSuperSet(): Filter<T, O>
        fun getSubSet(): Filter<T, O>
    }
}