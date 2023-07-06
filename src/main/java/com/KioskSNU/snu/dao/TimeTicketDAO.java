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
        return getSqlSession().insert("com.KioskSNU.snu.service.TimeTicketService.insert", timeTicketDTO);
    }

    @Override
    public int update(TimeTicketDTO timeTicketDTO) {
        return getSqlSession().update("com.KioskSNU.snu.service.TimeTicketService.update", timeTicketDTO);
    }

    @Override
    public int delete(TimeTicketDTO timeTicketDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.service.TimeTicketService.delete", timeTicketDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public TimeTicketDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.service.TimeTicketService.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TimeTicketDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.service.TimeTicketService.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<TimeTicketDTO> getAllByActive(boolean active) {
        return getSqlSession().selectList("com.KioskSNU.snu.service.TimeTicketService.getAllByActive", active);
    }
}
