package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.mapper.UsageCommutationTicketMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsageCommutationTicketDAO extends SqlSessionDaoSupport implements UsageCommutationTicketMapper {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.mapper.UsageCommutationTicketMapper.insert", usageCommutationTicketDTO);
    }

    @Override
    public int update(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        return getSqlSession().update("com.KioskSNU.snu.mapper.UsageCommutationTicketMapper.update", usageCommutationTicketDTO);
    }

    @Override
    public int delete(UsageCommutationTicketDTO usageCommutationTicketDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.mapper.UsageCommutationTicketMapper.delete", usageCommutationTicketDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public UsageCommutationTicketDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.UsageCommutationTicketMapper.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageCommutationTicketDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.UsageCommutationTicketMapper.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageCommutationTicketDTO> getAllByAccount(AccountDTO accountDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.UsageCommutationTicketMapper.getAllByAccount", accountDTO);
    }
}
