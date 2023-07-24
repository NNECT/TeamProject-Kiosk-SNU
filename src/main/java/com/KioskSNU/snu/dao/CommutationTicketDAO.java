package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.CommutationTicketDTO;
import com.KioskSNU.snu.mapper.CommutationTicketMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CommutationTicketDAO extends SqlSessionDaoSupport implements CommutationTicketMapper {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(CommutationTicketDTO commutationTicketDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.mapper.CommutationTicketMapper.insert", commutationTicketDTO);
    }

    @Override
    public int update(CommutationTicketDTO commutationTicketDTO) {
        return getSqlSession().update("com.KioskSNU.snu.mapper.CommutationTicketMapper.update", commutationTicketDTO);
    }

    @Override
    public int delete(CommutationTicketDTO commutationTicketDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.mapper.CommutationTicketMapper.delete", commutationTicketDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public CommutationTicketDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.CommutationTicketMapper.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommutationTicketDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.CommutationTicketMapper.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommutationTicketDTO> getAllByActive(boolean active) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.CommutationTicketMapper.getAllByActive", active);
    }
}
