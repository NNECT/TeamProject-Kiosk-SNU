package com.KioskSNU.snu.viewdto;

public class SeatStatusDTO {
    public static final String CANNOT_USE = "-1";
    public static final String USING = "0";
    public static final String CAN_USE = "1";

    private int seatNumber;
    private String status; // -1: cannot use, 0: using, 1: can use

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStatus() {
        return status;
    }

    /**
     * @param status -1: cannot use, 0: using, 1: can use
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
