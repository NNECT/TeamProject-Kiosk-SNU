package com.KioskSNU.snu.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class UsageSeatDTO {
    private int id;
    private int seat_id;
    private int seat_seatNumber;
    private boolean seat_usable;
    private int account_id;
    private String account_username;
    private String account_password;
    private String account_phoneNumber;
    private int account_point;
    private int account_remainTime;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
