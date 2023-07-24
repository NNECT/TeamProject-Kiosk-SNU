package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.SeatDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.mapper.UsageSeatMapper;
import com.KioskSNU.snu.service.SeatService;
import com.KioskSNU.snu.service.UsageSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class UsageSeatServiceImpl implements UsageSeatService {
    @Qualifier("usageSeatDAO")
    private final UsageSeatMapper usageSeatDAO;
    private final SeatService seatService;
    private final ConcurrentHashMap<Integer, UsageSeatDTO> seatMap;

    @Override
    public int insert(UsageSeatDTO usageSeatDTO) {
        return usageSeatDAO.insert(usageSeatDTO);
    }

    @Override
    public int update(UsageSeatDTO usageSeatDTO) {
        return usageSeatDAO.update(usageSeatDTO);
    }

    @Override
    public int delete(UsageSeatDTO usageSeatDTO) {
        return usageSeatDAO.delete(usageSeatDTO);
    }

    @Override
    public UsageSeatDTO getById(int id) {
        return usageSeatDAO.getById(id);
    }

    @Override
    public List<UsageSeatDTO> getAll() {
        return usageSeatDAO.getAll();
    }

    @Override
    public List<UsageSeatDTO> getAllBySeat(SeatDTO seatDTO) {
        return usageSeatDAO.getAllBySeat(seatDTO);
    }

    @Override
    public List<UsageSeatDTO> getAllByAccount(AccountDTO accountDTO) {
        return usageSeatDAO.getAllByAccount(accountDTO);
    }

    @Override
    public Map<Integer, Integer> getSeatStatusMap() {
        return getSeatStatusMap(null);
    }

    @Override
    public Map<Integer, Integer> getSeatStatusMap(AccountDTO accountDTO) {
        Map<Integer, Integer> seatStatusMap = new HashMap<>();

        // 사용 불가 좌석 처리
        seatService.getAll().forEach(seat -> seatStatusMap.put(seat.getSeatNumber(), seat.isUsable() ? 1 : -1));

        // 사용중 좌석 처리
        seatMap.forEach((id, usage) -> {
            if (accountDTO != null && accountDTO.getId() == usage.getAccount_id()) {
                seatStatusMap.put(id, 2);
            } else {
                seatStatusMap.put(id, 0);
            }
        });

        return seatStatusMap;
    }
}
