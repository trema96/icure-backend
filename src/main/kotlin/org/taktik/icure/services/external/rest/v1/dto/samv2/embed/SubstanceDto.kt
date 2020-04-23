package org.taktik.icure.services.external.rest.v1.dto.samv2.embed

import java.io.Serializable

data class SubstanceDto(
        val code: String? = null,
        val chemicalForm: String? = null,
        val name: SamTextDto? = null,
        val note: SamTextDto? = null,
        val standardSubstances: List<StandardSubstanceDto>? = null
) : Serializable
