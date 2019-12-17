package org.taktik.icure.asynclogic.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import org.springframework.stereotype.Service
import org.taktik.couchdb.DocIdentifier
import org.taktik.icure.asyncdao.AgendaDAO
import org.taktik.icure.asynclogic.AgendaLogic
import org.taktik.icure.asynclogic.AsyncICureSessionLogic
import org.taktik.icure.entities.Agenda
import org.taktik.icure.exceptions.DeletionException

@Service
class AgendaLogicImpl(private val agendaDAO: AgendaDAO, private val sessionLogic: AsyncICureSessionLogic) : GenericLogicImpl<Agenda, AgendaDAO>(sessionLogic), AgendaLogic {

    override suspend fun createAgenda(agenda: Agenda): Agenda? {
        val (dbInstanceUri, groupId) = sessionLogic.getInstanceAndGroupInformationFromSecurityContext()
        return agendaDAO.create(dbInstanceUri, groupId, agenda)
    }

    override fun deleteAgenda(ids: List<String>): Flow<DocIdentifier> {
        return try {
            deleteByIds(ids)
        } catch (e: Exception) {
            throw DeletionException(e.message, e)
        }
    }

    override suspend fun getAgenda(agenda: String): Agenda? {
        val (dbInstanceUri, groupId) = sessionLogic.getInstanceAndGroupInformationFromSecurityContext()
        return agendaDAO.get(dbInstanceUri, groupId, agenda)
    }

    override suspend fun modifyAgenda(agenda: Agenda): Agenda? {
        val (dbInstanceUri, groupId) = sessionLogic.getInstanceAndGroupInformationFromSecurityContext()
        return agendaDAO.save(dbInstanceUri, groupId, agenda)
    }

    override fun getAllAgendaForUser(userId: String) = flow<Agenda> {
        val (dbInstanceUri, groupId) = sessionLogic.getInstanceAndGroupInformationFromSecurityContext()
        emitAll(agendaDAO.getAllAgendaForUser(dbInstanceUri, groupId, userId))
    }

    override fun getReadableAgendaForUser(userId: String) = flow<Agenda> {
        val (dbInstanceUri, groupId) = sessionLogic.getInstanceAndGroupInformationFromSecurityContext()
        emitAll(agendaDAO.getReadableAgendaForUser(dbInstanceUri, groupId, userId))
    }

    override fun getGenericDAO(): AgendaDAO {
        return agendaDAO
    }
}