package org.taktik.icure.entities.samv2.embed

import org.taktik.icure.entities.base.Code
import java.io.Serializable

data class VirtualForm(val name: SamText? = null, val standardForms: List<Code> = listOf()) : Serializable
