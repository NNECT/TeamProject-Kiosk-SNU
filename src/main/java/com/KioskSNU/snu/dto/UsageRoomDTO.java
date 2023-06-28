package com.KioskSNU.snu.dto;

import java.time.LocalDateTime;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getRoom_roomNumber() {
        return room_roomNumber;
    }

    public void setRoom_roomNumber(int room_roomNumber) {
        this.room_roomNumber = room_roomNumber;
    }

    public int getRoom_roomType_id() {
        return room_roomType_id;
    }

    public void setRoom_roomType_id(int room_roomType_id) {
        this.room_roomType_id = room_roomType_id;
    }

    public String getRoom_roomType_name() {
        return room_roomType_name;
    }

    public void setRoom_roomType_name(String room_roomType_name) {
        this.room_roomType_name = room_roomType_name;
    }

    public int getRoom_roomType_price() {
        return room_roomType_price;
    }

    public void setRoom_roomType_price(int room_roomType_price) {
        this.room_roomType_price = room_roomType_price;
    }

    public boolean isRoom_usable() {
        return room_usable;
    }

    public void setRoom_usable(boolean room_usable) {
        this.room_usable = room_usable;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getAccount_username() {
        return account_username;
    }

    public void setAccount_username(String account_username) {
        this.account_username = account_username;
    }

    public String getAccount_password() {
        return account_password;
    }

    public void setAccount_password(String account_password) {
        this.account_password = account_password;
    }

    public String getAccount_phoneNumber() {
        return account_phoneNumber;
    }

    public void setAccount_phoneNumber(String account_phoneNumber) {
        this.account_phoneNumber = account_phoneNumber;
    }

    public int getAccount_point() {
        return account_point;
    }

    public void setAccount_point(int account_point) {
        this.account_point = account_point;
    }

    public int getAccount_remainTime() {
        return account_remainTime;
    }

    public void setAccount_remainTime(int account_remainTime) {
        this.account_remainTime = account_remainTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
