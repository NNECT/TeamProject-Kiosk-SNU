package com.KioskSNU.snu.dto;

import java.time.LocalTime;

public class ChallengeDTO {
    private int id;
    private String title;
    private String description;
    private String titleColor;
    private String descriptionColor;
    private String backgroundColor;
    private String imageSrc;
    private LocalTime activeStartTime;
    private LocalTime activeEndTime;
    private int periodHours;
    private int periodDays;
    private int goalDay;
    private int goalHour;
    private int rewardPoint;
    private boolean active;
    private boolean visible;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getDescriptionColor() {
        return descriptionColor;
    }

    public void setDescriptionColor(String descriptionColor) {
        this.descriptionColor = descriptionColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public LocalTime getActiveStartTime() {
        return activeStartTime;
    }

    public void setActiveStartTime(LocalTime activeStartTime) {
        this.activeStartTime = activeStartTime;
    }

    public LocalTime getActiveEndTime() {
        return activeEndTime;
    }

    public void setActiveEndTime(LocalTime activeEndTime) {
        this.activeEndTime = activeEndTime;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
