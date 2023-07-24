package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.SeatDTO;
import com.KioskSNU.snu.mapper.SeatMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SeatDAO extends SqlSessionDaoSupport implements SeatMapper {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(SeatDTO seatDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.mapper.SeatMapper.insert", seatDTO);
    }

    @Override
    public int update(SeatDTO seatDTO) {
        return getSqlSession().update("com.KioskSNU.snu.mapper.SeatMapper.update", seatDTO);
    }

    @Override
    public int delete(SeatDTO seatDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.mapper.SeatMapper.delete", seatDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public SeatDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.SeatMapper.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public SeatDTO getBySeatNumber(int seatNumber) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.SeatMapper.getBySeatNumber", seatNumber);
    }

    @Transactional(readOnly = true)
    @Override
    public List<SeatDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.SeatMapper.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<SeatDTO> getAllByUsable(boolean usable) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.SeatMapper.getAllByUsable", usable);
    }
}
