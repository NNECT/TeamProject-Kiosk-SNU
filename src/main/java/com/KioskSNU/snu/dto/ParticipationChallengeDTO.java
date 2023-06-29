package com.KioskSNU.snu.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ParticipationChallengeDTO {
    private int id;
    private int account_id;
    private String account_username;
    private String account_password;
    private String account_phoneNumber;
    private int account_point;
    private int account_remainTime;
    private int challenge_id;
    private String challenge_title;
    private String challenge_description;
    private String challenge_titleColor;
    private String challenge_descriptionColor;
    private String challenge_backgroundColor;
    private String challenge_imageSrc;
    private LocalTime challenge_activeStartTime;
    private LocalTime challenge_activeEndTime;
    private int challenge_periodHours;
    private int challenge_periodDays;
    private int challenge_goalDay;
    private int challenge_goalHour;
    private int challenge_rewardPoint;
    private boolean challenge_active;
    private boolean challenge_visible;
    private LocalDateTime startDateTime;
    private int periodHours;
    private int periodDays;
    private int goalDay;
    private int goalHour;
    private int rewardPoint;
    private boolean active;
    private boolean result;

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

    public int getChallenge_id() {
        return challenge_id;
    }

    public void setChallenge_id(int challenge_id) {
        this.challenge_id = challenge_id;
    }

    public String getChallenge_title() {
        return challenge_title;
    }

    public void setChallenge_title(String challenge_title) {
        this.challenge_title = challenge_title;
    }

    public String getChallenge_description() {
        return challenge_description;
    }

    public void setChallenge_description(String challenge_description) {
        this.challenge_description = challenge_description;
    }

    public String getChallenge_titleColor() {
        return challenge_titleColor;
    }

    public void setChallenge_titleColor(String challenge_titleColor) {
        this.challenge_titleColor = challenge_titleColor;
    }

    public String getChallenge_descriptionColor() {
        return challenge_descriptionColor;
    }

    public void setChallenge_descriptionColor(String challenge_descriptionColor) {
        this.challenge_descriptionColor = challenge_descriptionColor;
    }

    public String getChallenge_backgroundColor() {
        return challenge_backgroundColor;
    }

    public void setChallenge_backgroundColor(String challenge_backgroundColor) {
        this.challenge_backgroundColor = challenge_backgroundColor;
    }

    public String getChallenge_imageSrc() {
        return challenge_imageSrc;
    }

    public void setChallenge_imageSrc(String challenge_imageSrc) {
        this.challenge_imageSrc = challenge_imageSrc;
    }

    public LocalTime getChallenge_activeStartTime() {
        return challenge_activeStartTime;
    }

    public void setChallenge_activeStartTime(LocalTime challenge_activeStartTime) {
        this.challenge_activeStartTime = challenge_activeStartTime;
    }

    public LocalTime getChallenge_activeEndTime() {
        return challenge_activeEndTime;
    }

    public void setChallenge_activeEndTime(LocalTime challenge_activeEndTime) {
        this.challenge_activeEndTime = challenge_activeEndTime;
    }

    public int getChallenge_periodHours() {
        return challenge_periodHours;
    }

    public void setChallenge_periodHours(int challenge_periodHours) {
        this.challenge_periodHours = challenge_periodHours;
    }

    public int getChallenge_periodDays() {
        return challenge_periodDays;
    }

    public void setChallenge_periodDays(int challenge_periodDays) {
        this.challenge_periodDays = challenge_periodDays;
    }

    public int getChallenge_goalDay() {
        return challenge_goalDay;
    }

    public void setChallenge_goalDay(int challenge_goalDay) {
        this.challenge_goalDay = challenge_goalDay;
    }

    public int getChallenge_goalHour() {
        return challenge_goalHour;
    }

    public void setChallenge_goalHour(int challenge_goalHour) {
        this.challenge_goalHour = challenge_goalHour;
    }

    public int getChallenge_rewardPoint() {
        return challenge_rewardPoint;
    }

    public void setChallenge_rewardPoint(int challenge_rewardPoint) {
        this.challenge_rewardPoint = challenge_rewardPoint;
    }

    public boolean isChallenge_active() {
        return challenge_active;
    }

    public void setChallenge_active(boolean challenge_active) {
        this.challenge_active = challenge_active;
    }

    public boolean isChallenge_visible() {
        return challenge_visible;
    }

    public void setChallenge_visible(boolean challenge_visible) {
        this.challenge_visible = challenge_visible;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public int getPeriodHours() {
        return periodHours;
    }

    public void setPeriodHours(int periodHours) {
        this.periodHours = periodHours;
    }

    public int getPeriodDays() {
        return periodDays;
    }

    public void setPeriodDays(int periodDays) {
        this.periodDays = periodDays;
    }

    public int getGoalDay() {
        return goalDay;
    }

    public void setGoalDay(int goalDay) {
        this.goalDay = goalDay;
    }

    public int getGoalHour() {
        return goalHour;
    }

    public void setGoalHour(int goalHour) {
        this.goalHour = goalHour;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
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
