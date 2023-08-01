package com.KioskSNU.view.inside;

import com.KioskSNU.snu.dto.NoticeDTO;
import com.KioskSNU.snu.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class InsideNoticeListController {
    private final  NoticeService noticeService;

    //사용자 공지목
    @GetMapping("/inside/notice")
    public ModelAndView getNoticeList(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("inside/inside_noticeList");

        try {
            List<NoticeDTO> noticeList = noticeService.getAll();
            mav.addObject("noticeList",noticeList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }

    //공지검색
    @GetMapping("/inside/insideNotice")
    public ModelAndView getNotice(NoticeDTO noticeDTO){
        ModelAndView mav = new ModelAndView();
        mav.addObject("notice",noticeService.getById(noticeDTO.getId()));
        mav.setViewName("inside/inside_notice");
        return mav;
    }

}
