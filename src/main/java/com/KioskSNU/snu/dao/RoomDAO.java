package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.RoomDTO;
import com.KioskSNU.snu.dto.RoomTypeDTO;
import com.KioskSNU.snu.service.RoomService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RoomDAO extends SqlSessionDaoSupport implements RoomService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(RoomDTO roomDTO) {
        return getSqlSession().insert("room.insert", roomDTO);
    }

    @Override
    public int update(RoomDTO roomDTO) {
        return getSqlSession().update("room.update", roomDTO);
    }

    @Override
    public int delete(RoomDTO roomDTO) {
        return getSqlSession().delete("room.delete", roomDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public RoomDTO getById(int id) {
        return getSqlSession().selectOne("room.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public RoomDTO getByRoomNumber(int roomNumber) {
        return getSqlSession().selectOne("room.getByRoomNumber", roomNumber);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoomDTO> getAll() {
        return getSqlSession().selectList("room.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoomDTO> getAllByRoomType(RoomTypeDTO roomTypeDTO) {
        return getSqlSession().selectList("room.getAllByRoomType", roomTypeDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoomDTO> getAllByUsable(boolean usable) {
        return getSqlSession().selectList("room.getAllByUsable", usable);
    }
}
