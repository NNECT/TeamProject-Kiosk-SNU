package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.mapper.UsageLockerMapper;
import com.KioskSNU.snu.service.LockerService;
import com.KioskSNU.snu.service.UsageLockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsageLockerServiceImpl implements UsageLockerService {
    @Qualifier("usageLockerDAO")
    private final UsageLockerMapper usageLockerDAO;
    private final LockerService lockerService;

    @Override
    public int insert(UsageLockerDTO usageLockerDTO) {
        return usageLockerDAO.insert(usageLockerDTO);
    }

    @Override
    public int update(UsageLockerDTO usageLockerDTO) {
        return usageLockerDAO.update(usageLockerDTO);
    }

    @Override
    public int delete(UsageLockerDTO usageLockerDTO) {
        return usageLockerDAO.delete(usageLockerDTO);
    }

    @Override
    public UsageLockerDTO getById(int id) {
        return usageLockerDAO.getById(id);
    }

    @Override
    public List<UsageLockerDTO> getAll() {
        return usageLockerDAO.getAll();
    }

    @Override
    public List<UsageLockerDTO> getAllByLocker(LockerDTO lockerDTO) {
        return usageLockerDAO.getAllByLocker(lockerDTO);
    }

    @Override
    public List<UsageLockerDTO> getAllByAccount(AccountDTO accountDTO) {
        return usageLockerDAO.getAllByAccount(accountDTO);
    }

    @Override
    public List<UsageLockerDTO> getAllValidDate() {
        return usageLockerDAO.getAllValidDate();
    }

    @Override
    public boolean hasLocker(AccountDTO accountDTO) {
        // 사용자 확인
        if (accountDTO == null) return false;

        // 사용 기록 확인
        List<UsageLockerDTO> usageLockerDTOList = this.getAllByAccount(accountDTO);
        if (usageLockerDTOList.isEmpty()) return false;

        // 사용 기록 유효기간 확인
        UsageLockerDTO usageLockerDTO = usageLockerDTOList.get(0);
        return !usageLockerDTO.getEndDate().isBefore(LocalDate.now());
    }

    @Override
    public UsageLockerDTO getLocker(AccountDTO accountDTO) {
        // 사용자 확인
        if (accountDTO == null) return null;

        // 사용 기록 확인
        List<UsageLockerDTO> usageLockerDTOList = this.getAllByAccount(accountDTO);
        if (usageLockerDTOList.isEmpty()) return null;

        // 사용 기록 유효기간 확인
        UsageLockerDTO usageLockerDTO = usageLockerDTOList.get(0);
        if (usageLockerDTO.getEndDate().isBefore(LocalDate.now())) return null;

        return usageLockerDTO;
    }

    @Override
    public Map<Integer, Integer> getLockerStatusMap() {
        return getLockerStatusMap(null);
    }

    @Override
    public Map<Integer, Integer> getLockerStatusMap(AccountDTO accountDTO) {
        Map<Integer, Integer> lockerStatusMap = new HashMap<>();

        // 사용 불가 사물함 처리
        lockerService.getAll().forEach(locker -> lockerStatusMap.put(locker.getLockerNumber(), locker.isUsable() ? 1 : -1));

        // 사용중 사물함 처리
        this.getAllValidDate().forEach(usageLockerDTO -> {
            if (accountDTO != null && accountDTO.getId() == usageLockerDTO.getAccount_id()) {
                lockerStatusMap.put(usageLockerDTO.getLocker_id(), 2);
            } else {
                lockerStatusMap.put(usageLockerDTO.getLocker_id(), 0);
            }
        });

        return lockerStatusMap;
    }
}
