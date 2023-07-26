package com.KioskSNU.snu.dao;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ChallengeDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.mapper.ParticipationChallengeMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ParticipationChallengeDAO extends SqlSessionDaoSupport implements ParticipationChallengeMapper {
    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int insert(ParticipationChallengeDTO participationChallengeDTO) {
        return getSqlSession().insert("com.KioskSNU.snu.mapper.ParticipationChallengeMapper.insert", participationChallengeDTO);
    }

    @Override
    public int update(ParticipationChallengeDTO participationChallengeDTO) {
        return getSqlSession().update("com.KioskSNU.snu.mapper.ParticipationChallengeMapper.update", participationChallengeDTO);
    }

    @Override
    public int delete(ParticipationChallengeDTO participationChallengeDTO) {
        return getSqlSession().delete("com.KioskSNU.snu.mapper.ParticipationChallengeMapper.delete", participationChallengeDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public ParticipationChallengeDTO getById(int id) {
        return getSqlSession().selectOne("com.KioskSNU.snu.mapper.ParticipationChallengeMapper.getById", id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ParticipationChallengeDTO> getAll() {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.ParticipationChallengeMapper.getAll");
    }

    @Transactional(readOnly = true)
    @Override
    public List<ParticipationChallengeDTO> getAllByAccount(AccountDTO accountDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.ParticipationChallengeMapper.getAllByAccount", accountDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ParticipationChallengeDTO> getAllByChallenge(ChallengeDTO challengeDTO) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.ParticipationChallengeMapper.getAllByChallenge", challengeDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ParticipationChallengeDTO> getAllByActive(boolean active) {
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.ParticipationChallengeMapper.getAllByActive", active);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ParticipationChallengeDTO> getAllByChallengeAndActive(ChallengeDTO challengeDTO, boolean active) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", challengeDTO.getId());
        params.put("active", active);
        return getSqlSession().selectList("com.KioskSNU.snu.mapper.ParticipationChallengeMapper.getAllByChallengeAndActive", params);
    }
}
