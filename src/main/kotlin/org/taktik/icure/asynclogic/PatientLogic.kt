package org.taktik.icure.asynclogic

import kotlinx.coroutines.flow.Flow
import ma.glasnost.orika.MapperFacade
import org.ektorp.ComplexKey
import org.taktik.couchdb.DocIdentifier
import org.taktik.couchdb.ViewQueryResultEvent
import org.taktik.icure.db.PaginationOffset
import org.taktik.icure.db.Sorting
import org.taktik.icure.dto.filter.chain.FilterChain
import org.taktik.icure.entities.Patient
import org.taktik.icure.entities.embed.Delegation
import org.taktik.icure.entities.embed.Gender
import org.taktik.icure.exceptions.MissingRequirementsException
import org.taktik.icure.services.external.rest.v1.dto.PatientDto
import java.time.Instant

interface PatientLogic {
    suspend fun countByHcParty(healthcarePartyId: String): Int

    suspend fun countOfHcParty(healthcarePartyId: String): Int
    fun listByHcPartyIdsOnly(healthcarePartyId: String): Flow<String>
    fun listByHcPartyAndSsinIdsOnly(ssin: String, healthcarePartyId: String): Flow<String>
    fun listByHcPartyAndSsinsIdsOnly(ssins: Collection<String>, healthcarePartyId: String): Flow<String>
    fun listByHcPartyDateOfBirthIdsOnly(date: Int, healthcarePartyId: String): Flow<String>
    fun listByHcPartyGenderEducationProfessionIdsOnly(healthcarePartyId: String, gender: Gender?, education: String?, profession: String?): Flow<String>
    fun listByHcPartyDateOfBirthIdsOnly(startDate: Int?, endDate: Int?, healthcarePartyId: String): Flow<String>
    fun listByHcPartyNameContainsFuzzyIdsOnly(searchString: String?, healthcarePartyId: String): Flow<String>
    fun listByHcPartyName(searchString: String?, healthcarePartyId: String): Flow<String>
    fun listByHcPartyAndExternalIdsOnly(externalId: String?, healthcarePartyId: String): Flow<String>
    fun listByHcPartyAndActiveIdsOnly(active: Boolean, healthcarePartyId: String): Flow<String>
    fun listOfMergesAfter(date: Long?): Flow<Patient>
    fun findByHcPartyIdsOnly(healthcarePartyId: String, offset: PaginationOffset<List<String>>): Flow<ViewQueryResultEvent>
    fun findByHcPartyAndSsinOrDateOfBirthOrNameContainsFuzzy(healthcarePartyId: String, offset: PaginationOffset<List<String>>, searchString: String?, sorting: Sorting): Flow<ViewQueryResultEvent>
    fun listPatients(paginationOffset: PaginationOffset<*>?, filterChain: FilterChain<Patient>, sort: String?, desc: Boolean?): Flow<ViewQueryResultEvent>
    fun findByHcPartyNameContainsFuzzy(searchString: String?, healthcarePartyId: String, offset: PaginationOffset<*>, descending: Boolean): Flow<ViewQueryResultEvent>
    fun findOfHcPartyNameContainsFuzzy(searchString: String?, healthcarePartyId: String, offset: PaginationOffset<*>, descending: Boolean): Flow<ViewQueryResultEvent>
    fun findOfHcPartyAndSsinOrDateOfBirthOrNameContainsFuzzy(healthcarePartyId: String, offset: PaginationOffset<List<String>>, searchString: String?, sorting: Sorting): Flow<ViewQueryResultEvent>
    fun findByHcPartyAndSsin(ssin: String?, healthcarePartyId: String, paginationOffset: PaginationOffset<List<String>>): Flow<ViewQueryResultEvent>
    fun findByHcPartyDateOfBirth(date: Int?, healthcarePartyId: String, paginationOffset: PaginationOffset<List<String>>): Flow<ViewQueryResultEvent>
    fun findByHcPartyModificationDate(start: Long?, end: Long?, healthcarePartyId: String, descending: Boolean, paginationOffset: PaginationOffset<List<String>>): Flow<ViewQueryResultEvent>
    fun findOfHcPartyModificationDate(start: Long?, end: Long?, healthcarePartyId: String, descending: Boolean, paginationOffset: PaginationOffset<List<String>>): Flow<ViewQueryResultEvent>

    suspend fun findByUserId(id: String): Patient?

    suspend fun getPatient(patientId: String): Patient?
    fun getPatientSummary(patientDto: PatientDto?, propertyExpressions: List<String?>?): Map<String, Any>?
    fun getPatients(patientIds: List<String>): Flow<Patient>

    suspend fun addDelegation(patientId: String, delegation: Delegation): Patient?

    suspend fun addDelegations(patientId: String, delegations: Collection<Delegation>): Patient?

    @Throws(MissingRequirementsException::class)
    suspend fun createPatient(patient: Patient): Patient?

    @Throws(MissingRequirementsException::class)
    suspend fun modifyPatient(patient: Patient): Patient?

    suspend fun logAllPatients(hcPartyId: String)

    suspend fun modifyPatientReferral(patient: Patient, referralId: String?, start: Instant?, end: Instant?): Patient?

    suspend fun mergePatient(patient: Patient, fromPatients: List<Patient>): Patient?

    suspend fun getByExternalId(externalId: String): Patient?

    suspend fun solveConflicts()

    suspend fun getHcPartyKeysForDelegate(healthcarePartyId: String): Map<String, String>
    fun listOfPatientsModifiedAfter(date: Long, startKey: Long?, startDocumentId: String?, limit: Int?): Flow<ViewQueryResultEvent>
    fun getDuplicatePatientsBySsin(healthcarePartyId: String, paginationOffset: PaginationOffset<List<String>>): Flow<ViewQueryResultEvent>
    fun getDuplicatePatientsByName(healthcarePartyId: String, paginationOffset: PaginationOffset<List<String>>): Flow<ViewQueryResultEvent>
    fun fuzzySearchPatients(mapper: MapperFacade, healthcarePartyId: String, firstName: String?, lastName: String?, dateOfBirth: Int?): Flow<Patient>
    fun deletePatients(ids: Set<String>): Flow<DocIdentifier>
    fun findDeletedPatientsByDeleteDate(start: Long, end: Long?, descending: Boolean, paginationOffset: PaginationOffset<Long>): Flow<ViewQueryResultEvent>
    fun findDeletedPatientsByNames(firstName: String?, lastName: String?): Flow<Patient>
    fun undeletePatients(ids: Set<String>): Flow<DocIdentifier>
    fun updateEntities(entities: Collection<Patient>): Flow<Patient>
    suspend fun hasEntities(): Boolean
}
