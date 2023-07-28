package com.KioskSNU.snu.logic;

import com.KioskSNU.snu.dto.PaymentDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PaymentSituationAnalysis {
    /**
     * 이전 연도의 같은 달부터 현재까지의 월별 결제 데이터를 가져온다.
     * @return 월별 결제 데이터를 나타내는 Map
     */
    Map<LocalDate, List<PaymentDTO>> get1YearPayment();
}
