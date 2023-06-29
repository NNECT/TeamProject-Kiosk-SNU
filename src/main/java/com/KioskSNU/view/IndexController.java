package com.KioskSNU.view;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class IndexController {
    Map<Integer, HttpSession> seatMap;
    Map<Integer, HttpSession> roomMap;
    RSA rsa;
    SHA sha;

    @Autowired
    public IndexController(@Qualifier("seatMap") ConcurrentHashMap<Integer, HttpSession> seatMap,
                           @Qualifier("roomMap") ConcurrentHashMap<Integer, HttpSession> roomMap,
                           RSA rsa, SHA sha) {
        this.seatMap = seatMap;
        this.roomMap = roomMap;
        this.rsa = rsa;
        this.sha = sha;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
