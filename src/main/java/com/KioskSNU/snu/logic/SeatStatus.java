package com.KioskSNU.snu.logic;

import com.KioskSNU.snu.dto.AccountDTO;

import java.time.LocalDate;
import java.util.Map;

public interface SeatStatus {
    /**
     * 현재 좌석 상태를 반환하는 메소드
     *
     * @return 좌석 상태를 나타내는 Map (-1: 사용 불가, 0: 사용중, 1: 사용 가능)
     */
    Map<Integer, Integer> getSeatStatusMap();

    /**
     * 현재 좌석 상태를 반환하는 메소드
     *
     * @param accountDTO 정보를 얻는 사용자의 계정
     * @return 좌석 상태를 나타내는 Map (-1: 사용 불가, 0: 사용중, 1: 사용 가능, 2: 현재 사용자의 좌석)
     */
    Map<Integer, Integer> getSeatStatusMap(AccountDTO accountDTO);

    /**
     * 이전 연도의 같은 달부터 현재까지의 월별 누적 이용시간 기록을 가져온다.
     * @return 월별 누적 이용시간 기록을 나타내는 Map
     */
    Map<LocalDate, Double> get1YearTimes();
}
