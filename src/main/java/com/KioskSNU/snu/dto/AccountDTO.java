package com.KioskSNU.snu.dto;

import lombok.*;

@Data
public class AccountDTO {
    private int id;
    private String username;
    private String password;
    private String phoneNumber;
    private int point;
    private int remainTime;
}
