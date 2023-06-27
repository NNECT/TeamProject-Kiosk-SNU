package com.KioskSNU.snu.dto;

import java.time.LocalDate;

public class UsageLockerDTO {
    private int id;
    private LockerDTO locker;
    private AccountDTO account;
    private LocalDate startDate;
    private LocalDate endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LockerDTO getLocker() {
        return locker;
    }

    public void setLocker(LockerDTO locker) {
        this.locker = locker;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
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
