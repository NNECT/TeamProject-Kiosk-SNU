package com.KioskSNU.view.outside;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OutsideErrorController {
    @RequestMapping("/outside/error")
    public String process(){
        return "outside/outside_error";
    }
}
