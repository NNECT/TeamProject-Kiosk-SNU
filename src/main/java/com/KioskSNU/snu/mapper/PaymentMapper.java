package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {
    int insert(PaymentDTO paymentDTO);
    int update(PaymentDTO paymentDTO);
    int delete(PaymentDTO paymentDTO);
    PaymentDTO getById(int id);
    List<PaymentDTO> getAll();
    List<PaymentDTO> getAllByAccount(AccountDTO accountDTO);
}
