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
package org.taktik.icure.services.external.rest.fhir.dto.r4.adverseevent

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.github.pozo.KotlinBuilder
import org.taktik.icure.services.external.rest.fhir.dto.r4.backboneelement.BackboneElement
import org.taktik.icure.services.external.rest.fhir.dto.r4.codeableconcept.CodeableConcept
import org.taktik.icure.services.external.rest.fhir.dto.r4.extension.Extension
import org.taktik.icure.services.external.rest.fhir.dto.r4.reference.Reference

/**
 * Information on the possible cause of the event
 *
 * Information on the possible cause of the event.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class AdverseEventSuspectEntityCausality(
  /**
   * Assessment of if the entity caused the event
   */
  val assessment: CodeableConcept? = null,
  /**
   * AdverseEvent.suspectEntity.causalityAuthor
   */
  val author: Reference? = null,
  override val extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override val id: String? = null,
  /**
   * ProbabilityScale | Bayesian | Checklist
   */
  val method: CodeableConcept? = null,
  override val modifierExtension: List<Extension> = listOf(),
  /**
   * AdverseEvent.suspectEntity.causalityProductRelatedness
   */
  val productRelatedness: String? = null
) : BackboneElement