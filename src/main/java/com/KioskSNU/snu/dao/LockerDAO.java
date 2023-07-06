package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.service.LockerService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class LockerDAO extends SqlSessionDaoSupport implements LockerService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(LockerDTO lockerDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.service.LockerService.insert", lockerDTO);
    }

    @Override
    public int update(LockerDTO lockerDTO) {
        return getSqlSession().update("com.KioskSNU.snu.service.LockerService.update", lockerDTO);
    }

    @Override
    public int delete(LockerDTO lockerDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.service.LockerService.delete", lockerDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public LockerDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.service.LockerService.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LockerDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.service.LockerService.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<LockerDTO> getAllByUsable(boolean usable) {
        return getSqlSession().selectList("com.KioskSNU.snu.service.LockerService.getAllByUsable", usable);
    }
}
