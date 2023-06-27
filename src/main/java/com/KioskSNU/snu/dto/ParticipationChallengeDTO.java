package com.KioskSNU.snu.dto;

import java.time.LocalDate;

public class ParticipationChallengeDTO {
    private int id;
    private AccountDTO account;
    private ChallengeDTO challenge;
    private LocalDate startDate;
    private boolean active;
    private boolean result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public ChallengeDTO getChallenge() {
        return challenge;
    }

    public void setChallenge(ChallengeDTO challenge) {
        this.challenge = challenge;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
