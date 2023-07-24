package com.KioskSNU.snu.dto;

import lombok.*;

import java.time.LocalTime;

@Data
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
}
