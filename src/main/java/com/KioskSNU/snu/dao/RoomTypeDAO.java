package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.RoomTypeDTO;
import com.KioskSNU.snu.service.RoomTypeService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RoomTypeDAO extends SqlSessionDaoSupport implements RoomTypeService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(RoomTypeDTO roomTypeDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.service.RoomTypeService.insert", roomTypeDTO);
    }

    @Override
    public int update(RoomTypeDTO roomTypeDTO) {
        return getSqlSession().update("com.KioskSNU.snu.service.RoomTypeService.update", roomTypeDTO);
    }

    @Override
    public int delete(RoomTypeDTO roomTypeDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.service.RoomTypeService.delete", roomTypeDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public RoomTypeDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.service.RoomTypeService.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoomTypeDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.service.RoomTypeService.getAll");
    }
}
