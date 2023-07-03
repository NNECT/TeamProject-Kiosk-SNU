package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.service.AccountService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AccountDAO extends DAOTemplate implements AccountService {
    @Autowired
    public AccountDAO(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    @Override
    public int insert(AccountDTO accountDTO) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.insert("AccountDAO.insert", accountDTO);
        }
    }

    @Override
    public int update(AccountDTO accountDTO) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.update("AccountDAO.update", accountDTO);
        }
    }

    @Override
    public int delete(AccountDTO accountDTO) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.delete("AccountDAO.delete", accountDTO);
        }
    }

    @Override
    public AccountDTO getById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectOne("AccountDAO.getById", id);
        }
    }

    @Override
    public AccountDTO getByUsername(String username) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectOne("AccountDAO.getByUsername", username);
        }
    }

    @Override
    public List<AccountDTO> getAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("AccountDAO.getAll");
        }
    }
}
