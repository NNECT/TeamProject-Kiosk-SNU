package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.mapper.UsageLockerMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsageLockerDAO extends SqlSessionDaoSupport implements UsageLockerMapper {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(UsageLockerDTO usageLockerDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.mapper.UsageLockerMapper.insert", usageLockerDTO);
    }

    @Override
    public int update(UsageLockerDTO usageLockerDTO) {
        return getSqlSession().update("com.KioskSNU.snu.mapper.UsageLockerMapper.update", usageLockerDTO);
    }

    @Override
    public int delete(UsageLockerDTO usageLockerDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.mapper.UsageLockerMapper.delete", usageLockerDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public UsageLockerDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.UsageLockerMapper.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageLockerDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.UsageLockerMapper.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageLockerDTO> getAllByLocker(LockerDTO lockerDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.UsageLockerMapper.getAllByLocker", lockerDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageLockerDTO> getAllByAccount(AccountDTO accountDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.UsageLockerMapper.getAllByAccount", accountDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageLockerDTO> getAllValidDate() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.UsageLockerMapper.getAllValidDate");
    }
}
