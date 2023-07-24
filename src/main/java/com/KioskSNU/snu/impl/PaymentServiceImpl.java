package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dao.PaymentDAO;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.PaymentDTO;
import com.KioskSNU.snu.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO;

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
}
