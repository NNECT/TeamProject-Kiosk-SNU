package com.KioskSNU.snu.dto;

import java.time.LocalDateTime;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public int getSeat_seatNumber() {
        return seat_seatNumber;
    }

    public void setSeat_seatNumber(int seat_seatNumber) {
        this.seat_seatNumber = seat_seatNumber;
    }

    public boolean isSeat_usable() {
        return seat_usable;
    }

    public void setSeat_usable(boolean seat_usable) {
        this.seat_usable = seat_usable;
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
