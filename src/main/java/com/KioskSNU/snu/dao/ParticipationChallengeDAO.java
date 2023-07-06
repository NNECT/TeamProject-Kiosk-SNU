package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ChallengeDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.service.ParticipationChallengeService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ParticipationChallengeDAO extends SqlSessionDaoSupport implements ParticipationChallengeService {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(ParticipationChallengeDTO participationChallengeDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.service.ParticipationChallengeService.insert", participationChallengeDTO);
    }

    @Override
    public int update(ParticipationChallengeDTO participationChallengeDTO) {
        return getSqlSession().update("com.KioskSNU.snu.service.ParticipationChallengeService.update", participationChallengeDTO);
    }

    @Override
    public int delete(ParticipationChallengeDTO participationChallengeDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.service.ParticipationChallengeService.delete", participationChallengeDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public ParticipationChallengeDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.service.ParticipationChallengeService.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ParticipationChallengeDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.service.ParticipationChallengeService.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<ParticipationChallengeDTO> getAllByAccount(AccountDTO accountDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.service.ParticipationChallengeService.getAllByAccount", accountDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ParticipationChallengeDTO> getAllByChallenge(ChallengeDTO challengeDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.service.ParticipationChallengeService.getAllByChallenge", challengeDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ParticipationChallengeDTO> getAllByActive(boolean active) {
        return getSqlSession().selectList("com.KioskSNU.snu.service.ParticipationChallengeService.getAllByActive", active);
    }
}
