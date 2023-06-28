package com.KioskSNU.view;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class IndexController {
    Map<Integer, HttpSession> seatMap;
    RSA rsa;
    SHA sha;

    @Autowired
    public IndexController(Map<Integer, HttpSession> seatMap, RSA rsa, SHA sha) {
        this.seatMap = seatMap;
        this.rsa = rsa;
        this.sha = sha;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
