package com.KioskSNU.snu.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class NoticeDTO {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private int id;
    private String title;
    private String content;
    private LocalDateTime dateTime;
    private boolean outside;
    private boolean active;

    public String getDateTimeString() {
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
