package org.taktik.icure.services.external.rest.v1.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ma.glasnost.orika.MapperFacade
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.taktik.couchdb.DocIdentifier
import org.taktik.icure.asynclogic.AsyncSessionLogic
import org.taktik.icure.asynclogic.FrontEndMigrationLogic
import org.taktik.icure.entities.FrontEndMigration
import org.taktik.icure.services.external.rest.v1.dto.FrontEndMigrationDto
import org.taktik.icure.utils.injectReactorContext
import reactor.core.publisher.Flux

@ExperimentalCoroutinesApi
@RestController
@RequestMapping("/rest/v1/frontendmigration")
@Api(tags = ["frontendmigration"])
class FrontEndMigrationController(private var frontEndMigrationLogic: FrontEndMigrationLogic,
                                  private var mapper: MapperFacade,
                                  private var sessionLogic: AsyncSessionLogic) {

    @ApiOperation(nickname = "getFrontEndMigrations", value = "Gets a front end migration")
    @GetMapping
    fun getFrontEndMigrations(): Flux<FrontEndMigrationDto> = flow{
        val userId = sessionLogic.getCurrentSessionContext().getUser().id
                ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Not authorized")
        emitAll(
                frontEndMigrationLogic.getFrontEndMigrationByUserIdName(userId, null)
                        .map { mapper.map(it, FrontEndMigrationDto::class.java) }
        )
    }.injectReactorContext()

    @ApiOperation(nickname = "createFrontEndMigration", value = "Creates a front end migration")
    @PostMapping
    suspend fun createFrontEndMigration(@RequestBody frontEndMigrationDto: FrontEndMigrationDto): FrontEndMigrationDto {
        val frontEndMigration = frontEndMigrationLogic.createFrontEndMigration(mapper.map(frontEndMigrationDto, FrontEndMigration::class.java))
                ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Frontend migration creation failed")

        return mapper.map(frontEndMigration, FrontEndMigrationDto::class.java)
    }

    @ApiOperation(nickname = "deleteFrontEndMigration", value = "Deletes a front end migration")
    @DeleteMapping("/{frontEndMigrationId}")
    suspend fun deleteFrontEndMigration(@PathVariable frontEndMigrationId: String): DocIdentifier {
        return frontEndMigrationLogic.deleteFrontEndMigration(frontEndMigrationId) ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Frontend migration deletion failed")
    }

    @ApiOperation(nickname = "getFrontEndMigration", value = "Gets a front end migration")
    @GetMapping("/{frontEndMigrationId}")
    suspend fun getFrontEndMigration(@PathVariable frontEndMigrationId: String): FrontEndMigrationDto {
        val migration = frontEndMigrationLogic.getFrontEndMigration(frontEndMigrationId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Frontend migration fetching failed")
        return mapper.map(migration, FrontEndMigrationDto::class.java)
    }

    @ApiOperation(nickname = "getFrontEndMigrationByName", value = "Gets an front end migration")
    @GetMapping("/byName/{frontEndMigrationName}")
    fun getFrontEndMigrationByName(@PathVariable frontEndMigrationName: String): Flux<FrontEndMigrationDto> = flow{
        val userId = sessionLogic.getCurrentSessionContext().getGroupIdUserId()

        emitAll(
                frontEndMigrationLogic.getFrontEndMigrationByUserIdName(userId, frontEndMigrationName)
                        .map { mapper.map(it, FrontEndMigrationDto::class.java) }
        )
    }.injectReactorContext()

    @ApiOperation(nickname = "modifyFrontEndMigration", value = "Modifies a front end migration")
    @PutMapping
    suspend fun modifyFrontEndMigration(@RequestBody frontEndMigrationDto: FrontEndMigrationDto): FrontEndMigrationDto {
        val migration = frontEndMigrationLogic.modifyFrontEndMigration(mapper.map(frontEndMigrationDto, FrontEndMigration::class.java))
                ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Front end migration modification failed")
        return mapper.map(migration, FrontEndMigrationDto::class.java)
    }
}
