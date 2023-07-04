package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.CommutationTicketDTO;
import com.KioskSNU.snu.service.CommutationTicketService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CommutationTicketDAO extends SqlSessionDaoSupport implements CommutationTicketService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(CommutationTicketDTO commutationTicketDTO) {
        return getSqlSession().insert("commutationTicket.insert", commutationTicketDTO);
    }

    @Override
    public int update(CommutationTicketDTO commutationTicketDTO) {
        return getSqlSession().update("commutationTicket.update", commutationTicketDTO);
    }

    @Override
    public int delete(CommutationTicketDTO commutationTicketDTO) {
        return getSqlSession().delete("commutationTicket.delete", commutationTicketDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public CommutationTicketDTO getById(int id) {
        return getSqlSession().selectOne("commutationTicket.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommutationTicketDTO> getAll() {
        return getSqlSession().selectList("commutationTicket.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommutationTicketDTO> getAllByActive(boolean active) {
        return getSqlSession().selectList("commutationTicket.getAllByActive", active);
    }
}
