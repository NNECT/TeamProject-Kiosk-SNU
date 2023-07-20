package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dto.NoticeDTO;
import com.KioskSNU.snu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView updateNotice(@RequestParam(name = "outside", required = false) String outside, NoticeDTO noticeDTO){
        ModelAndView mav = new ModelAndView();

        if ("on".equals(outside)) {
            // 체크박스가 체크되었을 때
            noticeDTO.setOutside(true);
        } else {
            // 체크박스가 체크되지 않았을 때
            noticeDTO.setOutside(false);
        }
        noticeDTO.setDateTime(LocalDateTime.now());
        noticeService.update(noticeDTO);

        mav.setViewName("redirect:/admin/adminnotification");
        return mav;
    }

}
