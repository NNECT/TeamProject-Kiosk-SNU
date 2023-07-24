package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ChallengeDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParticipationChallengeMapper {
    int insert(ParticipationChallengeDTO participationChallengeDTO);
    int update(ParticipationChallengeDTO participationChallengeDTO);
    int delete(ParticipationChallengeDTO participationChallengeDTO);
    ParticipationChallengeDTO getById(int id);
    List<ParticipationChallengeDTO> getAll();
    List<ParticipationChallengeDTO> getAllByAccount(AccountDTO accountDTO);
    List<ParticipationChallengeDTO> getAllByChallenge(ChallengeDTO challengeDTO);
    List<ParticipationChallengeDTO> getAllByActive(boolean active);
}
