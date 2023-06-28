package com.KioskSNU.snu.dto;

public class RoomDTO {
    private int id;
    private int roomType_id;
    private String roomType_name;
    private int roomType_price;
    private boolean usable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomType_id() {
        return roomType_id;
    }

    public void setRoomType_id(int roomType_id) {
        this.roomType_id = roomType_id;
    }

    public String getRoomType_name() {
        return roomType_name;
    }

    public void setRoomType_name(String roomType_name) {
        this.roomType_name = roomType_name;
    }

    public int getRoomType_price() {
        return roomType_price;
    }

    public void setRoomType_price(int roomType_price) {
        this.roomType_price = roomType_price;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }
}
