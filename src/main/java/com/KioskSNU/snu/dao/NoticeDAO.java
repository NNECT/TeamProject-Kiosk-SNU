package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.NoticeDTO;
import com.KioskSNU.snu.mapper.NoticeMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class NoticeDAO extends SqlSessionDaoSupport implements NoticeMapper {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(NoticeDTO noticeDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.mapper.NoticeMapper.insert", noticeDTO);
    }

    @Override
    public int update(NoticeDTO noticeDTO) {
        return getSqlSession().update("com.KioskSNU.snu.mapper.NoticeMapper.update", noticeDTO);
    }

    @Override
    public int delete(NoticeDTO noticeDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.mapper.NoticeMapper.delete", noticeDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public NoticeDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.NoticeMapper.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<NoticeDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.NoticeMapper.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<NoticeDTO> getAllByOutside(boolean outside) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.NoticeMapper.getAllByOutside", outside);
    }

    @Transactional(readOnly = true)
    @Override
    public List<NoticeDTO> getAllByActive(boolean active) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.NoticeMapper.getAllByActive", active);
    }
}
