/*
 *  iCure Data Stack. Copyright (c) 2020 Taktik SA
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful, but
 *     WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public
 *     License along with this program.  If not, see
 *     <https://www.gnu.org/licenses/>.
 */

//
//  Generated from FHIR Version 4.0.1-9346c8cc45
//
package org.taktik.icure.fhir.entities.r4.dosage

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.github.pozo.KotlinBuilder
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.ratio.Ratio
import org.taktik.icure.fhir.entities.r4.timing.Timing

/**
 * How the medication is/was taken or should be taken
 *
 * Indicates how the medication is/was taken or should be taken by the patient.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class Dosage(
  val additionalInstruction: List<CodeableConcept> = listOf(),
  /**
   * Take "as needed" (for x)
   */
  val asNeededBoolean: Boolean? = null,
  /**
   * Take "as needed" (for x)
   */
  val asNeededCodeableConcept: CodeableConcept? = null,
  val doseAndRate: List<DosageDoseAndRate> = listOf(),
  override val extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override val id: String? = null,
  /**
   * Upper limit on medication per administration
   */
  val maxDosePerAdministration: Quantity? = null,
  /**
   * Upper limit on medication per lifetime of the patient
   */
  val maxDosePerLifetime: Quantity? = null,
  /**
   * Upper limit on medication per unit of time
   */
  val maxDosePerPeriod: Ratio? = null,
  /**
   * Technique for administering medication
   */
  val method: CodeableConcept? = null,
  override val modifierExtension: List<Extension> = listOf(),
  /**
   * Patient or consumer oriented instructions
   */
  val patientInstruction: String? = null,
  /**
   * How drug should enter body
   */
  val route: CodeableConcept? = null,
  /**
   * The order of the dosage instructions
   */
  val sequence: Int? = null,
  /**
   * Body site to administer to
   */
  val site: CodeableConcept? = null,
  /**
   * Free text dosage instructions e.g. SIG
   */
  val text: String? = null,
  /**
   * When medication should be administered
   */
  val timing: Timing? = null
) : BackboneElement