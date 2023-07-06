package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.LockerTicketDTO;
import com.KioskSNU.snu.service.LockerTicketService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class LockerTicketDAO extends SqlSessionDaoSupport implements LockerTicketService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(LockerTicketDTO lockerTicketDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.service.LockerTicketService.insert", lockerTicketDTO);
    }

    @Override
    public int update(LockerTicketDTO lockerTicketDTO) {
        return getSqlSession().update("com.KioskSNU.snu.service.LockerTicketService.update", lockerTicketDTO);
    }

    @Override
    public int delete(LockerTicketDTO lockerTicketDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.service.LockerTicketService.delete", lockerTicketDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public LockerTicketDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.service.LockerTicketService.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LockerTicketDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.service.LockerTicketService.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<LockerTicketDTO> getAllByActive(boolean active) {
        return getSqlSession().selectList("com.KioskSNU.snu.service.LockerTicketService.getAllByActive", active);
    }
}
