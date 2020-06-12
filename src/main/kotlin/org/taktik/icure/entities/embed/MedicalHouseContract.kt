package org.taktik.icure.entities.embed

import com.github.pozo.KotlinBuilder

//NOTE: better classname would be MedicalHouseInscriptionPeriod
@KotlinBuilder
data class MedicalHouseContract(
        val contractId: String? = null,
        val validFrom: Long? = null,  //yyyyMMdd : start of contract period
        val validTo: Long? = null,  //yyyyMMdd : end of contract period
        val mmNihii: String? = null,
        val hcpId: String? = null,
        val changeType: ContractChangeType? = null,  //inscription, coverageChange, suspension
        val parentContractId: String? = null,
        val changedBy: String? = null,  //user, mcn

        //Coverage specific data (coverage = forfait-inscription)
        val startOfContract: Long? = null,  //yyyyMMdd : signdate
        val startOfCoverage: Long? = null,  //yyyyMMdd
        val endOfContract: Long? = null,  //yyyyMMdd : signdate
        val endOfCoverage: Long? = null,  //yyyyMMdd
        val kine: Boolean = false,
        val gp: Boolean = false,
        val nurse: Boolean = false,
        val noKine: Boolean = false,
        val noGp: Boolean = false,
        val noNurse: Boolean = false,
        val unsubscriptionReasonId: Int? = null,

        //Suspension specific data:
        val startOfSuspension: Long? = null, //yyyyMMdd
        val endOfSuspension: Long? = null, //yyyyMMdd
        val suspensionReason: SuspensionReason? = null,
        val suspensionSource: String? = null,
        val forcedSuspension: Boolean = false, //no automatic unSuspension = false
        override val encryptedSelf: String? = null
) : Encrypted
