package com.KioskSNU.view.inside;

import com.KioskSNU.api.RoomStatus;
import com.KioskSNU.api.SeatStatus;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.SeatService;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import com.KioskSNU.snu.service.UsageSeatService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class InsideMoveSeatController {
    private final Map<Integer, UsageSeatDTO> seatMap;
    private final SeatStatus seatStatus;
    private final RoomStatus roomStatus;
    private final AccountService accountService;
    private final SeatService seatService;
    private final UsageSeatService usageSeatService;
    private final UsageCommutationTicketService usageCommutationTicketService;


    @Autowired
    InsideMoveSeatController(
            ConcurrentHashMap<Integer, UsageSeatDTO> seatMap,
            SeatStatus seatStatus,
            RoomStatus roomStatus,
            AccountService accountService,
            SeatService seatService,
            UsageSeatService usageSeatService,
            UsageCommutationTicketService usageCommutationTicketService
    ) {
        this.seatMap = seatMap;
        this.seatStatus = seatStatus;
        this.roomStatus = roomStatus;
        this.accountService = accountService;
        this.usageSeatService = usageSeatService;
        this.seatService = seatService;
        this.usageCommutationTicketService = usageCommutationTicketService;

    }

    @RequestMapping("/inside/move")
    public ModelAndView getProcess(String type, Integer number, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("inside/inside_moveSeat");

        //사용자 저장
        AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");

        //menu에서 자리이동 눌렀을 경우 자리선택 페이지로 이동
        if (type == null || number == null) {
            mav.setViewName("inside/inside_select");

            Map<Integer, Integer> seatStatusMap = seatStatus.getSeatStatusMap(accountDTO);
            mav.addObject("seatStatusMap", new Gson().toJson(seatStatusMap));

            Map<Integer, Integer> roomStatusMap = roomStatus.getRoomStatusMap(accountDTO);
            mav.addObject("roomStatusMap", new Gson().toJson(roomStatusMap));

            return mav;
        }

        if (!type.equals("seat")) {
            mav.setViewName("redirect:/inside/move");
            return mav;
        }

        //자리이동에서 이동할 좌석 선택 이후 변경처리 진행
        UsageSeatDTO usage = new UsageSeatDTO();
        //usage.seatId에 / 들어온 number로 조회한 좌석의 id값 넣어주기
        usage.setSeat_id(seatService.getBySeatNumber(number).getId());
        //usage.accountID 넣어주기
        usage.setAccount_id(accountDTO.getId());
        //현재 사용시간 넣어주기
        usage.setStartDateTime(LocalDateTime.now());
        //들어온 number 번호에 usage정보 넣어주기
        seatMap.put(number, usage);

        // 자리 번호 확인
        int seatNumber = (Integer) session.getAttribute("insideNumber");
        if (!seatMap.containsKey(seatNumber)) {
            mav.setViewName("redirect:/inside/index");
            return mav;
        }

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
        ) return mav;

        // 사용 시간 차감
        int usedTime = (int) ChronoUnit.MINUTES.between(usageSeatDTO.getStartDateTime(), usageSeatDTO.getEndDateTime());
        accountDTO.setRemainTime(Math.max(accountDTO.getRemainTime() - usedTime, 0));
        accountService.update(accountDTO);


        return mav;
    }


}
