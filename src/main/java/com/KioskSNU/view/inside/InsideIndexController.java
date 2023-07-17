package com.KioskSNU.view.inside;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InsideIndexController {
    @RequestMapping("/inside/index")
    public ModelAndView process() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("inside/index");
        return modelAndView;
    }
}
