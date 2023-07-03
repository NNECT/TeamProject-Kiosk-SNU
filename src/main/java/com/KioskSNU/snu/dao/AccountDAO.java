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
        return getSqlSession().insert("account.insert", accountDTO);
    }

    @Override
    public int update(AccountDTO accountDTO) {
        return getSqlSession().update("account.update", accountDTO);
    }

    @Override
    public int delete(AccountDTO accountDTO) {
        return getSqlSession().delete("account.delete", accountDTO);
    }

    @Override
    public AccountDTO getById(int id) {
        return getSqlSession().selectOne("account.getById", id);
    }

    @Override
    public AccountDTO getByUsername(String username) {
        return getSqlSession().selectOne("account.getByUsername", username);
    }

    @Override
    public List<AccountDTO> getAll() {
        return getSqlSession().selectList("account.getAll");
    }
}
