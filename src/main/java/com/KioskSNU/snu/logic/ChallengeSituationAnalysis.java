package com.KioskSNU.snu.logic;

import com.KioskSNU.snu.dto.ParticipationChallengeDTO;

import java.util.List;
import java.util.Map;

public interface ChallengeSituationAnalysis {
    /**
     * 각 챌린지별 참여 데이터를 반환하는 메소드
     * @return Map<String, List<ParticipationChallengeDTO>> : 챌린지 이름을 key로, 해당 챌린지에 참여한 데이터의 List를 value로 가지는 Map
     */
    Map<String, List<ParticipationChallengeDTO>> getEachChallengeSituation();

    /**
     * 참여 데이터 리스트에서 성공률을 계산하는 메소드
     * @param participationChallengeDTOList 참여 데이터 리스트
     * @return double : 성공률
     */
    double getSuccessRate(List<ParticipationChallengeDTO> participationChallengeDTOList);
}
