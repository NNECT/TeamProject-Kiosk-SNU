package com.KioskSNU.snu.dto;

public class SeatDTO {
    private int id;
    private boolean usable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }
}
