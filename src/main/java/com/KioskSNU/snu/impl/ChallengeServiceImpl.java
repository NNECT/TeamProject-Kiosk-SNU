package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.ChallengeDTO;
import com.KioskSNU.snu.mapper.ChallengeMapper;
import com.KioskSNU.snu.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {
    @Qualifier("challengeDAO")
    private final ChallengeMapper challengeDAO;

    @Override
    public int insert(ChallengeDTO challengeDTO) {
        return challengeDAO.insert(challengeDTO);
    }

    @Override
    public int update(ChallengeDTO challengeDTO) {
        return challengeDAO.update(challengeDTO);
    }

    @Override
    public int delete(ChallengeDTO challengeDTO) {
        return challengeDAO.delete(challengeDTO);
    }

    @Override
    public ChallengeDTO getById(int id) {
        return challengeDAO.getById(id);
    }

    @Override
    public List<ChallengeDTO> getAll() {
        return challengeDAO.getAll();
    }

    @Override
    public List<ChallengeDTO> getAllByActive(boolean active) {
        return challengeDAO.getAllByActive(active);
    }

    @Override
    public List<ChallengeDTO> getAllByVisible(boolean visible) {
        return challengeDAO.getAllByVisible(visible);
    }

    @Override
    public int getCountOfActiveChallenges() {
        return challengeDAO.getCountOfActiveChallenges();
    }
}
