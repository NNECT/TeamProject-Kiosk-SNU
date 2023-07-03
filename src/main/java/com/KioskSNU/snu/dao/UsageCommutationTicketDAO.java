package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsageCommutationTicketDAO extends DAOTemplate implements UsageCommutationTicketService {
    @Autowired
    public UsageCommutationTicketDAO(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    @Override
    public int insert(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.insert("UsageCommutationTicketDAO.insert", usageCommutationTicketDTO);
        }
    }

    @Override
    public int update(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.update("UsageCommutationTicketDAO.update", usageCommutationTicketDTO);
        }
    }

    @Override
    public int delete(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.delete("UsageCommutationTicketDAO.delete", usageCommutationTicketDTO);
        }
    }

    @Override
    public UsageCommutationTicketDTO getById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectOne("UsageCommutationTicketDAO.getById", id);
        }
    }

    @Override
    public List<UsageCommutationTicketDTO> getAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("UsageCommutationTicketDAO.getAll");
        }
    }

    @Override
    public List<UsageCommutationTicketDTO> getAllByAccount(AccountDTO accountDTO) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("UsageCommutationTicketDAO.getAllByAccount", accountDTO);
        }
    }
}
