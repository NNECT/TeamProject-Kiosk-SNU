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

    public void setAccountDTO(AccountDTO accountDTO) {
        this.account_id = accountDTO.getId();
        this.account_username = accountDTO.getUsername();
        this.account_password = accountDTO.getPassword();
        this.account_phoneNumber = accountDTO.getPhoneNumber();
        this.account_point = accountDTO.getPoint();
        this.account_remainTime = accountDTO.getRemainTime();
    }

    public void setChallengeDTO(ChallengeDTO challengeDTO) {
        this.challenge_id = challengeDTO.getId();
        this.challenge_title = challengeDTO.getTitle();
        this.challenge_description = challengeDTO.getDescription();
        this.challenge_titleColor = challengeDTO.getTitleColor();
        this.challenge_descriptionColor = challengeDTO.getDescriptionColor();
        this.challenge_backgroundColor = challengeDTO.getBackgroundColor();
        this.challenge_imageSrc = challengeDTO.getImageSrc();
        this.challenge_activeStartTime = challengeDTO.getActiveStartTime();
        this.challenge_activeEndTime = challengeDTO.getActiveEndTime();
        this.challenge_periodHours = challengeDTO.getPeriodHours();
        this.challenge_periodDays = challengeDTO.getPeriodDays();
        this.challenge_goalDay = challengeDTO.getGoalDay();
        this.challenge_goalHour = challengeDTO.getGoalHour();
        this.challenge_rewardPoint = challengeDTO.getRewardPoint();
        this.challenge_active = challengeDTO.isActive();
        this.challenge_visible = challengeDTO.isVisible();
    }
}
