package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dao.ParticipationChallengeDAO;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ChallengeDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.service.ParticipationChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipationChallengeServiceImpl implements ParticipationChallengeService {
    private final ParticipationChallengeDAO participationChallengeDAO;

    @Override
    public int insert(ParticipationChallengeDTO participationChallengeDTO) {
        return participationChallengeDAO.insert(participationChallengeDTO);
    }

    @Override
    public int update(ParticipationChallengeDTO participationChallengeDTO) {
        return participationChallengeDAO.update(participationChallengeDTO);
    }

    @Override
    public int delete(ParticipationChallengeDTO participationChallengeDTO) {
        return participationChallengeDAO.delete(participationChallengeDTO);
    }

    @Override
    public ParticipationChallengeDTO getById(int id) {
        return participationChallengeDAO.getById(id);
    }

    @Override
    public List<ParticipationChallengeDTO> getAll() {
        return participationChallengeDAO.getAll();
    }

    @Override
    public List<ParticipationChallengeDTO> getAllByAccount(AccountDTO accountDTO) {
        return participationChallengeDAO.getAllByAccount(accountDTO);
    }

    @Override
    public List<ParticipationChallengeDTO> getAllByChallenge(ChallengeDTO challengeDTO) {
        return participationChallengeDAO.getAllByChallenge(challengeDTO);
    }

    @Override
    public List<ParticipationChallengeDTO> getAllByActive(boolean active) {
        return participationChallengeDAO.getAllByActive(active);
    }
}
