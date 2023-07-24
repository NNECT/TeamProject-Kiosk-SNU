package com.KioskSNU.snu.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
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
    private LocalDateTime endDateTime;
    private int goalDay;
    private int goalHour;
    private int rewardPoint;
    private boolean active;
    private boolean result;
}
