package com.KioskSNU.snu.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class NoticeDTO {
    private int id;
    private String title;
    private String content;
    private LocalDateTime dateTime;
    private boolean outside;
    private boolean active;
}
