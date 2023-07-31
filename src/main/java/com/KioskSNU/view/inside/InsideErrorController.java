package com.KioskSNU.view.inside;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InsideErrorController {
    @RequestMapping("/inside/error")
    public String process(){
        return "inside/inside_error";
    }
}
