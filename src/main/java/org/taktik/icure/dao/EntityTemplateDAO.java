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

package org.taktik.icure.dao;

import java.util.List;

import org.ektorp.support.View;
import org.taktik.icure.entities.EntityTemplate;

public interface EntityTemplateDAO extends GenericDAO<EntityTemplate> {
    List<EntityTemplate> getByUserIdTypeDescr(String userId, String type, String searchString, Boolean includeEntities);
    List<EntityTemplate> getByTypeDescr(String type, String searchString, Boolean includeEntities);
    List<EntityTemplate> getByUserIdTypeKeyword(String userId, String type, String keyword, Boolean includeEntities);
    List<EntityTemplate> getByTypeKeyword(String type, String keyword, Boolean includeEntities);
}
