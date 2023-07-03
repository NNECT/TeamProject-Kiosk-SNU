package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.TimeTicketDTO;
import com.KioskSNU.snu.service.TimeTicketService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TimeTicketDAO extends SqlSessionDaoSupport implements TimeTicketService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(TimeTicketDTO timeTicketDTO) {
        return getSqlSession().insert("timeTicket.insert", timeTicketDTO);
    }

    @Override
    public int update(TimeTicketDTO timeTicketDTO) {
        return getSqlSession().update("timeTicket.update", timeTicketDTO);
    }

    @Override
    public int delete(TimeTicketDTO timeTicketDTO) {
        return getSqlSession().delete("timeTicket.delete", timeTicketDTO);
    }

    @Override
    public TimeTicketDTO getById(int id) {
        return getSqlSession().selectOne("timeTicket.getById", id);
    }

    @Override
    public List<TimeTicketDTO> getAll() {
        return getSqlSession().selectList("timeTicket.getAll");
    }

    @Override
    public List<TimeTicketDTO> getAllByActive(boolean active) {
        return getSqlSession().selectList("timeTicket.getAllByActive", active);
    }
}
