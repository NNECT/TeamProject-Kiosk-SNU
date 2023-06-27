package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ChallengeDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;

import java.util.List;

public interface ParticipationChallengeService {
    ParticipationChallengeDTO insert(ParticipationChallengeDTO participationChallengeDTO);
    ParticipationChallengeDTO update(ParticipationChallengeDTO participationChallengeDTO);
    boolean delete(ParticipationChallengeDTO participationChallengeDTO);
    ParticipationChallengeDTO getById(int id);
    List<ParticipationChallengeDTO> getAll();
    List<ParticipationChallengeDTO> getAllByAccount(AccountDTO accountDTO);
    List<ParticipationChallengeDTO> getAllByChallenge(ChallengeDTO challengeDTO);
    List<ParticipationChallengeDTO> getAllByActive(boolean active);
}
