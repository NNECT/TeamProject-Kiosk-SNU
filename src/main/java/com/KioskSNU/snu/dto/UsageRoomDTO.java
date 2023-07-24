package com.KioskSNU.snu.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class UsageRoomDTO {
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
}
