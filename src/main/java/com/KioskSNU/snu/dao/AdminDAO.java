package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AdminDTO;
import com.KioskSNU.snu.service.AdminService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AdminDAO extends SqlSessionDaoSupport implements AdminService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(AdminDTO adminDTO) {
        return getSqlSession().insert("admin.insert", adminDTO);
    }

    @Override
    public int update(AdminDTO adminDTO) {
        return getSqlSession().update("admin.update", adminDTO);
    }

    @Override
    public int delete(AdminDTO adminDTO) {
        return getSqlSession().delete("admin.delete", adminDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public AdminDTO getById(int id) {
        return getSqlSession().selectOne("admin.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public AdminDTO getByUsername(String username) {
        return getSqlSession().selectOne("admin.getByUsername", username);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AdminDTO> getAll() {
        return getSqlSession().selectList("admin.getAll");
    }
}
