package com.KioskSNU.snu.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class UsageLockerDTO {
    private int id;
    private int locker_id;
    private int locker_lockerNumber;
    private boolean locker_usable;
    private int account_id;
    private String account_username;
    private String account_password;
    private String account_phoneNumber;
    private int account_point;
    private int account_remainTime;
    private LocalDate startDate;
    private LocalDate endDate;

    public void setLockerDTO(LockerDTO lockerDTO) {
        this.locker_id = lockerDTO.getId();
        this.locker_lockerNumber = lockerDTO.getLockerNumber();
        this.locker_usable = lockerDTO.isUsable();
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.account_id = accountDTO.getId();
        this.account_username = accountDTO.getUsername();
        this.account_password = accountDTO.getPassword();
        this.account_phoneNumber = accountDTO.getPhoneNumber();
        this.account_point = accountDTO.getPoint();
        this.account_remainTime = accountDTO.getRemainTime();
    }
}
