package com.KioskSNU.view.outside;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

@RestController
public class OutsideAjaxCodeCheckController {
    @RequestMapping("/ajax/codeSend")
    public ResponseEntity<Map<String, String>> process(@RequestBody Map<String, String> map, HttpSession session) {
        session.setAttribute("codePurpose", map.get("codePurpose"));
        session.setAttribute("codeTime", LocalDateTime.now());
        session.setAttribute("code", String.format("%04d", new Random().nextInt(10000)));
        System.out.println(session.getAttribute("codePurpose"));
        System.out.println(session.getAttribute("codeTime"));
        System.out.println(session.getAttribute("code"));
        return ResponseEntity.ok(Map.of("result", "success"));
    }

    @RequestMapping("/ajax/codeCheck")
    public ResponseEntity<Map<String, String>> process2(@RequestBody Map<String, String> map, HttpSession session) {
        if (session.getAttribute("codeTime") == null
                || LocalDateTime.now().isAfter(((LocalDateTime) session.getAttribute("codeTime")).plusMinutes(3))
                || session.getAttribute("codePurpose") == null
                || !session.getAttribute("codePurpose").equals(map.get("codePurpose"))) {
            return ResponseEntity.ok(Map.of("result", "fail"));
        }
        System.out.println(map.get("code"));
        System.out.println(session.getAttribute("code"));
        if (map.get("code").equals(session.getAttribute("code"))) {
            return ResponseEntity.ok(Map.of("result", "success"));
        } else {
            return ResponseEntity.ok(Map.of("result", "fail"));
        }
    }
}
