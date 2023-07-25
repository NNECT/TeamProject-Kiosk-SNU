package com.KioskSNU.view.admin;

import com.KioskSNU.snu.dto.*;
import com.KioskSNU.snu.service.*;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequiredArgsConstructor
public class AdminSeatController {
    private final ConcurrentHashMap<Integer, UsageSeatDTO> seatMap;
    private final ConcurrentHashMap<Integer, UsageRoomDTO> roomMap;
    private final UsageSeatService usageSeatService;
    private final UsageRoomService usageRoomService;
    private final AccountService accountService;
    private final SeatService seatService;
    private final RoomService roomService;

    @RequestMapping("/admin/adminseat")
    public ModelAndView process(String type, Integer number) {
        ModelAndView mav = new ModelAndView();

        Map<Integer, Integer> seatStatusMap = usageSeatService.getSeatStatusMap();
        mav.addObject("seatStatusMap", new Gson().toJson(seatStatusMap));

        Map<Integer, Integer> roomStatusMap = usageRoomService.getRoomStatusMap();
        mav.addObject("roomStatusMap", new Gson().toJson(roomStatusMap));

        //메인에서 좌석관리 눌러서 들어오는 경우
        if (type == null || number == null) {
            mav.setViewName("admin/admin_seat");
            return mav;
        }

        //좌석관리에서 좌석번호 눌러서 들어오는 경우
        else if (type.equals("seat")) {
            mav.setViewName("admin/admin_seat");
            //usage에서 좌석 사용정보 가져오기
            UsageSeatDTO usageSeatDTO = seatMap.get(number);
            SeatDTO seatDTO = seatService.getBySeatNumber(number);

            //해당 좌석의 사용가능 여부
            if (!seatDTO.isUsable()) {
                mav.addObject("usable", "사용불가능");
                mav.addObject("number",number);
                mav.addObject("type",type);
                return mav;
            }

            //사용가능 좌석, 사용중 분기처리
            if (usageSeatDTO == null) {
                mav.addObject("usable", "사용가능");
                mav.addObject("number",number);
                mav.addObject("type",type);
                return mav;
            }

            //현재 로그인중인 고객이 있는 경우
            if (usageSeatDTO.getAccount_id() == 0) {
                mav.addObject("accountID", "현재 로그인중인 고객이 있습니다. 로그인이 완료되면 설정해주세요.");
                return mav;
            }

            //사용중 좌석이라면
            mav.addObject("usable", "사용중");
            // 로그인이 완료된 사용자인 경우
            AccountDTO getUser = accountService.getById(usageSeatDTO.getAccount_id());
            //좌석사용자의 accountid로 가져온 getUser 정보의 username, 입실시간 가져오기
            mav.addObject("accountID", getUser.getUsername());
            mav.addObject("startDateTime", usageSeatDTO.getStartDateTime());
            mav.addObject("number",number);
            mav.addObject("type",type);
            return mav;

        } else if (type.equals("room")) {
            mav.setViewName("admin/admin_seat");
            //usage에서 좌석 사용정보 가져오기
            
            UsageRoomDTO usageRoomDTO = roomMap.get(number);
            RoomDTO roomDTO = roomService.getByRoomNumber(number);

            //해당 좌석의 사용가능 여부
            if (!roomDTO.isUsable()) {
                mav.addObject("usable", "사용불가능");
                mav.addObject("number",number);
                mav.addObject("type",type);
                return mav;
            }

            //사용가능 좌석, 사용중 분기처리
            if (usageRoomDTO == null) {
                mav.addObject("usable", "사용가능");
                mav.addObject("number",number);
                mav.addObject("type",type);
                return mav;
            }

            //현재 로그인중인 고객이 있는 경우
            if (usageRoomDTO.getAccount_id() == 0) {
                mav.addObject("accountID", "현재 로그인중인 고객이 있습니다. 로그인이 완료되면 설정해주세요.");
                return mav;
            }

            //사용중 좌석이라면
            mav.addObject("usable", "사용중");
            // 로그인이 완료된 사용자인 경우
            AccountDTO getUser = accountService.getById(usageRoomDTO.getAccount_id());
            //좌석사용자의 accountid로 가져온 getUser 정보의 username, 입실시간 가져오기
            mav.addObject("accountID", getUser.getUsername());
            mav.addObject("startDateTime", usageRoomDTO.getStartDateTime());
            mav.addObject("number",number);
            mav.addObject("type",type);
            return mav;
        }

        return mav;


    }
}
