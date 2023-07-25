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

    public void setSeatDTO(SeatDTO seatDTO) {
        this.seat_id = seatDTO.getId();
        this.seat_seatNumber = seatDTO.getSeatNumber();
        this.seat_usable = seatDTO.isUsable();
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
