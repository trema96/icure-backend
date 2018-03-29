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

package org.taktik.icure.services.external.rest.v1.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.taktik.icure.constants.Roles;
import org.taktik.icure.constants.Users;

import java.io.Serializable;
import java.util.*;

public class UserStubDto extends StoredDto implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	protected String name;
	protected String login;
	protected Long lastLoginDate;
	protected String healthcarePartyId;
    protected String email;

	protected Map<String,Set<String>> autoDelegations; //healthcareIds

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		this.email = value;
	}

	public String getHealthcarePartyId() {
		return healthcarePartyId;
	}

	public void setHealthcarePartyId(String healthcarePartyId) {
		this.healthcarePartyId = healthcarePartyId;
	}

	public Map<String, Set<String>> getAutoDelegations() {
		return autoDelegations;
	}

	public void setAutoDelegations(Map<String, Set<String>> autoDelegations) {
		this.autoDelegations = autoDelegations;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Roles.VirtualHostDependency getVirtualHostDependency() {
		return Roles.VirtualHostDependency.NONE;
	}

	public Set<String> getVirtualHosts() {
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserStubDto user = (UserStubDto) o;

		if (id != null ? !id.equals(user.id) : user.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
