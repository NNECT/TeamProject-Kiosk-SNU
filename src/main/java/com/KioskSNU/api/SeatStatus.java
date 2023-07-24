package com.KioskSNU.api;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class SeatStatus {
    private final ConcurrentHashMap<Integer, UsageSeatDTO> seatMap;
    private final SeatService seatService;

    /**
     * 현재 좌석 상태를 반환하는 메소드
     * @return 좌석 상태를 나타내는 Map (-1: 사용 불가, 0: 사용중, 1: 사용 가능)
     */
    public Map<Integer, Integer> getSeatStatusMap() {
        return getSeatStatusMap(null);
    }

    /**
     * 현재 좌석 상태를 반환하는 메소드
     * @param accountDTO 정보를 얻는 사용자의 계정
     * @return 좌석 상태를 나타내는 Map (-1: 사용 불가, 0: 사용중, 1: 사용 가능, 2: 현재 사용자의 좌석)
     */
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
