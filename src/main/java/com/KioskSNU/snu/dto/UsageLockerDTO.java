package com.KioskSNU.snu.dto;

import java.time.LocalDate;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocker_id() {
        return locker_id;
    }

    public void setLocker_id(int locker_id) {
        this.locker_id = locker_id;
    }

    public int getLocker_lockerNumber() {
        return locker_lockerNumber;
    }

    public void setLocker_lockerNumber(int locker_lockerNumber) {
        this.locker_lockerNumber = locker_lockerNumber;
    }

    public boolean isLocker_usable() {
        return locker_usable;
    }

    public void setLocker_usable(boolean locker_usable) {
        this.locker_usable = locker_usable;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
