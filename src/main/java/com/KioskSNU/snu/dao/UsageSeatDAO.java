package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.SeatDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.UsageSeatService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsageSeatDAO extends SqlSessionDaoSupport implements UsageSeatService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(UsageSeatDTO usageSeatDTO) {
        return getSqlSession().insert("usageSeat.insert", usageSeatDTO);
    }

    @Override
    public int update(UsageSeatDTO usageSeatDTO) {
        return getSqlSession().update("usageSeat.update", usageSeatDTO);
    }

    @Override
    public int delete(UsageSeatDTO usageSeatDTO) {
        return getSqlSession().delete("usageSeat.delete", usageSeatDTO);
    }

    @Override
    public UsageSeatDTO getById(int id) {
        return getSqlSession().selectOne("usageSeat.getById", id);
    }

    @Override
    public List<UsageSeatDTO> getAll() {
        return getSqlSession().selectList("usageSeat.getAll");
    }

    @Override
    public List<UsageSeatDTO> getAllBySeat(SeatDTO seatDTO) {
        return getSqlSession().selectList("usageSeat.getAllBySeat", seatDTO);
    }

    @Override
    public List<UsageSeatDTO> getAllByAccount(AccountDTO accountDTO) {
        return getSqlSession().selectList("usageSeat.getAllByAccount", accountDTO);
    }
}
