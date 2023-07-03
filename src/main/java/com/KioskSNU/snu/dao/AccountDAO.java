package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.service.AccountService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AccountDAO extends DAOTemplate implements AccountService {
    @Autowired
    public AccountDAO(SqlSessionTemplate sqlSessionTemplate) {
        super(sqlSessionTemplate);
    }

    @Override
    public int insert(AccountDTO accountDTO) {
        return sqlSessionTemplate.insert("account.insert", accountDTO);
    }

    @Override
    public int update(AccountDTO accountDTO) {
        return sqlSessionTemplate.update("account.update", accountDTO);
    }

    @Override
    public int delete(AccountDTO accountDTO) {
        return sqlSessionTemplate.delete("account.delete", accountDTO);
    }

    @Override
    public AccountDTO getById(int id) {
        return sqlSessionTemplate.selectOne("account.getById", id);
    }

    @Override
    public AccountDTO getByUsername(String username) {
        return sqlSessionTemplate.selectOne("account.getByUsername", username);
    }

    @Override
    public List<AccountDTO> getAll() {
        return sqlSessionTemplate.selectList("account.getAll");
    }
}
