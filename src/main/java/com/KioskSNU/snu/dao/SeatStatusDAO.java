package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.service.SeatStatusService;
import com.KioskSNU.snu.viewdto.SeatStatusDTO;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SeatStatusDAO extends SqlSessionDaoSupport implements SeatStatusService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public List<SeatStatusDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.service.SeatStatusService.getAll");
    }
}
