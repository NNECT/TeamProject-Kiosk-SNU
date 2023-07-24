package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.TimeTicketDTO;
import com.KioskSNU.snu.mapper.TimeTicketMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TimeTicketDAO extends SqlSessionDaoSupport implements TimeTicketMapper {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(TimeTicketDTO timeTicketDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.mapper.TimeTicketMapper.insert", timeTicketDTO);
    }

    @Override
    public int update(TimeTicketDTO timeTicketDTO) {
        return getSqlSession().update("com.KioskSNU.snu.mapper.TimeTicketMapper.update", timeTicketDTO);
    }

    @Override
    public int delete(TimeTicketDTO timeTicketDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.mapper.TimeTicketMapper.delete", timeTicketDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public TimeTicketDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.TimeTicketMapper.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TimeTicketDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.TimeTicketMapper.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<TimeTicketDTO> getAllByActive(boolean active) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.TimeTicketMapper.getAllByActive", active);
    }
}
