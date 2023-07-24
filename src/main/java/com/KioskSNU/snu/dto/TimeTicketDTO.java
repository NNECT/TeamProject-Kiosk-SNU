package com.KioskSNU.snu.dto;

import lombok.*;

@Data
public class TimeTicketDTO {
    private int id;
    private int time;
    private int price;
    private boolean active;
}
