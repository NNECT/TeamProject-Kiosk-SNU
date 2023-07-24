package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.SeatDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.mapper.UsageSeatMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsageSeatDAO extends SqlSessionDaoSupport implements UsageSeatMapper {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(UsageSeatDTO usageSeatDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.mapper.UsageSeatMapper.insert", usageSeatDTO);
    }

    @Override
    public int update(UsageSeatDTO usageSeatDTO) {
        return getSqlSession().update("com.KioskSNU.snu.mapper.UsageSeatMapper.update", usageSeatDTO);
    }

    @Override
    public int delete(UsageSeatDTO usageSeatDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.mapper.UsageSeatMapper.delete", usageSeatDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public UsageSeatDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.UsageSeatMapper.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageSeatDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.UsageSeatMapper.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageSeatDTO> getAllBySeat(SeatDTO seatDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.UsageSeatMapper.getAllBySeat", seatDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageSeatDTO> getAllByAccount(AccountDTO accountDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.UsageSeatMapper.getAllByAccount", accountDTO);
    }
}
