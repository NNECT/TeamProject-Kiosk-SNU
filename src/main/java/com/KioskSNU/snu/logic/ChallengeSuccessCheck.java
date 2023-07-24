package com.KioskSNU.snu.logic;

import com.KioskSNU.snu.dto.ParticipationChallengeDTO;

public interface ChallengeSuccessCheck {
    /**
     * 챌린지 기간 내 사용 시간 계산
     *
     * @param participationChallengeDTO 참여 챌린지
     * @return 기간 내 사용 시간
     */
    int getChallengeUsageMinutes(ParticipationChallengeDTO participationChallengeDTO);

    /**
     * 챌린지 기간 내 사용 날짜 계산
     *
     * @param participationChallengeDTO 참여 챌린지
     * @return 기간 내 사용 날짜
     */
    int getChallengeUsageDates(ParticipationChallengeDTO participationChallengeDTO);

    /**
     * 챌린지 성공 여부 확인
     *
     * @param participationChallengeDTO 참여 챌린지
     * @return 성공 여부 (성공: 1, 실패: -1, 진행 중: 0)
     */
    int challengeSuccessCheck(ParticipationChallengeDTO participationChallengeDTO);
}
