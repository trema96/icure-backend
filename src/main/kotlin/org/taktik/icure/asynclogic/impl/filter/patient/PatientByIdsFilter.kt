///*
// * Copyright (C) 2018 Taktik SA
// *
// * This file is part of iCureBackend.
// *
// * iCureBackend is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License version 2 as published by
// * the Free Software Foundation.
// *
// * iCureBackend is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
// */
//package org.taktik.icure.asynclogic.impl.filter.patient
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Service
//import org.taktik.icure.asynclogic.impl.filter.Filter
//import org.taktik.icure.asynclogic.impl.filter.Filters
//import org.taktik.icure.dto.filter.patient.PatientByIdsFilter
//import org.taktik.icure.entities.Patient
//import org.taktik.icure.logic.ICureSessionLogic
//import org.taktik.icure.logic.PatientLogic
//import java.util.*
//import javax.security.auth.login.LoginException
//
//@Service
//class PatientByIdsFilter : Filter<String?, Patient?, PatientByIdsFilter?> {
//    var patientLogic: PatientLogic? = null
//    var sessionLogic: ICureSessionLogic? = null
//    @Autowired
//    fun setPatientLogic(patientLogic: PatientLogic?) {
//        this.patientLogic = patientLogic
//    }
//
//    @Autowired
//    fun setSessionLogic(sessionLogic: ICureSessionLogic?) {
//        this.sessionLogic = sessionLogic
//    }
//
//    override fun resolve(filter: PatientByIdsFilter, context: Filters): Set<String> {
//        return HashSet(filter.ids)
//    }
//}