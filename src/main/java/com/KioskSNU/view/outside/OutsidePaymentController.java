package com.KioskSNU.view.outside;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class OutsidePaymentController {
    public ModelAndView process() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/payment");
        return mav;
    }
}
