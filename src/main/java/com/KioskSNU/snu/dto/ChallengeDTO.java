package com.KioskSNU.snu.dto;

import lombok.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class ChallengeDTO {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

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

    public String getActiveStartTimeString() {
        return activeStartTime.format(TIME_FORMATTER);
    }

    public String getActiveEndTimeString() {
        return activeEndTime.format(TIME_FORMATTER);
    }
}
