package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.service.AccountService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AccountDAO extends SqlSessionDaoSupport implements AccountService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(AccountDTO accountDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.service.AccountService.insert", accountDTO);
    }

    @Override
    public int update(AccountDTO accountDTO) {
        return getSqlSession().update("com.KioskSNU.snu.service.AccountService.update", accountDTO);
    }

    @Override
    public int delete(AccountDTO accountDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.service.AccountService.delete", accountDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public AccountDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.service.AccountService.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public AccountDTO getByUsername(String username) {
        return getSqlSession().selectOne("com.KioskSNU.snu.service.AccountService.getByUsername", username);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AccountDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.service.AccountService.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<AccountDTO> getAllByPhoneNumber(String phoneNumber) {
        return getSqlSession().selectList("com.KioskSNU.snu.service.AccountService.getAllByPhoneNumber", phoneNumber);
    }
}
