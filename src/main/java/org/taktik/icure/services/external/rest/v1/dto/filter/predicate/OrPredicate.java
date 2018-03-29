/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.icure.services.external.rest.v1.dto.filter.predicate;

import java.util.List;

import org.taktik.icure.services.external.rest.handlers.JsonPolymorphismRoot;

@JsonPolymorphismRoot(org.taktik.icure.services.external.rest.v1.dto.filter.predicate.Predicate.class)
public class OrPredicate extends Predicate {
	List<Predicate> predicates;

	public OrPredicate(List<Predicate> predicates) {
		this.predicates = predicates;
	}

	public OrPredicate() {}

	public List<Predicate> getPredicates() {
		return predicates;
	}

	public void setPredicates(List<Predicate> predicates) {
		this.predicates = predicates;
	}
}
