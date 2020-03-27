/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.icure.services.external.rest.v1.controllers.core

import com.google.gson.Gson
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.reactor.mono
import ma.glasnost.orika.MapperFacade
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.taktik.couchdb.ViewQueryResultEvent
import org.taktik.icure.asynclogic.CodeLogic
import org.taktik.icure.db.PaginationOffset
import org.taktik.icure.dto.filter.Filter
import org.taktik.icure.dto.filter.predicate.Predicate
import org.taktik.icure.entities.Patient
import org.taktik.icure.entities.base.Code
import org.taktik.icure.entities.base.Identifiable
import org.taktik.icure.services.external.rest.v1.dto.CodeDto
import org.taktik.icure.services.external.rest.v1.dto.filter.chain.FilterChain
import org.taktik.icure.utils.injectReactorContext
import org.taktik.icure.utils.paginatedList
import reactor.core.publisher.Flux

@ExperimentalCoroutinesApi
@FlowPreview
@RestController
@RequestMapping("/rest/v1/code")
@Tag(name = "code")
class CodeController(private val mapper: MapperFacade,
                     private val codeLogic: CodeLogic) {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val DEFAULT_LIMIT = 1000

    @Operation(summary = "Finding codes by code, type and version with pagination.", description = "Returns a list of codes matched with given input. If several types are provided, pagination is not supported")
    @GetMapping("/byLabel")
    fun findPaginatedCodesByLabel(
            @RequestParam(required = false) region: String?,
            @RequestParam(required = false) types: String?,
            @RequestParam(required = false) language: String?,
            @RequestParam(required = false) label: String?,
            @Parameter(description = "The start key for pagination: a JSON representation of an array containing all the necessary " + "components to form the Complex Key's startKey") @RequestParam(required = false) startKey: String?,
            @Parameter(description = "A code document ID") @RequestParam(required = false) startDocumentId: String?,
            @Parameter(description = "Number of rows") @RequestParam(required = false) limit: Int?) = mono {

        val realLimit = limit ?: DEFAULT_LIMIT

        val startKeyElements: List<String?>? = if (startKey == null) null else Gson().fromJson<List<String?>>(startKey, List::class.java)
        val paginationOffset = PaginationOffset(startKeyElements, startDocumentId, null, realLimit + 1)

        types?.let {
            val typesList = types.split(',')
            val wordsList = label?.split(' ') ?: listOf()
            if (typesList.size > 1 || wordsList.size > 1) {
                typesList.asFlow()
                        .map { type -> codeLogic.findCodesByLabel(region, language, type, label, paginationOffset) }
                        .flattenMerge()
                        .paginatedList<Code, CodeDto>(mapper, realLimit, object : Predicate {
                            override fun apply(input: Identifiable<String>?): Boolean {
                                return typesList.contains(input.toString())
                            }
                        })
            } else {
                codeLogic.findCodesByLabel(region, language, typesList[0], label, paginationOffset)
                        .paginatedList<Code, CodeDto>(mapper, realLimit)
            }
        } ?: codeLogic.findCodesByLabel(region, language, label, paginationOffset)
                .paginatedList<Code, CodeDto>(mapper, realLimit)

    }

    @Operation(summary = "Finding codes by code, type and version with pagination.", description = "Returns a list of codes matched with given input.")
    @GetMapping
    fun findPaginatedCodes(
            @RequestParam(required = false) region: String?,
            @RequestParam(required = false) type: String?,
            @RequestParam(required = false) code: String?,
            @RequestParam(required = false) version: String?,
            @Parameter(description = "A code document ID") @RequestParam(required = false) startDocumentId: String?,
            @Parameter(description = "Number of rows") @RequestParam(required = false) limit: Int?) = mono {

        val realLimit = limit ?: DEFAULT_LIMIT
        val paginationOffset = PaginationOffset(
                getStartKey(region, type, code, version),
                startDocumentId, null,
                realLimit + 1
        )

        codeLogic.findCodesBy(region, type, code, version, paginationOffset)
                .paginatedList<Code, CodeDto>(mapper, realLimit)


    }
    private fun getStartKey(startKeyRegion: String?, startKeyType: String?, startKeyCode: String?, startKeyVersion: String?): List<String?>? {
        return if (startKeyRegion != null && startKeyType != null && startKeyCode != null && startKeyVersion != null) {
            listOf(startKeyRegion, startKeyType, startKeyCode, startKeyVersion)
        } else {
            null
        }
    }
    @Operation(summary = "Finding codes by code, type and version with pagination.", description = "Returns a list of codes matched with given input.")
    @GetMapping("link/{linkType}")
    fun findPaginatedCodesWithLink(
            @PathVariable linkType: String,
            @RequestParam(required = false) linkedId: String,
            @Parameter(description = "The start key for pagination: a JSON representation of an array containing all the necessary " + "components to form the Complex Key's startKey")
            @RequestParam(required = false) startKey: String?,
            @Parameter(description = "A code document ID") @RequestParam(required = false) startDocumentId: String?,
            @Parameter(description = "Number of rows") @RequestParam(required = false) limit: Int?) = mono {

        val realLimit = limit ?: DEFAULT_LIMIT
        val startKeyElements : List<String>? = if (startKey == null) null else Gson().fromJson<List<String>>(startKey, List::class.java)
        val paginationOffset = PaginationOffset(startKeyElements, startDocumentId, null, realLimit + 1)
        codeLogic.findCodesByQualifiedLinkId(null, linkType, linkedId, paginationOffset)
                .paginatedList<Code, CodeDto>(mapper, realLimit)
    }



    @Operation(summary = "Finding codes by code, type and version", description = "Returns a list of codes matched with given input.")
    @GetMapping("/byRegionTypeCode")
    fun findCodes(
            @Parameter(description = "Code region") @RequestParam(required = false) region: String?,
            @Parameter(description = "Code type") @RequestParam(required = false) type: String?,
            @Parameter(description = "Code code") @RequestParam(required = false) code: String?,
            @Parameter(description = "Code version") @RequestParam(required = false) version: String?): Flux<CodeDto> {

        return codeLogic.findCodesBy(region, type, code, version)
                .map { c -> mapper.map(c, CodeDto::class.java) }
                .injectReactorContext()
    }

    @Operation(summary = "Finding code types.", description = "Returns a list of code types matched with given input.")
    @GetMapping("/codetype/byRegionType")
    fun findCodeTypes(
            @Parameter(description = "Code region") @RequestParam(required = false) region: String?,
            @Parameter(description = "Code type") @RequestParam(required = false) type: String?): Flux<String> {
        return codeLogic.findCodeTypes(region, type)
                .injectReactorContext()
    }

    @Operation(summary = "Finding tag types.", description = "Returns a list of tag types matched with given input.")
    @GetMapping("/tagtype/byRegionType")
    fun findTagTypes(
            @Parameter(description = "Code region") @RequestParam(required = false) region: String?,
            @Parameter(description = "Code type") @RequestParam(required = false) type: String?): Flux<String> {
        val tagTypeCandidates = codeLogic.getTagTypeCandidates()
        return codeLogic.findCodeTypes(region, type)
                .filter { tagTypeCandidates.contains(it) }
                .injectReactorContext()
    }

    @Operation(summary = "Create a Code", description = "Type, Code and Version are required.")
    @PostMapping
    fun createCode(@RequestBody c: CodeDto) = mono {
        val code = codeLogic.create(mapper.map(c, Code::class.java))
        mapper.map(code, CodeDto::class.java)
    }

    @Operation(summary = "Get a list of codes by ids", description = "Keys must be delimited by coma")
    @GetMapping("/byIds/{codeIds}")
    fun getCodes(@PathVariable codeIds: String): Flux<CodeDto> {
        val codes = codeLogic.get(codeIds.split(','))
        return codes
                .map { f -> mapper.map(f, CodeDto::class.java) }
                .injectReactorContext()
    }

    @Operation(summary = "Get a code", description = "Get a code based on ID or (code,type,version) as query strings. (code,type,version) is unique.")
    @GetMapping("/{codeId}")
    fun getCode(@Parameter(description = "Code id") @PathVariable codeId: String) = mono {
        val c = codeLogic.get(codeId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "A problem regarding fetching the code. Read the app logs.")
        mapper.map(c, CodeDto::class.java)
    }

    @Operation(summary = "Get a code", description = "Get a code based on ID or (code,type,version) as query strings. (code,type,version) is unique.")
    @GetMapping("/{type}/{code}/{version}")
    fun getCodeWithParts(
            @Parameter(description = "Code type") @PathVariable type: String,
            @Parameter(description = "Code code") @PathVariable code: String,
            @Parameter(description = "Code version") @PathVariable version: String) = mono {

        val c = codeLogic.get(type, code, version)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "A problem regarding fetching the code with parts. Read the app logs.")
        mapper.map(c, CodeDto::class.java)
    }

    @Operation(summary = "Modify a code", description = "Modification of (type, code, version) is not allowed.")
    @PutMapping
    fun modifyCode(@RequestBody codeDto: CodeDto) = mono {
        val modifiedCode = try {
            codeLogic.modify(mapper.map(codeDto, Code::class.java))
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "A problem regarding modification of the code. Read the app logs: " + e.message)
        }
        mapper.map(modifiedCode, CodeDto::class.java)
    }

    @Operation(summary = "Filter codes ", description = "Returns a list of codes along with next start keys and Document ID. If the nextStartKey is Null it means that this is the last page.")
    @PostMapping("/filter")
    fun filterBy(
            @Parameter(description = "The start key for pagination, depends on the filters used") @RequestParam(required = false) startKey: String?,
            @Parameter(description = "A patient document ID") @RequestParam(required = false) startDocumentId: String?,
            @Parameter(description = "Number of rows") @RequestParam(required = false) limit: Int?,
            @Parameter(description = "Skip rows") @RequestParam(required = false) skip: Int?,
            @Parameter(description = "Sort key") @RequestParam(required = false) sort: String?,
            @Parameter(description = "Descending") @RequestParam(required = false) desc: Boolean?,
            @RequestBody(required = false) filterChain: FilterChain?) = mono {

        val realLimit = limit ?: DEFAULT_LIMIT
        val startKeyList = startKey?.split(',')?.filter { it.isNotBlank() }?.map { it.trim() } ?: listOf()
        val paginationOffset = PaginationOffset(startKeyList, startDocumentId, skip, realLimit + 1)

        var codes: Flow<ViewQueryResultEvent>? = null
        val timing = System.currentTimeMillis()
        filterChain?.let {
            codes = codeLogic.listCodes(paginationOffset, org.taktik.icure.dto.filter.chain.FilterChain(filterChain.filter as Filter<String, Patient>, mapper.map(filterChain.predicate, Predicate::class.java)), sort, desc)
        }
        logger.info("Filter codes in " + (System.currentTimeMillis() - timing) + " ms.")
        codes?.let {
            it.paginatedList<Code, CodeDto>(mapper, realLimit)
        } ?:throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Listing codes failed.")
    }
}
