package com.KioskSNU.view.outside;

import com.KioskSNU.snu.dto.NoticeDTO;
import com.KioskSNU.snu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OutsideIndexController {

    private final NoticeService noticeService;

    @Autowired
    public OutsideIndexController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @RequestMapping("/outside")
    public ModelAndView process(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/index");

        //공지사항(outside가 true인 경우)
        List<NoticeDTO> outsideNotice = noticeService.getAllByOutside(true);
        mav.addObject("outsideNotice",outsideNotice);

        if (session.getAttribute("selectType") != null) {
            mav.setViewName("redirect:/outside/logout");
        }
        return mav;
    }
}
