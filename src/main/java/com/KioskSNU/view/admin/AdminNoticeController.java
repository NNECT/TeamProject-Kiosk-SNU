package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dto.NoticeDTO;
import com.KioskSNU.snu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //공지 등록
    @GetMapping("/admin/adminnotificationwrite")
    @AdminLoginRequired
    public ModelAndView insertNoticeForm(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/admin_notificationwrite");
        return mav;
    }

    @PostMapping("/admin/adminnotificationwrite")
    @AdminLoginRequired
    public ModelAndView insertNotice(@RequestParam(name = "outside", required = false) String outside, NoticeDTO noticeDTO){

        ModelAndView mav = new ModelAndView();

        if ("on".equals(outside)) {
            // 체크박스가 체크되었을 때
            noticeDTO.setOutside(true);
        } else {
            // 체크박스가 체크되지 않았을 때
            noticeDTO.setOutside(false);
        }

        noticeDTO.setDateTime(LocalDateTime.now());
        noticeService.insert(noticeDTO);

        mav.setViewName("redirect:/admin/adminnotification");
        return mav;

    }

    //공지 수정
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
