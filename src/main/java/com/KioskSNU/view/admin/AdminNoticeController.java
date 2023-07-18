package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dto.NoticeDTO;
import com.KioskSNU.snu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class AdminNoticeController {

    private final NoticeService noticeService;

    @Autowired
    public AdminNoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    //전체 공지목록
    @GetMapping("/admin/adminnotification")
    @AdminLoginRequired
    public ModelAndView getNoticeList(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("noticeList",noticeService.getAll());
        mav.setViewName("admin/admin_notification");
        return mav;
    }

    //특정 공지
    @GetMapping("/admin/adminnotificationedit")
    @AdminLoginRequired
    public ModelAndView getNotice(NoticeDTO noticeDTO){
        ModelAndView mav = new ModelAndView();
        mav.addObject("notice",noticeService.getById(noticeDTO.getId()));
        mav.setViewName("admin/admin_notificationedit");
        return mav;
    }

}
