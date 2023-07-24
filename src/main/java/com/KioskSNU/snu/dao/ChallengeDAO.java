package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.ChallengeDTO;
import com.KioskSNU.snu.service.ChallengeService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ChallengeDAO extends SqlSessionDaoSupport implements ChallengeService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(ChallengeDTO challengeDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.service.ChallengeService.insert", challengeDTO);
    }

    @Override
    public int update(ChallengeDTO challengeDTO) {
        return getSqlSession().update("com.KioskSNU.snu.service.ChallengeService.update", challengeDTO);
    }

    @Override
    public int delete(ChallengeDTO challengeDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.service.ChallengeService.delete", challengeDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public ChallengeDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.service.ChallengeService.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ChallengeDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.service.ChallengeService.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<ChallengeDTO> getAllByActive(boolean active) {
        return getSqlSession().selectList("com.KioskSNU.snu.service.ChallengeService.getAllByActive", active);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ChallengeDTO> getAllByVisible(boolean visible) {
        return getSqlSession().selectList("com.KioskSNU.snu.service.ChallengeService.getAllByVisible", visible);
    }

    @Transactional(readOnly = true)
    @Override
    public int getCountOfActiveChallenges() {
        return 0;
    }
}
