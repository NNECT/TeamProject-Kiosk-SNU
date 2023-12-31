package com.KioskSNU.snu.logic;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;

import java.time.LocalDateTime;

public interface ParticipationChallengeStatus {
    /**
     * 챌린지 종료 시간을 계산하여 설정
     * @param participationChallengeDTO 챌린지 참여 정보 DTO
     */
    void setEndDateTime(ParticipationChallengeDTO participationChallengeDTO);

    /**
     * 참여중 챌린지가 있는지 확인
     * @param accountDTO 해당 사용자 계정
     * @return 참여중 챌린지 존재 여부
     */
    boolean hasParticipationChallenge(AccountDTO accountDTO);

    /**
     * 참여중 챌린지 정보 가져오기
     * @param accountDTO 해당 사용자 계정
     * @return 참여중 챌린지 정보
     */
    ParticipationChallengeDTO getParticipationChallenge(AccountDTO accountDTO);

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
