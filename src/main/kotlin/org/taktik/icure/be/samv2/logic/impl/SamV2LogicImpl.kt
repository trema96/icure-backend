package org.taktik.icure.be.samv2.logic.impl

import org.springframework.stereotype.Service
import org.taktik.icure.be.samv2.logic.SamV2Logic
import org.taktik.icure.dao.samv2.AmpDAO
import org.taktik.icure.dao.samv2.VmpDAO
import org.taktik.icure.dao.samv2.VmpGroupDAO
import org.taktik.icure.db.PaginatedList
import org.taktik.icure.db.PaginationOffset
import org.taktik.icure.entities.samv2.Amp
import org.taktik.icure.entities.samv2.Vmp
import org.taktik.icure.entities.samv2.VmpGroup

@Service
class SamV2LogicImpl(val ampDAO: AmpDAO, val vmpDAO: VmpDAO, val vmpGroupDAO: VmpGroupDAO) : SamV2Logic {
    override fun findAmpsByDmppCode(dmppCode: String): List<Amp> {
        return ampDAO.findAmpsByDmppCode(dmppCode)
    }

    override fun findVmpsByGroupId(vmpgId: String, paginationOffset: PaginationOffset<*>): PaginatedList<Vmp> {
        return vmpDAO.findVmpsByGroupId(vmpgId, paginationOffset)
    }

    override fun findAmpsByVmpGroupCode(vmpgCode: String, paginationOffset: PaginationOffset<*>): PaginatedList<Amp> {
        return ampDAO.findAmpsByVmpGroupCode(vmpgCode, paginationOffset)
    }

    override fun findAmpsByVmpGroupId(vmpgId: String, paginationOffset: PaginationOffset<*>): PaginatedList<Amp> {
        return ampDAO.findAmpsByVmpGroupId(vmpgId, paginationOffset)
    }

    override fun findAmpsByVmpCode(vmpCode: String, paginationOffset: PaginationOffset<*>): PaginatedList<Amp> {
        return ampDAO.findAmpsByVmpCode(vmpCode, paginationOffset)
    }

    override fun findAmpsByVmpId(vmpId: String, paginationOffset: PaginationOffset<*>): PaginatedList<Amp> {
        return ampDAO.findAmpsByVmpId(vmpId, paginationOffset)
    }

    override fun listVmpIdsByGroupCode(vmpgCode: String, paginationOffset: PaginationOffset<*>): List<String> {
        return vmpDAO.listVmpIdsByGroupCode(vmpgCode, paginationOffset)
    }

    override fun listVmpIdsByGroupId(vmpgId: String, paginationOffset: PaginationOffset<*>): List<String> {
        return vmpDAO.listVmpIdsByGroupId(vmpgId, paginationOffset)
    }

    override fun listAmpIdsByVmpGroupCode(vmpgCode: String, paginationOffset: PaginationOffset<*>): List<String> {
        return ampDAO.listAmpIdsByVmpGroupCode(vmpgCode, paginationOffset)
    }

    override fun listAmpIdsByVmpGroupId(vmpgId: String, paginationOffset: PaginationOffset<*>): List<String> {
        return ampDAO.listAmpIdsByVmpGroupId(vmpgId, paginationOffset)
    }

    override fun listAmpIdsByVmpCode(vmpCode: String, paginationOffset: PaginationOffset<*>): List<String> {
        return ampDAO.listAmpIdsByVmpCode(vmpCode, paginationOffset)
    }

    override fun listAmpIdsByVmpId(vmpId: String, paginationOffset: PaginationOffset<*>): List<String> {
        return ampDAO.listAmpIdsByVmpId(vmpId, paginationOffset)
    }

    override fun findVmpsByGroupCode(vmpgCode: String, paginationOffset: PaginationOffset<*>): PaginatedList<Vmp> {
        return vmpDAO.findVmpsByGroupCode(vmpgCode, paginationOffset)
    }

    override fun findAmpsByLabel(language: String?, label: String?, paginationOffset: PaginationOffset<*>): PaginatedList<Amp> {
        return ampDAO.findAmpsByLabel(language, label, paginationOffset)
    }

    override fun listAmpIdsByLabel(language: String?, label: String?): List<String> {
        return ampDAO.listAmpIdsByLabel(language, label)
    }

    override fun findVmpsByLabel(language: String?, label: String?, paginationOffset: PaginationOffset<*>): PaginatedList<Vmp> {
        return vmpDAO.findVmpsByLabel(language, label, paginationOffset)
    }

    override fun listVmpIdsByLabel(language: String?, label: String?): List<String> {
        return vmpDAO.listVmpIdsByLabel(language, label)
    }

    override fun findVmpGroupsByLabel(language: String?, label: String?, paginationOffset: PaginationOffset<*>): PaginatedList<VmpGroup> {
        return vmpGroupDAO.findVmpGroupsByLabel(language, label, paginationOffset)
    }

    override fun listVmpGroupIdsByLabel(language: String?, label: String?): List<String> {
        return vmpGroupDAO.listVmpGroupIdsByLabel(language, label)
    }
}