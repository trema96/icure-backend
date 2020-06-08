package org.taktik.icure.services.external.rest.v1.mapper.samv2.embed

import org.mapstruct.Mapper
import org.taktik.icure.entities.samv2.embed.StrengthRange
import org.taktik.icure.services.external.rest.v1.dto.samv2.embed.StrengthRangeDto
@Mapper(componentModel = "spring")
interface StrengthRangeMapper {
	fun map(strengthRangeDto: StrengthRangeDto):StrengthRange
	fun map(strengthRange: StrengthRange):StrengthRangeDto
}
