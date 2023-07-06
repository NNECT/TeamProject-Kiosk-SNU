package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.RoomDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.service.UsageRoomService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsageRoomDAO extends SqlSessionDaoSupport implements UsageRoomService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(UsageRoomDTO usageRoomDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.service.UsageRoomService.insert", usageRoomDTO);
    }

    @Override
    public int update(UsageRoomDTO usageRoomDTO) {
        return getSqlSession().update("com.KioskSNU.snu.service.UsageRoomService.update", usageRoomDTO);
    }

    @Override
    public int delete(UsageRoomDTO usageRoomDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.service.UsageRoomService.delete", usageRoomDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public UsageRoomDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.service.UsageRoomService.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageRoomDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.service.UsageRoomService.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageRoomDTO> getAllByRoom(RoomDTO roomDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.service.UsageRoomService.getAllByRoom", roomDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsageRoomDTO> getAllByAccount(AccountDTO accountDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.service.UsageRoomService.getAllByAccount", accountDTO);
    }
}
