package com.KioskSNU.view.inside;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class InsideIndexController {
    private final Map<Integer, UsageSeatDTO> seatMap;
    private final Map<Integer, UsageRoomDTO> roomMap;
    private final AccountService accountService;

    @Autowired
    public InsideIndexController(
            ConcurrentHashMap<Integer, UsageSeatDTO> seatMap,
            ConcurrentHashMap<Integer, UsageRoomDTO> roomMap,
            AccountService accountService
    ) {
        this.seatMap = seatMap;
        this.roomMap = roomMap;
        this.accountService = accountService;
    }

    @RequestMapping("/inside/index")
    public ModelAndView process(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        // 자리 등록이 안되어있으면 자리 등록 페이지로 이동
        if (session.getAttribute("insideType") == null || session.getAttribute("insideNumber") == null) {
            modelAndView.setViewName("redirect:/inside");
            return modelAndView;
        }

        //테스트코드
//        session.setAttribute("author", accountService.getById(1));
//        UsageSeatDTO usageDTO = new UsageSeatDTO();
//        usageDTO.setAccount_id(1);
//        usageDTO.setSeat_id((Integer) session.getAttribute("insideNumber"));
//        usageDTO.setStartDateTime(LocalDateTime.now());
//        seatMap.put(usageDTO.getSeat_id(), usageDTO);

        modelAndView.setViewName("inside/index");
        return modelAndView;
    }

    @RequestMapping("/inside/start")
    public ModelAndView start(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        switch ((String) session.getAttribute("insideType")) {
            case "seat": {
                // 사용자가 등록되지 않았거나 사용 시작이 되지 않았으면 인덱스 페이지에서 대기
                if (!seatMap.containsKey((Integer) session.getAttribute("insideNumber"))
                        || seatMap.get((Integer) session.getAttribute("insideNumber")).getStartDateTime() == null) {
                    modelAndView.setViewName("redirect:/inside/index");
                    return modelAndView;
                }

                // 사용자가 등록되어있는데, 사용자 정보가 없으면 인덱스 페이지에서 대기
                AccountDTO accountDTO = accountService.getById(seatMap.get((Integer) session.getAttribute("insideNumber")).getAccount_id());
                if (accountDTO == null) {
                    System.out.println(session.getAttribute("insideNumber") + "번 자리에 등록된 사용자ID " + seatMap.get((Integer) session.getAttribute("insideNumber")).getAccount_id() + "에 해당하는 사용자가 없습니다.");
                    modelAndView.setViewName("redirect:/inside/index");
                    return modelAndView;
                }

                session.setAttribute("author", accountDTO);
                break;
            }

            case "room": {
                // 사용자가 등록되지 않았거나 사용 시작이 되지 않았으면 인덱스 페이지에서 대기
                if (!roomMap.containsKey((Integer) session.getAttribute("insideNumber"))
                        || roomMap.get((Integer) session.getAttribute("insideNumber")).getStartDateTime() == null) {
                    modelAndView.setViewName("redirect:/inside/index");
                    return modelAndView;
                }

                // 사용자가 등록되어있는데, 사용자 정보가 없으면 인덱스 페이지에서 대기
                AccountDTO accountDTO = accountService.getById(roomMap.get((Integer) session.getAttribute("insideNumber")).getAccount_id());
                if (accountDTO == null) {
                    System.out.println(session.getAttribute("insideNumber") + "번 방에 등록된 사용자ID " + roomMap.get((Integer) session.getAttribute("insideNumber")).getAccount_id() + "에 해당하는 사용자가 없습니다.");
                    modelAndView.setViewName("redirect:/inside/index");
                    return modelAndView;
                }

                session.setAttribute("author", accountDTO);
                break;
            }

            default: {
                modelAndView.setViewName("redirect:/inside");
                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/inside/login");
        return modelAndView;
    }
}
