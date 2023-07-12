package com.KioskSNU.view.outside;

import com.KioskSNU.snu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OutsideAjaxUsernameCheckController {
    private final AccountService accountService;

    @Autowired
    public OutsideAjaxUsernameCheckController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/ajax/username")
    public ResponseEntity<Map<String, String>> process(@RequestBody Map<String, String> map) {
        System.out.println(map.get("username"));
        return ResponseEntity.ok(Map.of("msg", accountService.getByUsername(map.get("username")) == null ? "true" : "false"));
    }
}
