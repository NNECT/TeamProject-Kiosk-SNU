package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ChallengeDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.mapper.ParticipationChallengeMapper;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.ChallengeService;
import com.KioskSNU.snu.service.ParticipationChallengeService;
import com.KioskSNU.snu.service.UsageSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ParticipationChallengeServiceImpl implements ParticipationChallengeService {
    @Qualifier("participationChallengeDAO")
    private final ParticipationChallengeMapper participationChallengeDAO;
    private final AccountService accountService;
    private final UsageSeatService usageSeatService;
    private final ChallengeService challengeService;

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

    @Override
    public List<ParticipationChallengeDTO> getAllByChallengeAndActive(ChallengeDTO challengeDTO, boolean active) {
        return participationChallengeDAO.getAllByChallengeAndActive(challengeDTO, active);
    }

    @Override
    public boolean hasParticipationChallenge(AccountDTO accountDTO) {
        // 사용자 확인
        if (accountDTO == null) return false;

        // 사용자 불러오기
        accountDTO = accountService.getById(accountDTO.getId());
        if (accountDTO == null) return false;

        // 챌린지 이력 확인
        List<ParticipationChallengeDTO> participationChallengeDTOList = getAllByAccount(accountDTO);
        if (participationChallengeDTOList == null || participationChallengeDTOList.isEmpty()) return false;

        // 진행중인 챌린지 확인
        ParticipationChallengeDTO participationChallengeDTO = participationChallengeDTOList.get(0);
        return participationChallengeDTO.isActive();
    }

    @Override
    public ParticipationChallengeDTO getParticipationChallenge(AccountDTO accountDTO) {
        // 사용자 확인
        if (accountDTO == null) return null;

        // 사용자 불러오기
        accountDTO = accountService.getById(accountDTO.getId());
        if (accountDTO == null) return null;

        // 챌린지 이력 확인
        List<ParticipationChallengeDTO> participationChallengeDTOList = getAllByAccount(accountDTO);
        if (participationChallengeDTOList == null || participationChallengeDTOList.isEmpty()) return null;

        // 진행중인 챌린지 확인
        ParticipationChallengeDTO participationChallengeDTO = participationChallengeDTOList.get(0);
        if (!participationChallengeDTO.isActive()) return null;

        return participationChallengeDTO;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public Map<String, List<ParticipationChallengeDTO>> getEachChallengeSituation() {
        Map<String, List<ParticipationChallengeDTO>> situationMap = new LinkedHashMap<>();

        // 유효 챌린지 목록 가져오기
        List<ChallengeDTO> challengeDTOList = challengeService.getAllByActive(true);

        // 챌린지별 결과가 나온 참여 데이터 가져오기
        challengeDTOList.forEach(challengeDTO -> {
            List<ParticipationChallengeDTO> participationChallengeDTOList = getAllByChallengeAndActive(challengeDTO, false);
            situationMap.put(challengeDTO.getTitle(), participationChallengeDTOList);
        });

        return situationMap;
    }

    @Override
    public double getSuccessRate(List<ParticipationChallengeDTO> participationChallengeDTOList) {
        double successCount = 0;
        for (ParticipationChallengeDTO participationChallengeDTO : participationChallengeDTOList) {
            if (participationChallengeDTO.isResult()) successCount++;
        }
        return successCount / participationChallengeDTOList.size() * 100;
    }
}
