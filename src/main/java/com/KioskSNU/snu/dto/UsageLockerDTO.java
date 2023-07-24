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
}
