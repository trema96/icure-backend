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

package org.taktik.icure.services.external.rest.v1.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import ma.glasnost.orika.MapperFacade
import ma.glasnost.orika.metadata.TypeBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.taktik.couchdb.DocIdentifier
import org.taktik.icure.asynclogic.AsyncSessionLogic
import org.taktik.icure.asynclogic.HealthcarePartyLogic
import org.taktik.icure.asynclogic.UserLogic
import org.taktik.icure.db.PaginatedList
import org.taktik.icure.db.PaginationOffset
import org.taktik.icure.entities.HealthcareParty
import org.taktik.icure.entities.User
import org.taktik.icure.exceptions.DeletionException
import org.taktik.icure.exceptions.DocumentNotFoundException
import org.taktik.icure.exceptions.MissingRequirementsException
import org.taktik.icure.exceptions.UserRegistrationException
import org.taktik.icure.services.external.rest.v1.dto.HealthcarePartyDto
import org.taktik.icure.services.external.rest.v1.dto.PublicKeyDto
import org.taktik.icure.services.external.rest.v1.dto.ReplicationDto
import org.taktik.icure.services.external.rest.v1.dto.SignUpDto
import org.taktik.icure.utils.injectReactorContext
import org.taktik.icure.utils.paginatedList
import reactor.core.publisher.Flux
import java.util.*

@ExperimentalCoroutinesApi
@RestController
@RequestMapping("/rest/v1/hcparty")
@Api(tags = ["hcparty"])
class HealthcarePartyController(private val mapper: MapperFacade,
                                private val userLogic: UserLogic,
                                private val healthcarePartyLogic: HealthcarePartyLogic,
                                private val sessionLogic: AsyncSessionLogic) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val DEFAULT_LIMIT = 1000
    @ApiOperation(nickname = "getCurrentHealthcareParty", value = "Get the current healthcare party if logged in.", notes = "General information about the current healthcare Party")
    @GetMapping("/current")
    suspend fun getCurrentHealthcareParty(): HealthcarePartyDto {
        val healthcareParty = healthcarePartyLogic.getHealthcareParty(sessionLogic.getCurrentHealthcarePartyId())
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "A problem regarding fetching the current healthcare party. Probable reasons: no healthcare party is logged in, or server error. Please try again or read the server log.")
        return mapper.map(healthcareParty, HealthcarePartyDto::class.java)
    }

    private fun getHealthcarePartyDtoPaginatedList(healthcareParties: PaginatedList<HealthcareParty>): org.taktik.icure.services.external.rest.v1.dto.PaginatedList<HealthcarePartyDto> {
        val healthcarePartyDtoPaginatedList = org.taktik.icure.services.external.rest.v1.dto.PaginatedList<HealthcarePartyDto>()
        mapper.map(healthcareParties,
                healthcarePartyDtoPaginatedList,
                object : TypeBuilder<PaginatedList<HealthcareParty>>() {}.build(),
                object : TypeBuilder<org.taktik.icure.services.external.rest.v1.dto.PaginatedList<HealthcarePartyDto>>() {}.build())
        return healthcarePartyDtoPaginatedList
    }

    @ApiOperation(nickname = "listHealthcareParties", value = "List healthcare parties with(out) pagination", notes = "Returns a list of healthcare parties.")
    @GetMapping
    suspend fun listHealthcareParties(
            @ApiParam(value = "A healthcare party Last name") @RequestParam(required = false) startKey: String?,
            @ApiParam(value = "A healthcare party document ID") @RequestParam(required = false) startDocumentId: String?,
            @ApiParam(value = "Number of rows") @RequestParam(required = false) limit: Int?,
            @ApiParam(value = "Descending") @RequestParam(required = false) desc: Boolean?): org.taktik.icure.services.external.rest.v1.dto.PaginatedList<HealthcarePartyDto> {

        val realLimit = limit ?: DEFAULT_LIMIT
        val paginationOffset = PaginationOffset(startKey, startDocumentId, null, realLimit+1)

        return healthcarePartyLogic.listHealthcareParties(paginationOffset, desc)
                .paginatedList<HealthcareParty, HealthcarePartyDto>(mapper, realLimit)
    }

    @ApiOperation(nickname = "findByName", value = "Find healthcare parties by name with(out) pagination", notes = "Returns a list of healthcare parties.")
    @GetMapping("/byName")
    suspend fun findByName(
            @ApiParam(value = "The Last name search value") @RequestParam(required = false) name: String?,
            @ApiParam(value = "A healthcare party Last name") @RequestParam(required = false) startKey: String?,
            @ApiParam(value = "A healthcare party document ID") @RequestParam(required = false) startDocumentId: String?,
            @ApiParam(value = "Number of rows") @RequestParam(required = false) limit: Int?,
            @ApiParam(value = "Descending") @RequestParam(required = false) desc: Boolean?): org.taktik.icure.services.external.rest.v1.dto.PaginatedList<HealthcarePartyDto> {

        val realLimit = limit ?: DEFAULT_LIMIT
        if (name == null || name.isEmpty()) {
            return listHealthcareParties(startKey, startDocumentId, limit, desc)
        }

        val paginationOffset = PaginationOffset(startKey, startDocumentId, null, realLimit+1)

        return healthcarePartyLogic.findHealthcareParties(name, paginationOffset, desc)
                .paginatedList<HealthcareParty, HealthcarePartyDto>(mapper, realLimit)
    }

    @ApiOperation(nickname = "findBySsinOrNihii", value = "Find healthcare parties by nihii or ssin with(out) pagination", notes = "Returns a list of healthcare parties.")
    @GetMapping("/byNihiiOrSsin/{searchValue}")
    suspend fun findBySsinOrNihii(
            @PathVariable searchValue: String,
            @ApiParam(value = "A healthcare party Last name") @RequestParam(required = false) startKey: String?,
            @ApiParam(value = "A healthcare party document ID") @RequestParam(required = false) startDocumentId: String?,
            @ApiParam(value = "Number of rows") @RequestParam(required = false) limit: Int?,
            @ApiParam(value = "Descending") @RequestParam(required = false) desc: Boolean): org.taktik.icure.services.external.rest.v1.dto.PaginatedList<HealthcarePartyDto> {

        val realLimit = limit ?: DEFAULT_LIMIT
        val paginationOffset = PaginationOffset(startKey, startDocumentId, null, realLimit+1)

        return healthcarePartyLogic.findHealthcarePartiesBySsinOrNihii(searchValue, paginationOffset, desc)
                .paginatedList<HealthcareParty, HealthcarePartyDto>(mapper, realLimit)
    }

    @ApiOperation(nickname = "listByName", value = "Find healthcare parties by name with(out) pagination", notes = "Returns a list of healthcare parties.")
    @GetMapping("/byNameStrict/{name}")
    fun listByName(@ApiParam(value = "The Last name search value")
                   @PathVariable name: String) =
            healthcarePartyLogic.listByName(name)
                           .map { mapper.map(it, HealthcarePartyDto::class.java) }
                           .injectReactorContext()

    @ApiOperation(nickname = "findBySpecialityAndPostCode", value = "Find healthcare parties by name with(out) pagination", notes = "Returns a list of healthcare parties.")
    @GetMapping("/bySpecialityAndPostCode/{type}/{spec}/{firstCode}/to/{lastCode}")
    suspend fun findBySpecialityAndPostCode(
            @ApiParam(value = "The type of the HCP (persphysician)") @PathVariable type: String,
            @ApiParam(value = "The speciality of the HCP") @PathVariable spec: String,
            @ApiParam(value = "The first postCode for the HCP") @PathVariable firstCode: String,
            @ApiParam(value = "The last postCode for the HCP") @PathVariable lastCode: String,
            @ApiParam(value = "Number of rows") @RequestParam(required = false) limit: Int) =
            healthcarePartyLogic.findHealthcareParties(type, spec, firstCode, lastCode).paginatedList<HealthcareParty, HealthcarePartyDto>(mapper, limit)

    @ApiOperation(nickname = "createHealthcareParty", value = "Create a healthcare party", notes = "One of Name or Last name+First name, Nihii, and Public key are required.")
    @PostMapping
    suspend fun createHealthcareParty(@RequestBody h: HealthcarePartyDto): HealthcarePartyDto {
        val hcParty = try {
            healthcarePartyLogic.createHealthcareParty(mapper.map(h, HealthcareParty::class.java))
        } catch (e: MissingRequirementsException) {
            logger.warn(e.message, e)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }

        val succeed = hcParty != null
        return if (succeed) {
            mapper.map(hcParty, HealthcarePartyDto::class.java)
        } else {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Healthcare party creation failed.")
        }
    }

    @ApiOperation(nickname = "createHealthcarePartySignUp", value = "Create a healthcare party sign up procedure", notes = "Email, Last name, First name and Nihii are required")
    @PostMapping("/signup")
    suspend fun createHealthcarePartySignUp(@RequestBody h: SignUpDto): HealthcarePartyDto {
        val hcParty = try {
            healthcarePartyLogic.createHealthcareParty(mapper.map(h.healthcarePartyDto, HealthcareParty::class.java))
        } catch (e: MissingRequirementsException) {
            logger.warn(e.message, e)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        } ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Healthcare party signup failed.")

        val user = try {
            val userDto = h.userDto
            userLogic.registerUser(mapper.map(userDto, User::class.java), userDto.password)
        } catch (e: UserRegistrationException) {
            logger.warn(e.message, e)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        } ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Healthcare party signup failed.")

        return mapper.map(hcParty, HealthcarePartyDto::class.java)
    }

    @ApiOperation(nickname = "getHcPartyKeysForDelegate", value = "Get the HcParty encrypted AES keys indexed by owner", notes = "(key, value) of the map is as follows: (ID of the owner of the encrypted AES key, encrypted AES key)")
    @GetMapping("/{healthcarePartyId}/keys")
    suspend fun getHcPartyKeysForDelegate(@PathVariable healthcarePartyId: String): Map<String, String> {
        return healthcarePartyLogic.getHcPartyKeysForDelegate(healthcarePartyId)
    }

    @ApiOperation(nickname = "getHealthcareParty", value = "Get a healthcareParty by his ID", notes = "General information about the healthcare Party")
    @GetMapping("/{healthcarePartyId}")
    suspend fun getHealthcareParty(@PathVariable healthcarePartyId: String): HealthcarePartyDto? {
        val healthcareParty = healthcarePartyLogic.getHealthcareParty(healthcarePartyId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "A problem regarding fetching the healthcare party. Probable reasons: no such party exists, or server error. Please try again or read the server log.")
        return mapper.map(healthcareParty, HealthcarePartyDto::class.java)
    }

    @ApiOperation(nickname = "getHealthcareParties", value = "Get healthcareParties by their IDs", notes = "General information about the healthcare Party")
    @GetMapping("/byIds/{healthcarePartyIds}")
    fun getHealthcareParties(@PathVariable healthcarePartyIds: String) =
            healthcarePartyLogic.getHealthcareParties(healthcarePartyIds.split(','))
                    .map { mapper.map(it, HealthcarePartyDto::class.java) }
                    .injectReactorContext()

    @ApiOperation(nickname = "getHealthcarePartiesByParentId", value = "Find children of an healthcare parties", notes = "Return a list of children hcp.")
    @GetMapping("/{parentId}/children")
    fun getHealthcarePartiesByParentId(@PathVariable parentId: String) =
            healthcarePartyLogic.getHealthcarePartiesByParentId(parentId)
                    .map { mapper.map(it, HealthcarePartyDto::class.java) }
                    .injectReactorContext()


    @ApiOperation(nickname = "getPublicKey", value = "Get public key of a healthcare party", notes = "Returns the public key of a healthcare party in Hex")
    @GetMapping("/{healthcarePartyId}/publicKey")
    suspend fun getPublicKey(@PathVariable healthcarePartyId: String): PublicKeyDto {
        val publicKey = try {
            healthcarePartyLogic.getPublicKey(healthcarePartyId)
        } catch (e: DocumentNotFoundException) {
            logger.warn(e.message, e)
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "No public key is found.")

        return PublicKeyDto(healthcarePartyId, publicKey)
    }

    @ApiOperation(nickname = "deleteHealthcareParties", value = "Delete a healthcare party", notes = "Deleting a healthcareParty. Response is an array containing the id of deleted healthcare party.")
    @DeleteMapping("/{healthcarePartyIds}")
    fun deleteHealthcareParties(@PathVariable healthcarePartyIds: String): Flux<DocIdentifier> {
        return try {
            healthcarePartyLogic.deleteHealthcareParties(healthcarePartyIds.split(',')).injectReactorContext()
        } catch (e: DeletionException) {
            logger.warn(e.message, e)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }

    @ApiOperation(nickname = "modifyHealthcareParty", value = "Modify a Healthcare Party.", notes = "No particular return value. It's just a message.")
    @PutMapping
    suspend fun modifyHealthcareParty(@RequestBody healthcarePartyDto: HealthcarePartyDto): HealthcarePartyDto {
        try {
            healthcarePartyLogic.modifyHealthcareParty(mapper.map(healthcarePartyDto, HealthcareParty::class.java))
            val modifiedHealthcareParty = healthcarePartyLogic.getHealthcareParty(healthcarePartyDto.id)
                    ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Modification of the healthcare party failed. Read the server log.")
            return mapper.map(modifiedHealthcareParty, HealthcarePartyDto::class.java)
        } catch (e: MissingRequirementsException) {
            logger.warn(e.message, e)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }
}