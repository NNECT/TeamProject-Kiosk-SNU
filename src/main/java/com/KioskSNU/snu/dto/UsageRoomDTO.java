package com.KioskSNU.snu.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class UsageRoomDTO {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private int id;
    private int room_id;
    private int room_roomNumber;
    private int room_roomType_id;
    private String room_roomType_name;
    private int room_roomType_price;
    private boolean room_usable;
    private int account_id;
    private String account_username;
    private String account_password;
    private String account_phoneNumber;
    private int account_point;
    private int account_remainTime;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public void setRoomDTO(RoomDTO roomDTO) {
        this.room_id = roomDTO.getId();
        this.room_roomNumber = roomDTO.getRoomNumber();
        this.room_roomType_id = roomDTO.getRoomType_id();
        this.room_roomType_name = roomDTO.getRoomType_name();
        this.room_roomType_price = roomDTO.getRoomType_price();
        this.room_usable = roomDTO.isUsable();
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.account_id = accountDTO.getId();
        this.account_username = accountDTO.getUsername();
        this.account_password = accountDTO.getPassword();
        this.account_phoneNumber = accountDTO.getPhoneNumber();
        this.account_point = accountDTO.getPoint();
        this.account_remainTime = accountDTO.getRemainTime();
    }

    public String getStartDateTimeString() {
        return startDateTime.format(DATE_TIME_FORMATTER);
    }

    public String getEndDateTimeString() {
        return endDateTime.format(DATE_TIME_FORMATTER);
    }
}
