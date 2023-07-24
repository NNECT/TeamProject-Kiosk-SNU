package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.LockerTicketDTO;
import com.KioskSNU.snu.mapper.LockerTicketMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class LockerTicketDAO extends SqlSessionDaoSupport implements LockerTicketMapper {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(LockerTicketDTO lockerTicketDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.mapper.LockerTicketMapper.insert", lockerTicketDTO);
    }

    @Override
    public int update(LockerTicketDTO lockerTicketDTO) {
        return getSqlSession().update("com.KioskSNU.snu.mapper.LockerTicketMapper.update", lockerTicketDTO);
    }

    @Override
    public int delete(LockerTicketDTO lockerTicketDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.mapper.LockerTicketMapper.delete", lockerTicketDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public LockerTicketDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.LockerTicketMapper.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LockerTicketDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.LockerTicketMapper.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<LockerTicketDTO> getAllByActive(boolean active) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.LockerTicketMapper.getAllByActive", active);
    }
}
