package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.mapper.LockerMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class LockerDAO extends SqlSessionDaoSupport implements LockerMapper {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(LockerDTO lockerDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.mapper.LockerMapper.insert", lockerDTO);
    }

    @Override
    public int update(LockerDTO lockerDTO) {
        return getSqlSession().update("com.KioskSNU.snu.mapper.LockerMapper.update", lockerDTO);
    }

    @Override
    public int delete(LockerDTO lockerDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.mapper.LockerMapper.delete", lockerDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public LockerDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.LockerMapper.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LockerDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.LockerMapper.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<LockerDTO> getAllByUsable(boolean usable) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.LockerMapper.getAllByUsable", usable);
    }
}
