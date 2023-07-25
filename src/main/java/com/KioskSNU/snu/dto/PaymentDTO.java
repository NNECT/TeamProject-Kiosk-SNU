package com.KioskSNU.snu.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private int id;
    private int account_id;
    private String account_username;
    private String account_password;
    private String account_phoneNumber;
    private int account_point;
    private int account_remainTime;
    private LocalDateTime dateTime;
    private int amount;
    private int usedPoint;
    private String log;

    public void setAccountDTO(AccountDTO accountDTO) {
        this.account_id = accountDTO.getId();
        this.account_username = accountDTO.getUsername();
        this.account_password = accountDTO.getPassword();
        this.account_phoneNumber = accountDTO.getPhoneNumber();
        this.account_point = accountDTO.getPoint();
        this.account_remainTime = accountDTO.getRemainTime();
    }
}
