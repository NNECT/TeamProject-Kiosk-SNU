package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AdminDTO;
import com.KioskSNU.snu.mapper.AdminMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AdminDAO extends SqlSessionDaoSupport implements AdminMapper {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(AdminDTO adminDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.mapper.AdminMapper.insert", adminDTO);
    }

    @Override
    public int update(AdminDTO adminDTO) {
        return getSqlSession().update("com.KioskSNU.snu.mapper.AdminMapper.update", adminDTO);
    }

    @Override
    public int delete(AdminDTO adminDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.mapper.AdminMapper.delete", adminDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public AdminDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.AdminMapper.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public AdminDTO getByUsername(String username) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.AdminMapper.getByUsername", username);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AdminDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.AdminMapper.getAll");
    }
}
