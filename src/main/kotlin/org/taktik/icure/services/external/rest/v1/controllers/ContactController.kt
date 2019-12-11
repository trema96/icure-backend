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

import com.google.common.collect.Lists
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import kotlinx.coroutines.flow.*
import ma.glasnost.orika.MapperFacade
import ma.glasnost.orika.metadata.TypeBuilder
import org.ektorp.ComplexKey
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.taktik.couchdb.DocIdentifier
import org.taktik.icure.asynclogic.AsyncSessionLogic
import org.taktik.icure.asynclogic.ContactLogic
import org.taktik.icure.db.PaginatedList
import org.taktik.icure.db.PaginationOffset
import org.taktik.icure.dto.filter.predicate.Predicate
import org.taktik.icure.entities.Contact
import org.taktik.icure.entities.embed.Delegation
import org.taktik.icure.entities.embed.Service
import org.taktik.icure.exceptions.MissingRequirementsException
import org.taktik.icure.services.external.rest.v1.dto.ContactDto
import org.taktik.icure.services.external.rest.v1.dto.IcureStubDto
import org.taktik.icure.services.external.rest.v1.dto.ListOfIdsDto
import org.taktik.icure.services.external.rest.v1.dto.data.LabelledOccurenceDto
import org.taktik.icure.services.external.rest.v1.dto.embed.ContentDto
import org.taktik.icure.services.external.rest.v1.dto.embed.DelegationDto
import org.taktik.icure.services.external.rest.v1.dto.embed.ServiceDto
import org.taktik.icure.services.external.rest.v1.dto.filter.Filter
import org.taktik.icure.services.external.rest.v1.dto.filter.chain.FilterChain
import org.taktik.icure.utils.FuzzyValues
import org.taktik.icure.utils.paginatedList
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

@RestController
@RequestMapping("/rest/v1/contact")
@Api(tags = ["contact"])
class ContactController(private val mapper: MapperFacade,
                        private val filters: org.taktik.icure.logic.impl.filter.Filters,
                        private val contactLogic: ContactLogic,
                        private val sessionLogic: AsyncSessionLogic) {
    private val log = LoggerFactory.getLogger(javaClass)
    val DEFAULT_LIMIT = 1000

    @ApiOperation(nickname = "getEmptyContent", value = "Get an empty content")
    @GetMapping("/service/content/empty")
    fun getEmptyContent() = ContentDto()

    @ApiOperation(nickname = "createContact", value = "Create a contact with the current user", notes = "Returns an instance of created contact.")
    @PostMapping
    suspend fun createContact(@RequestBody c: ContactDto): ContactDto {
        val contact = try {
            // handling services' indexes
            handleServiceIndexes(c)
            contactLogic.createContact(mapper.map(c, Contact::class.java))
                    ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Contact creation failed")
        } catch (e: MissingRequirementsException) {
            log.warn(e.message, e)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
        return mapper.map(contact, ContactDto::class.java)
    }

    protected fun handleServiceIndexes(c: ContactDto) {
        var max = 0L
        var baseIndex = 0L
        var nullFound = false
        if (c.services != null) {
            for (service in c.services) {
                var index = service.index
                if (index == null) {
                    nullFound = true
                    baseIndex = max + 1
                    max = 0L
                }
                if (nullFound) {
                    index = baseIndex + 1 + (index ?: 0L)
                    service.index = index
                }
                if (index != null && index > max) {
                    max = index
                }
            }
        } else {
            // no null pointer exception in Orika in case of no services
            c.services = Lists.newArrayList()
        }
    }

    @ApiOperation(nickname = "getContact", value = "Get a contact")
    @GetMapping("/{contactId}")
    suspend fun getContact(@PathVariable contactId: String): ContactDto {
        val contact = contactLogic.getContact(contactId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Getting Contact failed. Possible reasons: no such contact exists, or server error. Please try again or read the server log.")
        return mapper.map(contact, ContactDto::class.java)
    }

    @ApiOperation(nickname = "getContacts", value = "Get contacts")
    @PostMapping("/byIds")
    fun getContacts(@RequestBody contactIds: ListOfIdsDto): Flow<ContactDto> {
        val contacts = contactLogic.getContacts(contactIds.ids)
        return contacts.map { c -> mapper.map(c, ContactDto::class.java) }
    }

    @ApiOperation(nickname = "getServiceCodesOccurences", value = "Get the list of all used codes frequencies in services")
    @GetMapping("/service/codes/{codeType}/{minOccurences}")
    suspend fun getServiceCodesOccurences(@PathVariable codeType: String,
                                  @PathVariable minOccurences: Long): List<LabelledOccurenceDto> {
        return contactLogic.getServiceCodesOccurences(sessionLogic.getCurrentSessionContext().getUser().healthcarePartyId, codeType, minOccurences)
                .map { mapper.map(it, LabelledOccurenceDto::class.java) }
    }

    @ApiOperation(nickname = "findByHCPartyFormId", value = "List contacts found By Healthcare Party and form Id.")
    @GetMapping("/byHcPartyFormId")
    fun findByHCPartyFormId(@RequestParam hcPartyId: String, @RequestParam formId: String): Flow<ContactDto> {
        val contactList = contactLogic.findContactsByHCPartyFormId(hcPartyId, formId)
        return contactList.map { contact -> mapper.map(contact, ContactDto::class.java) }
    }

    @ApiOperation(nickname = "findByHCPartyFormIds", value = "List contacts found By Healthcare Party and form Id.")
    @PostMapping("/byHcPartyFormIds")
    fun findByHCPartyFormIds(@RequestParam hcPartyId: String, @RequestBody formIds: ListOfIdsDto): Flow<ContactDto> {
        if (formIds.ids.size == 0) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "A required query parameter was not specified for this request.")
        }
        val contactList = contactLogic.findContactsByHCPartyFormIds(hcPartyId, formIds.ids)

        return contactList.map { contact -> mapper.map(contact, ContactDto::class.java) }
    }

    @ApiOperation(nickname = "findByHCPartyPatientForeignKeys", value = "List contacts found By Healthcare Party and Patient foreign keys.")
    @PostMapping("/byHcPartyPatientForeignKeys")
    fun findByHCPartyPatientForeignKeys(@RequestParam hcPartyId: String, @RequestBody patientForeignKeys: ListOfIdsDto): Flow<ContactDto> {
        if (patientForeignKeys.ids.size == 0) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "A required query parameter was not specified for this request.")
        }
        val contactList = contactLogic.findByHCPartyPatient(hcPartyId, patientForeignKeys.ids)

        return contactList.map { contact -> mapper.map(contact, ContactDto::class.java) }
    }

    @ApiOperation(nickname = "findByHCPartyPatientSecretFKeys", value = "List contacts found By Healthcare Party and secret foreign keys.", notes = "Keys must be delimited by coma")
    @GetMapping("/byHcPartySecretForeignKeys")
    fun findByHCPartyPatientSecretFKeys(@RequestParam hcPartyId: String,
                                        @RequestParam secretFKeys: String,
                                        @RequestParam(required = false) planOfActionsIds: String?,
                                        @RequestParam(required = false) skipClosedContacts: Boolean?): Flow<ContactDto> {
        val secretPatientKeys = secretFKeys.split(',').map { it.trim() }
        val contactList = contactLogic.findByHCPartyPatient(hcPartyId, ArrayList(secretPatientKeys))

        return if (planOfActionsIds != null) {
            val poaids = planOfActionsIds.split(',')
            contactList.filter { c -> (skipClosedContacts == null || !skipClosedContacts || c.closingDate == null) && !Collections.disjoint(c.subContacts.map { it.planOfActionId }, poaids) }.map { contact -> mapper.map(contact, ContactDto::class.java) }
        } else {
            contactList.filter { c -> skipClosedContacts == null || !skipClosedContacts || c.closingDate == null }.map { contact -> mapper.map(contact, ContactDto::class.java) }
        }
    }

    @ApiOperation(nickname = "findDelegationsStubsByHCPartyPatientSecretFKeys", value = "List contacts found By Healthcare Party and secret foreign keys.", notes = "Keys must be delimited by coma")
    @GetMapping("/byHcPartySecretForeignKeys/delegations")
    fun findDelegationsStubsByHCPartyPatientSecretFKeys(@RequestParam hcPartyId: String,
                                                        @RequestParam secretFKeys: String): Flow<IcureStubDto> {
        val secretPatientKeys = secretFKeys.split(',').map { it.trim() }
        return contactLogic.findByHCPartyPatient(hcPartyId, secretPatientKeys).map { contact -> mapper.map(contact, IcureStubDto::class.java) }
    }

    @ApiOperation(nickname = "setContactsDelegations", value = "Update delegations in healthElements.", notes = "Keys must be delimited by coma")
    @PostMapping("/delegations")
    suspend fun setContactsDelegations(@RequestBody stubs: List<IcureStubDto>) {
        val contacts = contactLogic.getContacts(stubs.map { it.id })
        contacts.onEach { contact ->
            stubs.find { s -> s.id == contact.id }?.let { stub ->
                stub.delegations.forEach { (s, delegationDtos) -> contact.delegations[s] = delegationDtos.map { ddto -> mapper.map(ddto, Delegation::class.java) }.toSet() }
                stub.encryptionKeys.forEach { (s, delegationDtos) -> contact.encryptionKeys[s] = delegationDtos.map { ddto -> mapper.map(ddto, Delegation::class.java) }.toSet() }
                stub.cryptedForeignKeys.forEach { (s, delegationDtos) -> contact.cryptedForeignKeys[s] = delegationDtos.map { ddto -> mapper.map(ddto, Delegation::class.java) }.toSet() }
            }
        }
        contactLogic.updateEntities(contacts.toList())
    }


    @ApiOperation(nickname = "closeForHCPartyPatientSecretFKeys", value = "Close contacts for Healthcare Party and secret foreign keys.", notes = "Keys must be delimited by coma")
    @PutMapping("/byHcPartySecretForeignKeys/close")
    fun closeForHCPartyPatientSecretFKeys(@RequestParam hcPartyId: String,
                                          @RequestParam secretFKeys: String): Flow<ContactDto> {
        val secretPatientKeys = secretFKeys.split(',').map { it.trim() }
        val contactFlow = contactLogic.findByHCPartyPatient(hcPartyId, secretPatientKeys)

        val savedOrFailed = contactFlow.mapNotNull { c ->
            if (c.closingDate == null) {
                c.closingDate = FuzzyValues.getFuzzyDateTime(LocalDateTime.now(), ChronoUnit.SECONDS)
                contactLogic.modifyContact(c)
                c // TODO SH now: do we want to return null if save failed? if so, then remove this line
            } else {
                null
            }
        }

        return savedOrFailed.map { contact -> mapper.map(contact, ContactDto::class.java) }
    }

    @ApiOperation(nickname = "deleteContacts", value = "Delete contacts.", notes = "Response is a set containing the ID's of deleted contacts.")
    @DeleteMapping("/{contactIds}")
    fun deleteContacts(@PathVariable contactIds: String): Flow<DocIdentifier> {
        if (contactIds.isBlank()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "A required query parameter was not specified for this request.")
        }
        // TODO versioning?
        return contactLogic.deleteContacts(contactIds.split(',').toSet())
    }

    @ApiOperation(nickname = "modifyContact", value = "Modify a contact", notes = "Returns the modified contact.")
    @PutMapping
    suspend fun modifyContact(@RequestBody contactDto: ContactDto): ContactDto {
        handleServiceIndexes(contactDto)

        contactLogic.modifyContact(mapper.map(contactDto, Contact::class.java))
        val modifiedContact = contactLogic.getContact(contactDto.id)
                ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Contact modification failed.")
        return mapper.map(modifiedContact, ContactDto::class.java)
    }

    @ApiOperation(nickname = "modifyContacts", value = "Modify a batch of contacts", notes = "Returns the modified contacts.")
    @PutMapping("/batch")
    fun modifyContacts(@RequestBody contactDtos: List<ContactDto>): Flow<ContactDto> {
        return try {
            contactDtos.forEach { c -> handleServiceIndexes(c) }

            val contacts = contactLogic.updateEntities(contactDtos.map { f -> mapper.map(f, Contact::class.java) })
            contacts.map { f -> mapper.map(f, ContactDto::class.java) }
        } catch (e: Exception) {
            log.warn(e.message, e)
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }

    }

    @ApiOperation(nickname = "newDelegations", value = "Delegates a contact to a healthcare party", notes = "It delegates a contact to a healthcare party (By current healthcare party). Returns the contact with new delegations.")
    @PostMapping("/{contactId}/delegate")
    suspend fun newDelegations(@PathVariable contactId: String, @RequestBody d: DelegationDto): ContactDto {
        contactLogic.addDelegation(contactId, mapper.map(d, Delegation::class.java))
        val contactWithDelegation = contactLogic.getContact(contactId)

        // TODO: write more function to add complete access to all contacts of a patient to a HcParty, or a contact or a subset of contacts
        // TODO: kind of adding new secretForeignKeys and cryptedForeignKeys

        val succeed = contactWithDelegation != null && contactWithDelegation.delegations != null && contactWithDelegation.delegations.isNotEmpty()
        return if (succeed) {
            mapper.map(contactWithDelegation, ContactDto::class.java)
        } else {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Delegation creation for Contact failed.")
        }
    }

    @ApiOperation(nickname = "filterBy", value = "List contacts for the current user (HcParty) or the given hcparty in the filter ", notes = "Returns a list of contacts along with next start keys and Document ID. If the nextStartKey is Null it means that this is the last page.")
    @PostMapping("/filter")
    suspend fun filterBy(
            @ApiParam(value = "The start key for pagination, depends on the filters used. If multiple keys are used, the keys are delimited by coma") @RequestParam(required = false) startKey: String?,
            @ApiParam(value = "A Contact document ID") @RequestParam(required = false) startDocumentId: String?,
            @ApiParam(value = "Number of rows") @RequestParam(required = false) limit: Int?,
            @RequestBody filterChain: FilterChain): org.taktik.icure.services.external.rest.v1.dto.PaginatedList<ContactDto> {

        val realLimit = limit ?: DEFAULT_LIMIT
        val startKeyList = if (startKey != null && startKey.isNotEmpty()) { // TODO SH now: this is not used anywhere !? and why is it a list?
            startKey.split(',').filterNot { it.isBlank() }.map { it.trim() }
        } else {
            null
        }

        val paginationOffset = PaginationOffset(startKeyList, startDocumentId, null, realLimit+1)

        // TODO SH now: why not filterChain directly?
        val contacts = contactLogic.filterContacts(paginationOffset, org.taktik.icure.dto.filter.chain.FilterChain(filterChain.filter as org.taktik.icure.dto.filter.Filter<String, Contact>, mapper.map(filterChain.predicate, Predicate::class.java)))

        return contacts.paginatedList<Contact, ContactDto>(mapper, realLimit)
    }

    @ApiOperation(nickname = "matchBy", value = "Get ids of contacts matching the provided filter for the current user (HcParty) ")
    @PostMapping("/match")
    fun matchBy(@RequestBody filter: Filter<*>): Set<String> { // TODO SH Flow
        return filters.resolve(filter)
    }

    // TODO SH do this one
//    @ApiOperation(nickname = "filterServicesBy", value = "List services for the current user (HcParty) or the given hcparty in the filter ", notes = "Returns a list of contacts along with next start keys and Document ID. If the nextStartKey is Null it means that this is the last page.")
//    @PostMapping("/service/filter")
//    suspend fun filterServicesBy(
//            @ApiParam(value = "The start key for pagination, depends on the filters used. If multiple keys are used, the keys are delimited by coma") @RequestParam(required = false) startKey: String?,
//            @ApiParam(value = "A Contact document ID") @RequestParam(required = false) startDocumentId: String?,
//            @ApiParam(value = "Number of rows") @RequestParam(required = false) limit: Int?,
//            @RequestBody(required = false) filterChain: FilterChain?): org.taktik.icure.services.external.rest.v1.dto.PaginatedList<ServiceDto> {
//
//        var startKeyList: ArrayList<*>? = null
//        if (startKey != null && startKey.isNotEmpty()) {
//            startKeyList = ArrayList(startKey.split(',').filterNot { it.isBlank() }.map { it.trim() })
//        }
//
//        val paginationOffset = PaginationOffset(startKeyList, startDocumentId, null, limit)
//
//        val services = if (filterChain != null) {
//            contactLogic.filterServices(paginationOffset, org.taktik.icure.dto.filter.chain.FilterChain(filterChain.filter as org.taktik.icure.dto.filter.Filter<String, Service>, mapper.map(filterChain.predicate, Predicate::class.java)))
//        } else {
//            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "A required query parameter was not specified for this request.")
//        }
//
//        if (services != null) {
//            if (services.rows == null) {
//                services.rows = ArrayList()
//            }
//
//            val paginatedServiceDtoList = org.taktik.icure.services.external.rest.v1.dto.PaginatedList<ServiceDto>()
//            mapper.map(
//                    services,
//                    paginatedServiceDtoList,
//                    object : TypeBuilder<PaginatedList<Service>>() {}.build(),
//                    object : TypeBuilder<org.taktik.icure.services.external.rest.v1.dto.PaginatedList<ServiceDto>>() {}.build()
//            )
//            return paginatedServiceDtoList
//        } else {
//            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Listing and Filtering services failed.")
//        }
//    }

    @ApiOperation(nickname = "listContactsByOpeningDate", value = "List contacts bu opening date parties with(out) pagination", notes = "Returns a list of contacts.")
    @GetMapping("/byOpeningDate")
    suspend fun listContactsByOpeningDate(
            @ApiParam(value = "The contact openingDate", required = true) @RequestParam startKey: Long,
            @ApiParam(value = "The contact max openingDate", required = true) @RequestParam endKey: Long,
            @ApiParam(value = "hcpartyid", required = true) @RequestParam hcpartyid: String,
            @ApiParam(value = "A contact party document ID") @RequestParam(required = false) startDocumentId: String?,
            @ApiParam(value = "Number of rows") @RequestParam(required = false) limit: Int?): org.taktik.icure.services.external.rest.v1.dto.PaginatedList<ContactDto> {

        val realLimit = limit ?: DEFAULT_LIMIT

        val paginationOffset = PaginationOffset<ComplexKey>(null, startDocumentId, null, realLimit+1)
        // TODO SH now: I've replaced startKey by null since it is already used in listContactsByOpeningDate, it was just a duplicated!?
        val contacts = contactLogic.listContactsByOpeningDate(hcpartyid, startKey, endKey, paginationOffset)

        return contacts.paginatedList<Contact, ContactDto>(mapper, realLimit)
    }
}
