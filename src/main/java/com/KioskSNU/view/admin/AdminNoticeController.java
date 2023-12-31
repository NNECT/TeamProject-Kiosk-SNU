package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dto.NoticeDTO;
import com.KioskSNU.snu.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminNoticeController {
    private final NoticeService noticeService;

    //전체 공지목록
    @GetMapping("/admin/adminnotification")
    @AdminLoginRequired
    public ModelAndView getNoticeList(@RequestParam(value = "noticeRegistered", required = false) Boolean noticeRegistered){
        ModelAndView mav = new ModelAndView();
        mav.addObject("noticeList",noticeService.getAll());

        if (noticeRegistered != null && noticeRegistered) {
            mav.addObject("noticeRegistered", true);
        }

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
        List<NoticeDTO> list = noticeService.getAllByOutside(true);

        if ("on".equals(outside)) {
            // 새로운 공지가 외부공지로 선택되면 이전 외부공지를 모두 해제
            list.forEach(notice -> {
                notice.setOutside(false);
                noticeService.update(notice);
            });
            noticeDTO.setOutside(true);
        } else {
            noticeDTO.setOutside(false);
        }

        noticeDTO.setDateTime(LocalDateTime.now());
        noticeService.insert(noticeDTO);

        // 공지 등록이 완료되었다는 속성 설정
        mav.addObject("noticeRegistered", true);

        mav.setViewName("redirect:/admin/adminnotification");
        return mav;

    }

    //공지 수정
    @PostMapping("/admin/adminnotification")
    @AdminLoginRequired
    public ModelAndView updateNotice(@RequestParam(name = "outside", required = false) String outside, NoticeDTO noticeDTO){

        ModelAndView mav = new ModelAndView();
        List<NoticeDTO> list = noticeService.getAllByOutside(true);

        if ("on".equals(outside)) {
            // 새로운 공지가 외부공지로 선택되면 이전 외부공지를 모두 해제
            list.forEach(notice -> {
                notice.setOutside(false);
                noticeService.update(notice);
            });
            noticeDTO.setOutside(true);
        } else {
            noticeDTO.setOutside(false);
        }

        noticeDTO.setDateTime(LocalDateTime.now());
        noticeService.update(noticeDTO);

        mav.setViewName("redirect:/admin/adminnotification");
        return mav;
    }


}
