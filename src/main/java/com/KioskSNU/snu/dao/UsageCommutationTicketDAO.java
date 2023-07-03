package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsageCommutationTicketDAO extends SqlSessionDaoSupport implements UsageCommutationTicketService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        return getSqlSession().insert("usageCommutationTicket.insert", usageCommutationTicketDTO);
    }

    @Override
    public int update(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        return getSqlSession().update("usageCommutationTicket.update", usageCommutationTicketDTO);
    }

    @Override
    public int delete(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        return getSqlSession().delete("usageCommutationTicket.delete", usageCommutationTicketDTO);
    }

    @Override
    public UsageCommutationTicketDTO getById(int id) {
        return getSqlSession().selectOne("usageCommutationTicket.getById", id);
    }

    @Override
    public List<UsageCommutationTicketDTO> getAll() {
        return getSqlSession().selectList("usageCommutationTicket.getAll");
    }

    @Override
    public List<UsageCommutationTicketDTO> getAllByAccount(AccountDTO accountDTO) {
        return getSqlSession().selectList("usageCommutationTicket.getAllByAccount", accountDTO);
    }
}
