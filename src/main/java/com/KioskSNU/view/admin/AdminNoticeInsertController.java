package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dto.NoticeDTO;
import com.KioskSNU.snu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class AdminNoticeInsertController {

    private final NoticeService noticeService;

    @Autowired
    public AdminNoticeInsertController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/admin/adminnotificationwrite")
    @AdminLoginRequired
    public ModelAndView insertNoticeForm(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/admin_notificationwrite");
        return mav;
    }

    @PostMapping("/admin/adminnotificationwrite")
    @AdminLoginRequired
    public ModelAndView insertNotice(NoticeDTO noticeDTO){

        ModelAndView mav = new ModelAndView();
        noticeDTO.setDateTime(LocalDateTime.now());
        noticeService.insert(noticeDTO);

        mav.setViewName("redirect:/admin/adminnotification");
        return mav;

    }

}
