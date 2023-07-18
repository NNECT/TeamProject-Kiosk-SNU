package com.KioskSNU.view.outside;

import com.KioskSNU.interceptor.OutsideLoginRequired;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageCommutationTicketDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.service.UsageCommutationTicketService;
import com.KioskSNU.snu.service.UsageLockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class OutsideHavingController {
    UsageCommutationTicketService usageCommutationTicketService;
    UsageLockerService usageLockerService;

    @Autowired
    public OutsideHavingController(
            UsageCommutationTicketService usageCommutationTicketService,
            UsageLockerService usageLockerService
    ) {
        this.usageCommutationTicketService = usageCommutationTicketService;
        this.usageLockerService = usageLockerService;
    }

    @RequestMapping("/outside/having")
    @OutsideLoginRequired
    public ModelAndView process(HttpSession session){
        ModelAndView mav = new ModelAndView();

        AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");

        List<UsageCommutationTicketDTO> commutationTicketDTOList = usageCommutationTicketService.getAllByAccount(accountDTO);
        if (!commutationTicketDTOList.isEmpty() && !commutationTicketDTOList.get(0).getEndDate().isBefore(LocalDate.now())) {
            mav.addObject("remainDays", (int) ChronoUnit.DAYS.between(LocalDate.now(), commutationTicketDTOList.get(0).getEndDate()) + 1);
        } else {
            mav.addObject("remainDays", 0);
        }

        mav.addObject("remainTime", accountDTO.getRemainTime());

        List<UsageLockerDTO> lockerDTOList = usageLockerService.getAllByAccount(accountDTO);
        if (!lockerDTOList.isEmpty() && !lockerDTOList.get(0).getEndDate().isBefore(LocalDate.now())) {
            mav.addObject("locker", lockerDTOList.get(0).getLocker_lockerNumber());
            mav.addObject("remainLocker", (int) ChronoUnit.DAYS.between(LocalDate.now(), lockerDTOList.get(0).getEndDate()) + 1);
        } else {
            mav.addObject("remainLocker", 0);
        }

        mav.setViewName("outside/havingPayment");
        return mav;
    }
}
