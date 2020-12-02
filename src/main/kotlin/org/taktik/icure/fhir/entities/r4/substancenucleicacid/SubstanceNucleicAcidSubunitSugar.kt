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
package org.taktik.icure.fhir.entities.r4.substancenucleicacid

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.github.pozo.KotlinBuilder
import org.taktik.icure.fhir.entities.r4.backboneelement.BackboneElement
import org.taktik.icure.fhir.entities.r4.extension.Extension
import org.taktik.icure.fhir.entities.r4.identifier.Identifier

/**
 * 5.3.6.8.1 Sugar ID (Mandatory)
 *
 * 5.3.6.8.1 Sugar ID (Mandatory).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@KotlinBuilder
data class SubstanceNucleicAcidSubunitSugar(
  override val extension: List<Extension> = listOf(),
  /**
   * Unique id for inter-element referencing
   */
  override val id: String? = null,
  /**
   * The Substance ID of the sugar or sugar-like component that make up the nucleotide
   */
  val identifier: Identifier? = null,
  override val modifierExtension: List<Extension> = listOf(),
  /**
   * The name of the sugar or sugar-like component that make up the nucleotide
   */
  val name: String? = null,
  /**
   * The residues that contain a given sugar will be captured. The order of given residues will be
   * captured in the 5‘-3‘direction consistent with the base sequences listed above
   */
  val residueSite: String? = null
) : BackboneElement