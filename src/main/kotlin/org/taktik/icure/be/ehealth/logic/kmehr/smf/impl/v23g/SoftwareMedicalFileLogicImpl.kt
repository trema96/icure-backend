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

package org.taktik.icure.be.ehealth.logic.kmehr.smf.impl.v23g

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service
import org.taktik.icure.be.ehealth.logic.kmehr.smf.SoftwareMedicalFileLogic
import org.taktik.icure.dto.mapping.ImportMapping
import org.taktik.icure.dto.result.CheckSMFPatientResult
import org.taktik.icure.dto.result.ImportResult
import org.taktik.icure.entities.HealthcareParty
import org.taktik.icure.entities.Patient
import org.taktik.icure.entities.User
import org.taktik.icure.services.external.api.AsyncDecrypt
import org.taktik.icure.services.external.http.websocket.AsyncProgress
import java.io.InputStream
import java.io.OutputStream
import java.nio.ByteBuffer

/**
 * @author Bernard Paulus on 24/05/17.
 */
@Service
class SoftwareMedicalFileLogicImpl(val softwareMedicalFileExport: SoftwareMedicalFileExport,
                                   val softwareMedicalFileImport: SoftwareMedicalFileImport) : SoftwareMedicalFileLogic {

    override suspend fun importSmfFile(inputData : Flow<ByteBuffer>,
                                       author: User,
                                       language: String,
                                       dest: Patient?,
                                       mappings: Map<String, List<ImportMapping>>
                              ) : List<ImportResult> {
        return softwareMedicalFileImport.importSMF(inputData, author, language, mappings, dest)
    }

    override suspend fun checkIfSMFPatientsExists(inputData : Flow<ByteBuffer>,
                                                  author: User,
                                                  language: String,
                                                  dest: Patient?,
                                                  mappings: Map<String, List<ImportMapping>>
    ) : List<CheckSMFPatientResult> {
        return softwareMedicalFileImport.checkIfSMFPatientsExists(inputData, author, language, mappings, dest)
    }

    override suspend fun createSmfExport(os: OutputStream, patient: Patient, sfks: List<String>, sender: HealthcareParty, language: String, decryptor: AsyncDecrypt?, progressor: AsyncProgress?) {
		softwareMedicalFileExport.exportSMF(os, patient, sfks, sender, language, decryptor, progressor)
	}
}
