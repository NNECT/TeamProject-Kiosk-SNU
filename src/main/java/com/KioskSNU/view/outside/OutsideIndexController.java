package com.KioskSNU.view.outside;

import com.KioskSNU.snu.dto.NoticeDTO;
import com.KioskSNU.snu.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OutsideIndexController {

    private final NoticeService noticeService;

    @RequestMapping("/outside")
    public ModelAndView process(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/index");

        //공지사항(outside가 true인 경우)
        List<NoticeDTO> outsideNotice = noticeService.getAllByOutside(true);
        if (!outsideNotice.isEmpty()) {
            mav.addObject("outsideNotice", outsideNotice.get(0));
        }

        if (session.getAttribute("selectType") != null) {
            mav.setViewName("redirect:/outside/logout");
        }
        return mav;
    }
}
