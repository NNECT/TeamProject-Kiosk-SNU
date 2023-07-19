package com.KioskSNU.view.inside;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import com.KioskSNU.snu.service.UsageRoomService;
import com.KioskSNU.snu.service.UsageSeatService;
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
public class InsideLogoutController {
    private final Map<Integer, UsageSeatDTO> seatMap;
    private final Map<Integer, UsageRoomDTO> roomMap;
    AccountService accountService;
    UsageSeatService usageSeatService;
    UsageRoomService usageRoomService;
    UsageCommutationTicketService usageCommutationTicketService;

    @Autowired
    public InsideLogoutController(
            ConcurrentHashMap<Integer, UsageSeatDTO> seatMap,
            ConcurrentHashMap<Integer, UsageRoomDTO> roomMap,
            AccountService accountService,
            UsageSeatService usageSeatService,
            UsageRoomService usageRoomService,
            UsageCommutationTicketService usageCommutationTicketService
    ) {
        this.seatMap = seatMap;
        this.roomMap = roomMap;
        this.accountService = accountService;
        this.usageSeatService = usageSeatService;
        this.usageRoomService = usageRoomService;
        this.usageCommutationTicketService = usageCommutationTicketService;
    }

    @RequestMapping("/inside/logout")
    public ModelAndView process(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        // 자리 등록이 안되어있으면 자리 등록 페이지로 이동
        String type = (String) session.getAttribute("insideType");
        if (type == null) {
            mav.setViewName("redirect:/inside");
            return mav;
        }

        switch (type) {
            // 자리 사용 종료
            case "seat": {
                // 자리 번호 확인
                int seatNumber = (Integer) session.getAttribute("insideNumber");
                if (!seatMap.containsKey(seatNumber)) {
                    mav.setViewName("redirect:/inside/index");
                    return mav;
                }

                // 자리 사용 종료
                UsageSeatDTO usageSeatDTO = seatMap.get(seatNumber);
                seatMap.remove(seatNumber);

                // 자리 사용 기록 저장
                usageSeatDTO.setEndDateTime(LocalDateTime.now());
                if (usageSeatDTO.getId() == 0) usageSeatService.insert(usageSeatDTO);
                else usageSeatService.update(usageSeatDTO);

                // 정기권 처리
                AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");
                List<UsageCommutationTicketDTO> commutationTicketDTOList = usageCommutationTicketService.getAllByAccount(accountDTO);
                if (
                        !commutationTicketDTOList.isEmpty()
                        && !commutationTicketDTOList.get(0).getEndDate().isBefore(LocalDate.now())
                ) break;

                // 사용 시간 차감
                int usedTime = (int) ChronoUnit.MINUTES.between(usageSeatDTO.getStartDateTime(), usageSeatDTO.getEndDateTime());
                accountDTO.setRemainTime(Math.max(accountDTO.getRemainTime() - usedTime, 0));
                break;
            }

            // 룸 사용 종료
            case "room": {
                // 룸 번호 확인
                int roomNumber = (Integer) session.getAttribute("insideNumber");
                if (!roomMap.containsKey(roomNumber)) {
                    mav.setViewName("redirect:/inside/index");
                    return mav;
                }

                // 룸 사용 종료
                UsageRoomDTO usageRoomDTO = roomMap.get(roomNumber);
                roomMap.remove(roomNumber);

                // 룸 사용 기록 저장
                usageRoomDTO.setEndDateTime(LocalDateTime.now());
                if (usageRoomDTO.getId() == 0) usageRoomService.insert(usageRoomDTO);
                else usageRoomService.update(usageRoomDTO);
                break;
            }

            default: {
                mav.setViewName("redirect:/inside");
                return mav;
            }
        }

        mav.setViewName("inside/logout");
        return mav;
    }
}
