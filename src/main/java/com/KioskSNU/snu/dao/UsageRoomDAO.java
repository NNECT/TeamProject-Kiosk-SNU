package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.RoomDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.mapper.UsageRoomMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsageRoomDAO extends SqlSessionDaoSupport implements UsageRoomMapper {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(UsageRoomDTO usageRoomDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.mapper.UsageRoomMapper.insert", usageRoomDTO);
    }

    @Override
    public int update(UsageRoomDTO usageRoomDTO) {
        return getSqlSession().update("com.KioskSNU.snu.mapper.UsageRoomMapper.update", usageRoomDTO);
    }

    @Override
    public int delete(UsageRoomDTO usageRoomDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.mapper.UsageRoomMapper.delete", usageRoomDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public UsageRoomDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.UsageRoomMapper.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageRoomDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.UsageRoomMapper.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageRoomDTO> getAllByRoom(RoomDTO roomDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.UsageRoomMapper.getAllByRoom", roomDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageRoomDTO> getAllByAccount(AccountDTO accountDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.UsageRoomMapper.getAllByAccount", accountDTO);
    }
}
