package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.*;
import com.KioskSNU.snu.service.RoomService;
import com.KioskSNU.snu.service.TicketMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketMapServiceImpl implements TicketMapService {
    private final HashMap<String, Object> ticketMap;
    private final RoomService roomService;

    @Override
    public void putTimeTicket(TimeTicketDTO timeTicketDTO) {
        ticketMap.put("timeTicket", timeTicketDTO);
    }

    @Override
    public TimeTicketDTO getTimeTicket() {
        return (TimeTicketDTO) ticketMap.get("timeTicket");
    }

    @Override
    public void putCommutationTicket(CommutationTicketDTO commutationTicketDTO) {
        ticketMap.put("commutationTicket", commutationTicketDTO);
    }

    @Override
    public CommutationTicketDTO getCommutationTicket() {
        return (CommutationTicketDTO) ticketMap.get("commutationTicket");
    }

    @Override
    public void putRoomTicket(int time) {
        ticketMap.put("roomTicket", time);
    }

    @Override
    public int getRoomTicket() {
        Integer roomTicket = (Integer) ticketMap.get("roomTicket");
        return roomTicket == null ? 0 : roomTicket;
    }

    @Override
    public void putSelectedLocker(LockerDTO lockerDTO) {
        ticketMap.put("selectedLocker", lockerDTO);
    }

    @Override
    public LockerDTO getSelectedLocker() {
        return (LockerDTO) ticketMap.get("selectedLocker");
    }

    @Override
    public void putLockerTicket(LockerTicketDTO lockerTicketDTO) {
        ticketMap.put("lockerTicket", lockerTicketDTO);
    }

    @Override
    public LockerTicketDTO getLockerTicket() {
        return (LockerTicketDTO) ticketMap.get("lockerTicket");
    }

    @Override
    public void setTicketModel(ModelMap modelMap, HttpSession session) {
        List<String> ticketList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();
        List<Integer> priceList = new ArrayList<>();

        ticketMap.forEach((key, value) -> {
            switch (key) {
                case "timeTicket":
                    ticketList.add("시간권");
                    TimeTicketDTO timeTicket = (TimeTicketDTO) value;
                    timeList.add(timeTicket.getTime() + "시간");
                    priceList.add(timeTicket.getPrice());
                    break;
                case "commutationTicket":
                    ticketList.add("정기권");
                    CommutationTicketDTO commutationTicket = (CommutationTicketDTO) value;
                    timeList.add(commutationTicket.getDay() + "일");
                    priceList.add(commutationTicket.getPrice());
                    break;
                case "roomTicket":
                    ticketList.add("룸");
                    timeList.add(value + "시간");
                    int roomNumber = (int) session.getAttribute("selectNumber");
                    RoomDTO roomDTO = roomService.getByRoomNumber(roomNumber);
                    priceList.add(((Integer) value) * roomDTO.getRoomType_price());
                    break;
                case "lockerTicket":
                    ticketList.add("사물함");
                    LockerTicketDTO lockerTicket = (LockerTicketDTO) value;
                    timeList.add(lockerTicket.getDay() + "일");
                    priceList.add(lockerTicket.getPrice());
                    break;
            }
        });

        modelMap.addAttribute("ticketList", ticketList);
        modelMap.addAttribute("timeList", timeList);
        modelMap.addAttribute("priceList", priceList);
        modelMap.addAttribute("totalPrice", priceList.stream().mapToInt(Integer::intValue).sum());
    }

    @Override
    public void clear() {
        ticketMap.clear();
    }
}
