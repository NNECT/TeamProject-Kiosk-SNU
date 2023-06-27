package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.ChallengeDTO;

import java.util.List;

public interface ChallengeService {
    ChallengeDTO insert(ChallengeDTO challengeDTO);
    ChallengeDTO update(ChallengeDTO challengeDTO);
    boolean delete(ChallengeDTO challengeDTO);
    ChallengeDTO getById(int id);
    ChallengeDTO getByTitle(String title);
    List<ChallengeDTO> getAll();
    List<ChallengeDTO> getAllByActive(boolean active);
    List<ChallengeDTO> getAllByVisible(boolean visible);
}
