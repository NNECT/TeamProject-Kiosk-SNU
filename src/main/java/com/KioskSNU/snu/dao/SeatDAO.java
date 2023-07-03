package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.SeatDTO;
import com.KioskSNU.snu.service.SeatService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SeatDAO extends SqlSessionDaoSupport implements SeatService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(SeatDTO seatDTO) {
        return getSqlSession().insert("seat.insert", seatDTO);
    }

    @Override
    public int update(SeatDTO seatDTO) {
        return getSqlSession().update("seat.update", seatDTO);
    }

    @Override
    public int delete(SeatDTO seatDTO) {
        return getSqlSession().delete("seat.delete", seatDTO);
    }

    @Override
    public SeatDTO getById(int id) {
        return getSqlSession().selectOne("seat.getById", id);
    }

    @Override
    public List<SeatDTO> getAll() {
        return getSqlSession().selectList("seat.getAll");
    }

    @Override
    public List<SeatDTO> getAllByUsable(boolean usable) {
        return getSqlSession().selectList("seat.getAllByUsable", usable);
    }
}
