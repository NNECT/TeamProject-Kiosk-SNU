package com.KioskSNU.snu.logic;

import java.time.LocalDate;
import java.util.Map;

public interface CommutationTicketSituationAnalysis {
    /**
     * 이전 연도의 같은 달부터 현재까지의 월별 기간권 사용자 수를 가져온다.
     * @return 월별 기간권 사용자 수를 나타내는 Map
     */
    Map<LocalDate, Integer> get1YearCommutationTicketUsers();
}
