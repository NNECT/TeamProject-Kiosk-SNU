package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.PaymentDTO;
import com.KioskSNU.snu.mapper.PaymentMapper;
import com.KioskSNU.snu.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Qualifier("paymentDAO")
    private final PaymentMapper paymentDAO;

    @Override
    public int insert(PaymentDTO paymentDTO) {
        return paymentDAO.insert(paymentDTO);
    }

    @Override
    public int update(PaymentDTO paymentDTO) {
        return paymentDAO.update(paymentDTO);
    }

    @Override
    public int delete(PaymentDTO paymentDTO) {
        return paymentDAO.delete(paymentDTO);
    }

    @Override
    public PaymentDTO getById(int id) {
        return paymentDAO.getById(id);
    }

    @Override
    public List<PaymentDTO> getAll() {
        return paymentDAO.getAll();
    }

    @Override
    public List<PaymentDTO> getAllByAccount(AccountDTO accountDTO) {
        return paymentDAO.getAllByAccount(accountDTO);
    }

    @Override
    public List<PaymentDTO> getAllBy1Year() {
        return paymentDAO.getAllBy1Year();
    }

    @Override
    public Map<LocalDate, List<PaymentDTO>> get1YearPayment() {
        LinkedHashMap<LocalDate, List<PaymentDTO>> datesMap = new LinkedHashMap<>();
        LocalDate ago1Year = LocalDate.now().minusYears(1).withDayOfMonth(1);

        // datesMap 1년치 초기화
        for (
                LocalDate date = ago1Year;
                !date.isAfter(LocalDate.now());
                date = date.plusMonths(1)
        ) {
            datesMap.put(date, new ArrayList<>());
        }

        // 1년치 데이터 가져오기
        List<PaymentDTO> paymentDTOList = getAllBy1Year();
        if (paymentDTOList == null || paymentDTOList.isEmpty()) return datesMap;

        // datesMap에 데이터 넣기
        paymentDTOList.forEach(paymentDTO -> {
            LocalDate date = LocalDate.from(paymentDTO.getDateTime()).withDayOfMonth(1);
            datesMap.get(date).add(paymentDTO);
        });

        return datesMap;
    }
}
