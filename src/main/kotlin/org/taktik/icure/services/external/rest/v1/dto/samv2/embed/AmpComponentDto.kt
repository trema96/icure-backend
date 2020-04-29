package org.taktik.icure.services.external.rest.v1.dto.samv2.embed

data class AmpComponentDto(
        override val from: Long? = null,
        override val to: Long? = null,
        val ingredients: List<IngredientDto>? = null,
        val pharmaceuticalForms: List<PharmaceuticalFormDto>? = null,
        val routeOfAdministrations: List<RouteOfAdministrationDto>? = null,
        val dividable: String? = null,
        val scored: String? = null,
        val crushable: CrushableDto? = null,
        val containsAlcohol: ContainsAlcoholDto? = null,
        val isSugarFree: Boolean? = null,
        val modifiedReleaseType: Int? = null,
        val specificDrugDevice: Int? = null,
        val dimensions: String? = null,
        val name: SamTextDto? = null,
        val note: SamTextDto? = null) : DataPeriodDto