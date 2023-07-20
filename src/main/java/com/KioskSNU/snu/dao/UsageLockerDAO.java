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
        return getSqlSession().insert("com.KioskSNU.snu.service.UsageLockerService.insert", usageLockerDTO);
    }

    @Override
    public int update(UsageLockerDTO usageLockerDTO) {
        return getSqlSession().update("com.KioskSNU.snu.service.UsageLockerService.update", usageLockerDTO);
    }

    @Override
    public int delete(UsageLockerDTO usageLockerDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.service.UsageLockerService.delete", usageLockerDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public UsageLockerDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.service.UsageLockerService.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageLockerDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.service.UsageLockerService.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageLockerDTO> getAllByLocker(LockerDTO lockerDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.service.UsageLockerService.getAllByLocker", lockerDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageLockerDTO> getAllByAccount(AccountDTO accountDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.service.UsageLockerService.getAllByAccount", accountDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageLockerDTO> getAllValidDate() {
        return getSqlSession().selectList("com.KioskSNU.snu.service.UsageLockerService.getAllValidDate");
    }
}
