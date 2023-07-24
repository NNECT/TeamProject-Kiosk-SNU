package com.KioskSNU.view.outside;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OutsideEndController {
    @RequestMapping("outside/end")
    public String end(){
        return "outside/end";
    }
}
