package com.KioskSNU.view.outside;

import com.KioskSNU.interceptor.OutsideLoginRequired;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.dto.LockerTicketDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.service.LockerService;
import com.KioskSNU.snu.service.LockerTicketService;
import com.KioskSNU.snu.service.TicketMapService;
import com.KioskSNU.snu.service.UsageLockerService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class OutsideLockerController {
    private final TicketMapService ticketMapService;
    private final Set<Integer> lockerSet;
    private final LockerService lockerService;
    private final UsageLockerService usageLockerService;
    private final LockerTicketService lockerTicketService;

    @GetMapping("/outside/locker")
    @OutsideLoginRequired
    public ModelAndView getProcess(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        // 사용자 확인
        AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");

        // 사용자 사물함 보유 여부 확인
        boolean hasLocker = usageLockerService.hasLocker(accountDTO);
        mav.addObject("hasLocker", hasLocker);

        // 사물함 상태 확인
        Map<Integer, Integer> lockerStatusMap = usageLockerService.getLockerStatusMap(accountDTO);
        mav.addObject("lockerStatusMap", new Gson().toJson(lockerStatusMap));

        // 사물함 사용권 목록 확인
        mav.addObject("lockerTicketList", lockerTicketService.getAllByActive(true));

        mav.setViewName("outside/lockerTicket");
        return mav;
    }

    @PostMapping("/outside/locker")
    @OutsideLoginRequired
    public ModelAndView postProcess(
            @RequestParam("radio-button") int ticket,
            @RequestParam(value = "locker-radio", required = false) String lockerNumber,
            HttpSession session
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/outside/payment");

        // 사용자 확인
        AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");
        if (accountDTO == null) {
            mav.setViewName("redirect:/outside/logout");
            return mav;
        }

        // 사용권 선택 여부 확인
        if (ticket == 0) return mav;

        // 사용자 사물함 보유 여부 확인
        UsageLockerDTO usageLockerDTO = usageLockerService.getLocker(accountDTO);

        // 사물함 선택 여부 확인
        if (usageLockerDTO == null) {
            if (lockerNumber == null) {
                mav.setViewName("redirect:/outside/locker");
                return mav;
            }

            // 선택된 사물함 확인
            LockerDTO lockerDTO = lockerService.getByLockerNumber(Integer.parseInt(lockerNumber));
            if (lockerDTO == null || !lockerDTO.isUsable() || lockerSet.contains(lockerDTO.getLockerNumber())) {
                mav.setViewName("redirect:/outside/locker");
                return mav;
            }
            ticketMapService.putSelectedLocker(lockerDTO);

            // 사물함 결제중 정보 등록
            lockerSet.add(lockerDTO.getLockerNumber());

            // 사물함 정보 등록
            usageLockerDTO = new UsageLockerDTO();
            usageLockerDTO.setLockerDTO(lockerDTO);
        }

        // 선택된 사용권 확인
        LockerTicketDTO lockerTicketDTO = lockerTicketService.getById(ticket);
        if (lockerTicketDTO == null) {
            mav.setViewName("redirect:/outside/locker");
            return mav;
        }
        ticketMapService.putLockerTicket(lockerTicketDTO);

        return mav;
    }
}
