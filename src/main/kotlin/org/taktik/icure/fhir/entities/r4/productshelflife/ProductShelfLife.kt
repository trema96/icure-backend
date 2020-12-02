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
package org.taktik.icure.fhir.entities.r4.productshelflife

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.github.pozo.KotlinBuilder
import org.taktik.icure.fhir.entities.r4.Quantity
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.codeableconcept.CodeableConcept
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier

/**
 * The shelf-life and storage information for a medicinal product item or container can be described
 * using this class
 *
 * The shelf-life and storage information for a medicinal product item or container can be described
 * using this class.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class ProductShelfLife(
  override val extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override val id: String? = null,
  /**
   * Unique identifier for the packaged Medicinal Product
   */
  val identifier: Identifier? = null,
  override val modifierExtension: List<Extension> = listOf(),
  /**
   * The shelf life time period can be specified using a numerical value for the period of time and
   * its unit of time measurement The unit of measurement shall be specified in accordance with ISO
   * 11240 and the resulting terminology The symbol and the symbol identifier shall be used
   */
  val period: Quantity,
  val specialPrecautionsForStorage: List<CodeableConcept> = listOf(),
  /**
   * This describes the shelf life, taking into account various scenarios such as shelf life of the
   * packaged Medicinal Product itself, shelf life after transformation where necessary and shelf life
   * after the first opening of a bottle, etc. The shelf life type shall be specified using an
   * appropriate controlled vocabulary The controlled term and the controlled term identifier shall be
   * specified
   */
  val type: CodeableConcept
) : BackboneElement