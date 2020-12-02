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
package org.taktik.icure.services.external.rest.fhir.dto.r4.devicerequest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.github.pozo.KotlinBuilder
import org.taktik.icure.services.external.rest.fhir.dto.r4.DomainResource
import org.taktik.icure.services.external.rest.fhir.dto.r4.Meta
import org.taktik.icure.services.external.rest.fhir.dto.r4.Resource
import org.taktik.icure.services.external.rest.fhir.dto.r4.annotation.Annotation
import org.taktik.icure.services.external.rest.fhir.dto.r4.codeableconcept.CodeableConcept
import org.taktik.icure.services.external.rest.fhir.dto.r4.extension.Extension
import org.taktik.icure.services.external.rest.fhir.dto.r4.identifier.Identifier
import org.taktik.icure.services.external.rest.fhir.dto.r4.narrative.Narrative
import org.taktik.icure.services.external.rest.fhir.dto.r4.period.Period
import org.taktik.icure.services.external.rest.fhir.dto.r4.reference.Reference
import org.taktik.icure.services.external.rest.fhir.dto.r4.timing.Timing

/**
 * Medical device request
 *
 * Represents a request for a patient to employ a medical device. The device may be an implantable
 * device, or an external assistive device, such as a walker.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class DeviceRequest(
  /**
   * When recorded
   */
  val authoredOn: String? = null,
  val basedOn: List<Reference> = listOf(),
  /**
   * Device requested
   */
  val codeCodeableConcept: CodeableConcept,
  /**
   * Device requested
   */
  val codeReference: Reference,
  override val contained: List<Resource> = listOf(),
  /**
   * Encounter motivating request
   */
  val encounter: Reference? = null,
  override val extension: List<Extension> = listOf(),
  /**
   * Identifier of composite request
   */
  val groupIdentifier: Identifier? = null,
  /**
   * Logical id of this artifact
   */
  override val id: String? = null,
  val identifier: List<Identifier> = listOf(),
  /**
   * A set of rules under which this content was created
   */
  override val implicitRules: String? = null,
  val instantiatesCanonical: List<String> = listOf(),
  val instantiatesUri: List<String> = listOf(),
  val insurance: List<Reference> = listOf(),
  /**
   * proposal | plan | directive | order | original-order | reflex-order | filler-order |
   * instance-order | option
   */
  val intent: String? = null,
  /**
   * Language of the resource content
   */
  override val language: String? = null,
  /**
   * Metadata about the resource
   */
  override val meta: Meta? = null,
  override val modifierExtension: List<Extension> = listOf(),
  val note: List<Annotation> = listOf(),
  /**
   * Desired time or schedule for use
   */
  val occurrenceDateTime: String? = null,
  /**
   * Desired time or schedule for use
   */
  val occurrencePeriod: Period? = null,
  /**
   * Desired time or schedule for use
   */
  val occurrenceTiming: Timing? = null,
  val parameter: List<DeviceRequestParameter> = listOf(),
  /**
   * Requested Filler
   */
  val performer: Reference? = null,
  /**
   * Filler role
   */
  val performerType: CodeableConcept? = null,
  val priorRequest: List<Reference> = listOf(),
  /**
   * routine | urgent | asap | stat
   */
  val priority: String? = null,
  val reasonCode: List<CodeableConcept> = listOf(),
  val reasonReference: List<Reference> = listOf(),
  val relevantHistory: List<Reference> = listOf(),
  /**
   * Who/what is requesting diagnostics
   */
  val requester: Reference? = null,
  /**
   * draft | active | on-hold | revoked | completed | entered-in-error | unknown
   */
  val status: String? = null,
  /**
   * Focus of request
   */
  val subject: Reference,
  val supportingInfo: List<Reference> = listOf(),
  /**
   * Text summary of the resource, for human interpretation
   */
  override val text: Narrative? = null
) : DomainResource