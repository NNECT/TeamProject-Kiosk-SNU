package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dto.NoticeDTO;
import com.KioskSNU.snu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class AdminNoticeUpdateController {

    private final NoticeService noticeService;

    @Autowired
    public AdminNoticeUpdateController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PostMapping("/admin/adminnotification")
    @AdminLoginRequired
    public ModelAndView updateNotice(NoticeDTO noticeDTO){
        ModelAndView mav = new ModelAndView();

        noticeDTO.setDateTime(LocalDateTime.now());
        noticeService.update(noticeDTO);

        mav.setViewName("redirect:/admin/adminnotification");
        return mav;
    }

}
