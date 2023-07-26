package com.KioskSNU.snu.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class UsageCommutationTicketDTO {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private int id;
    private int account_id;
    private String account_username;
    private String account_password;
    private String account_phoneNumber;
    private int account_point;
    private int account_remainTime;
    private LocalDate startDate;
    private LocalDate endDate;

    public void setAccountDTO(AccountDTO accountDTO) {
        this.account_id = accountDTO.getId();
        this.account_username = accountDTO.getUsername();
        this.account_password = accountDTO.getPassword();
        this.account_phoneNumber = accountDTO.getPhoneNumber();
        this.account_point = accountDTO.getPoint();
        this.account_remainTime = accountDTO.getRemainTime();
    }

    public String getStartDateString() {
        return startDate.format(DATE_FORMATTER);
    }

    public String getEndDateString() {
        return endDate.format(DATE_FORMATTER);
    }
}
