package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.service.UsageLockerService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsageLockerDAO extends SqlSessionDaoSupport implements UsageLockerService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(UsageLockerDTO usageLockerDTO) {
        return getSqlSession().insert("usageLocker.insert", usageLockerDTO);
    }

    @Override
    public int update(UsageLockerDTO usageLockerDTO) {
        return getSqlSession().update("usageLocker.update", usageLockerDTO);
    }

    @Override
    public int delete(UsageLockerDTO usageLockerDTO) {
        return getSqlSession().delete("usageLocker.delete", usageLockerDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public UsageLockerDTO getById(int id) {
        return getSqlSession().selectOne("usageLocker.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageLockerDTO> getAll() {
        return getSqlSession().selectList("usageLocker.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageLockerDTO> getAllByLocker(LockerDTO lockerDTO) {
        return getSqlSession().selectList("usageLocker.getAllByLocker", lockerDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageLockerDTO> getAllByAccount(AccountDTO accountDTO) {
        return getSqlSession().selectList("usageLocker.getAllByAccount", accountDTO);
    }
}
