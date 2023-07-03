package com.KioskSNU.snu.dto;

import java.time.LocalDateTime;

public class PaymentDTO {
    private int id;
    private int account_id;
    private String account_username;
    private String account_password;
    private String account_phoneNumber;
    private int account_point;
    private int account_remainTime;
    private LocalDateTime dateTime;
    private int amount;
    private int usedPoint;
    private String log;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUsedPoint() {
        return usedPoint;
    }

    public void setUsedPoint(int usedPoint) {
        this.usedPoint = usedPoint;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
