package com.KioskSNU.api;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.service.LockerService;
import com.KioskSNU.snu.service.UsageLockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LockerStatus {
    private final LockerService lockerService;
    private final UsageLockerService usageLockerService;

    @Autowired
    public LockerStatus(
            LockerService lockerService,
            UsageLockerService usageLockerService
    ) {
        this.lockerService = lockerService;
        this.usageLockerService = usageLockerService;
    }

    /**
     * 사물함 등록 여부 확인 메소드
     * @param accountDTO 사용자
     * @return 사물함 등록 여부
     */
    public boolean hasLocker(AccountDTO accountDTO) {
        // 사용자 확인
        if (accountDTO == null) return false;

        // 사용 기록 확인
        List<UsageLockerDTO> usageLockerDTOList = usageLockerService.getAllByAccount(accountDTO);
        if (usageLockerDTOList.isEmpty()) return false;

        // 사용 기록 유효기간 확인
        UsageLockerDTO usageLockerDTO = usageLockerDTOList.get(0);
        return !usageLockerDTO.getEndDate().isBefore(LocalDate.now());
    }

    /**
     * 등록 사물함 Get 메소드
     * @param accountDTO 사용자
     * @return 등록 사물함
     */
    public UsageLockerDTO getLocker(AccountDTO accountDTO) {
        // 사용자 확인
        if (accountDTO == null) return null;

        // 사용 기록 확인
        List<UsageLockerDTO> usageLockerDTOList = usageLockerService.getAllByAccount(accountDTO);
        if (usageLockerDTOList.isEmpty()) return null;

        // 사용 기록 유효기간 확인
        UsageLockerDTO usageLockerDTO = usageLockerDTOList.get(0);
        if (usageLockerDTO.getEndDate().isBefore(LocalDate.now())) return null;

        return usageLockerDTO;
    }

    /**
     * 현재 사물함 상태를 반환하는 메소드
     * @return 사물함 상태를 나타내는 Map (-1: 사용 불가, 0: 사용중, 1: 사용 가능)
     */
    public Map<Integer, Integer> getLockerStatusMap() {
        return getLockerStatusMap(null);
    }

    /**
     * 현재 사물함 상태를 반환하는 메소드
     * @param accountDTO 정보를 얻는 사용자의 계정
     * @return 사물함 상태를 나타내는 Map (-1: 사용 불가, 0: 사용중, 1: 사용 가능, 2: 현재 사용자의 룸)
     */
    public Map<Integer, Integer> getLockerStatusMap(AccountDTO accountDTO) {
        Map<Integer, Integer> lockerStatusMap = new HashMap<>();

        // 사용 불가 사물함 처리
        lockerService.getAll().forEach(locker -> lockerStatusMap.put(locker.getLockerNumber(), locker.isUsable() ? 1 : -1));

        // 사용중 사물함 처리
        usageLockerService.getAllValidDate().forEach(usageLockerDTO -> {
            if (accountDTO != null && accountDTO.getId() == usageLockerDTO.getAccount_id()) {
                lockerStatusMap.put(usageLockerDTO.getLocker_id(), 2);
            } else {
                lockerStatusMap.put(usageLockerDTO.getLocker_id(), 0);
            }
        });

        return lockerStatusMap;
    }
}
