/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.icure.config

import com.hazelcast.core.HazelcastInstance
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.taktik.icure.asynclogic.impl.filter.Filters
import org.taktik.icure.dao.impl.idgenerators.UUIDGenerator


@Configuration
class CoreConfig {
    @Bean fun cacheManager(hazelcastInstance: HazelcastInstance) = com.hazelcast.spring.cache.HazelcastCacheManager(hazelcastInstance)
    @Bean fun uuidGenerator() = UUIDGenerator()
    @Bean fun filters() = Filters()
    @Bean fun permissionSetCache(cacheManager: CacheManager) =  cacheManager.getCache("org.taktik.cache.permissionSetCache")
}
