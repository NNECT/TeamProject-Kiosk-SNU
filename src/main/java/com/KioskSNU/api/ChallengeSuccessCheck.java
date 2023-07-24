package com.KioskSNU.api;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.UsageSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ChallengeSuccessCheck {
    AccountService accountService;
    UsageSeatService usageSeatService;

    /**
     * 챌린지 기간 내 사용 시간 계산
     * @param participationChallengeDTO 참여 챌린지
     * @return 기간 내 사용 시간
     */
    public int getChallengeUsageMinutes(ParticipationChallengeDTO participationChallengeDTO) {
        // 사용자 확인
        AccountDTO accountDTO = accountService.getById(participationChallengeDTO.getAccount_id());
        if (accountDTO == null) return 0;

        // 사용 기록 확인
        List<UsageSeatDTO> usageSeatDTOList = usageSeatService.getAllByAccount(accountDTO);
        if (usageSeatDTOList == null || usageSeatDTOList.isEmpty()) return 0;

        // 기간 내 사용 기록 확인
        int[] usageMinutes = {0};
        LocalDateTime challengeStartDateTime = participationChallengeDTO.getStartDateTime();
        LocalDateTime challengeEndDateTime = participationChallengeDTO.getEndDateTime();
        usageSeatDTOList.forEach(usage -> {
            // 기간 내 사용 기록만 확인
            LocalDateTime startDateTime = usage.getStartDateTime();
            if (startDateTime.isBefore(challengeStartDateTime))
                startDateTime = challengeStartDateTime;
            LocalDateTime endDateTime = usage.getEndDateTime();
            if (endDateTime.isAfter(challengeEndDateTime))
                endDateTime = challengeEndDateTime;

            // 사용 기록이 기간 내에 없으면 continue
            if (!startDateTime.isBefore(endDateTime)) return;

            // 기간 내 사용 시간 계산
            usageMinutes[0] += startDateTime.until(endDateTime, ChronoUnit.MINUTES);
        });

        return usageMinutes[0];
    }

    /**
     * 챌린지 기간 내 사용 날짜 계산
     * @param participationChallengeDTO 참여 챌린지
     * @return 기간 내 사용 날짜
     */
    public int getChallengeUsageDates(ParticipationChallengeDTO participationChallengeDTO) {
        // 사용자 확인
        AccountDTO accountDTO = accountService.getById(participationChallengeDTO.getAccount_id());
        if (accountDTO == null) return 0;

        // 사용 기록 확인
        List<UsageSeatDTO> usageSeatDTOList = usageSeatService.getAllByAccount(accountDTO);
        if (usageSeatDTOList == null || usageSeatDTOList.isEmpty()) return 0;

        // 기간 내 사용 기록 확인
        Set<LocalDate> usageDates = new HashSet<>();
        LocalDateTime challengeStartDateTime = participationChallengeDTO.getStartDateTime();
        LocalDateTime challengeEndDateTime = participationChallengeDTO.getEndDateTime();
        usageSeatDTOList.forEach(usage -> {
            // 기간 내 사용 기록만 확인
            LocalDateTime startDateTime = usage.getStartDateTime();
            if (startDateTime.isBefore(challengeStartDateTime))
                startDateTime = challengeStartDateTime;
            LocalDateTime endDateTime = usage.getEndDateTime();
            if (endDateTime.isAfter(challengeEndDateTime))
                endDateTime = challengeEndDateTime;

            // 사용 기록이 기간 내에 없으면 continue
            if (!startDateTime.isBefore(endDateTime)) return;

            // 기간 내 사용 날짜 계산
            for (
                    LocalDate date = startDateTime.toLocalDate();
                 	date.isBefore(endDateTime.toLocalDate());
                 	date = date.plusDays(1)
            ) {
                usageDates.add(date);
            }
        });

        return usageDates.size();
    }

    /**
     * 챌린지 성공 여부 확인
     * @param participationChallengeDTO 참여 챌린지
     * @return 성공 여부 (성공: 1, 실패: -1, 진행 중: 0)
     */
    public int challengeSuccessCheck(ParticipationChallengeDTO participationChallengeDTO) {
        // 사용자 확인
        AccountDTO accountDTO = accountService.getById(participationChallengeDTO.getAccount_id());
        if (accountDTO == null) return 0;

        // 기간 내 사용 기록 확인
        int usageDates = getChallengeUsageDates(participationChallengeDTO);
        int usageMinutes = getChallengeUsageMinutes(participationChallengeDTO);

        int success = 1;

        // 날짜 조건
        int remainDays = usageDates - participationChallengeDTO.getGoalDay();
        if (remainDays > 0) {
            if (remainDays > LocalDate.now().until(participationChallengeDTO.getEndDateTime().toLocalDate(), ChronoUnit.DAYS)) {
                success = -1;
            } else {
                success = 0;
            }
        }

        // 시간 조건
        int remainMinutes = usageMinutes - participationChallengeDTO.getGoalHour() * 60;
        if (remainMinutes > 0) {
            if (remainMinutes > LocalDateTime.now().until(participationChallengeDTO.getEndDateTime(), ChronoUnit.MINUTES)) {
                success = -1;
            } else {
                success = 0;
            }
        }

        return success;
    }
}
