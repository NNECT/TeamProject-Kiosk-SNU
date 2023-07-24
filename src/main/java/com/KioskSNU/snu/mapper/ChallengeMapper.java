package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.ChallengeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChallengeMapper {
    int insert(ChallengeDTO challengeDTO);
    int update(ChallengeDTO challengeDTO);
    int delete(ChallengeDTO challengeDTO);
    ChallengeDTO getById(int id);
    List<ChallengeDTO> getAll();
    List<ChallengeDTO> getAllByActive(boolean active);
    List<ChallengeDTO> getAllByVisible(boolean visible);
    int getCountOfActiveChallenges();
}
