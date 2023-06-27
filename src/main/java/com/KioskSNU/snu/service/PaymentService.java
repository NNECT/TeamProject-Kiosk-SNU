package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO insert(PaymentDTO paymentDTO);
    PaymentDTO update(PaymentDTO paymentDTO);
    boolean delete(PaymentDTO paymentDTO);
    PaymentDTO getById(int id);
    List<PaymentDTO> getAll();
    List<PaymentDTO> getAllByAccount(AccountDTO accountDTO);
}
