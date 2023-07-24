package com.KioskSNU.snu.dto;

import lombok.*;

@Data
public class CommutationTicketDTO {
    private int id;
    private int day;
    private int price;
    private boolean active;
}
