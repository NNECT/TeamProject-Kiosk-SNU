package com.KioskSNU.view.inside;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class InsideMoveSeatController {
    private final Map<Integer, UsageSeatDTO> seatMap;
    private final Map<Integer, UsageRoomDTO> roomMap;
    AccountService accountService;
    SeatService seatService;
    RoomService roomService;
    UsageSeatService usageSeatService;
    UsageRoomService usageRoomService;
    UsageCommutationTicketService usageCommutationTicketService;



    @Autowired
    InsideMoveSeatController(
            ConcurrentHashMap<Integer, UsageSeatDTO> seatMap,
            ConcurrentHashMap<Integer, UsageRoomDTO> roomMap,
            AccountService accountService,
            SeatService seatService,
            UsageSeatService usageSeatService,
            UsageRoomService usageRoomService,
            RoomService roomService,
            UsageCommutationTicketService usageCommutationTicketService
    ) {
        this.seatMap = seatMap;
        this.roomMap = roomMap;
        this.accountService = accountService;
        this.usageSeatService = usageSeatService;
        this.usageRoomService = usageRoomService;
        this.seatService = seatService;
        this.roomService = roomService;
        this.usageCommutationTicketService = usageCommutationTicketService;

    }

    @RequestMapping("/inside/move")
    public ModelAndView getProcess(String type, Integer number, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("inside/inside_moveSeat");

        //menu에서 자리이동 눌렀을 경우 자리선택 페이지로 이동
        if(type == null || number == null){
            mav.setViewName("inside/inside_select");

            Map<Integer, Integer> seatStatusMap = new HashMap<>();
            seatService.getAll().forEach(seat -> seatStatusMap.put(seat.getSeatNumber(), seat.isUsable() ? 1 : -1));
            seatMap.forEach((id, usage) -> seatStatusMap.put(id, 0));
            mav.addObject("seatStatusMap", new Gson().toJson(seatStatusMap));

            Map<Integer, Integer> roomStatusMap = new HashMap<>();
            roomService.getAll().forEach(room -> roomStatusMap.put(room.getRoomNumber(), room.isUsable() ? 1 : -1));
            roomMap.forEach((id, usage) -> roomStatusMap.put(id, 0));
            mav.addObject("roomStatusMap", new Gson().toJson(roomStatusMap));

            return mav;
        }

        //자리이동에서 이동할 좌석 선택 이후 변경처리 진행
        if(type.equals("seat")){

            UsageSeatDTO usage = new UsageSeatDTO();
            //usage.seatId에 / 들어온 number로 조회한 좌석의 id값 넣어주기
            usage.setSeat_id(seatService.getBySeatNumber(number).getId());
            //현재 사용시간 넣어주기
            usage.setStartDateTime(LocalDateTime.now());
            //들어온 number 번호에 usage정보 넣어주기
            seatMap.put(number, usage);

        }else {
            mav.setViewName("redirect:/inside/move");
        }


        switch (type) {
            case "seat" : {
                // 자리 번호 확인
                int seatNumber = (Integer) session.getAttribute("insideNumber");
                if (!seatMap.containsKey(seatNumber)) {
                    mav.setViewName("redirect:/inside/index");
                    return mav;
                }

                //사용자 저장
                AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");

                //자리사용종료 / 로그인기록 로그아웃
                session.removeAttribute("author");
                UsageSeatDTO usageSeatDTO = seatMap.get(seatNumber);
                seatMap.remove(seatNumber);

                // 자리 사용 기록 저장
                usageSeatDTO.setEndDateTime(LocalDateTime.now());
                if (usageSeatDTO.getId() == 0) usageSeatService.insert(usageSeatDTO);
                else usageSeatService.update(usageSeatDTO);

                // 정기권 처리
                List<UsageCommutationTicketDTO> commutationTicketDTOList = usageCommutationTicketService.getAllByAccount(accountDTO);
                if (
                        !commutationTicketDTOList.isEmpty()
                                && !commutationTicketDTOList.get(0).getEndDate().isBefore(LocalDate.now())
                ) break;

                // 사용 시간 차감
                int usedTime = (int) ChronoUnit.MINUTES.between(usageSeatDTO.getStartDateTime(), usageSeatDTO.getEndDateTime());
                accountDTO.setRemainTime(Math.max(accountDTO.getRemainTime() - usedTime, 0));
                accountService.update(accountDTO);
                break;
            }
        }

        return mav;
    }


}
