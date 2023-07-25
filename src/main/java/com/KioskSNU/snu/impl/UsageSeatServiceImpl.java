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

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    public List<UsageSeatDTO> getAllBy1Year() {
        return usageSeatDAO.getAllBy1Year();
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

    @Override
    public Map<LocalDate, Double> get1YearTimes() {
        LinkedHashMap<LocalDate, Double> timesMap = new LinkedHashMap<>();
        LocalDateTime ago1Year = LocalDateTime.now().minusYears(1).withDayOfMonth(1);
        System.out.println(ago1Year);

        // timesMap 1년치 초기화
        for (
                LocalDate month = LocalDate.now().minusYears(1).withDayOfMonth(1);
                !month.isAfter(LocalDate.now().withDayOfMonth(1));
                month = month.plusMonths(1)
        ) {
            timesMap.put(month, 0.0);
        }
        System.out.println(timesMap);

        List<UsageSeatDTO> usageSeatDTOList = getAllBy1Year();
        System.out.println(usageSeatDTOList);
        usageSeatDTOList.forEach(usageSeatDTO -> {
            LocalDateTime start = usageSeatDTO.getStartDateTime();
            LocalDateTime end = usageSeatDTO.getEndDateTime();

            // 탐색 범위를 벗어난 경우
            if (end.isBefore(ago1Year)) return;

            // 데이터가 걸쳐 있는 모든 월에 대해 반복
            for (LocalDateTime date = start; !date.isAfter(end); date = date.plusMonths(1)) {
                LocalDate firstDayOfMonth = date.withDayOfMonth(1).toLocalDate();

                LocalDateTime endOfMonth = (date.getMonth() == end.getMonth() && date.getYear() == end.getYear())
                        ? end
                        : firstDayOfMonth.plusMonths(1).atStartOfDay();

                long minutesUsed = Duration.between(date, endOfMonth).toMinutes();

                // 현재 월의 사용 시간에 시간 사용 시간 더하기
                timesMap.put(firstDayOfMonth, timesMap.get(firstDayOfMonth) + minutesUsed / 60.0);
            }
        });
        System.out.println("over");

        return timesMap;
    }
}
