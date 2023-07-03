package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.ChallengeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface ChallengeService {
    int insert(ChallengeDTO challengeDTO);
    int update(ChallengeDTO challengeDTO);
    int delete(ChallengeDTO challengeDTO);
    ChallengeDTO getById(int id);
    ChallengeDTO getByTitle(String title);
    List<ChallengeDTO> getAll();
    List<ChallengeDTO> getAllByActive(boolean active);
    List<ChallengeDTO> getAllByVisible(boolean visible);
}
