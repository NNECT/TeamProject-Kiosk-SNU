package com.KioskSNU.view.outside;

import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class OutsideAjaxCodeCheckController {
    private final AccountService accountService;

    @RequestMapping("/ajax/codeSend")
    public ResponseEntity<Map<String, String>> sendProcess(@RequestBody Map<String, String> map, HttpSession session) {

        String purpose = map.get("codePurpose");
        String phoneNumber = map.get("codePhoneNumber");
        String username = map.get("username");

        switch (purpose) {
            //findUsername 요청
            //: { 사용된 이유 : 같은이름 객체 사용 가능하기 위해서
            case "findUsername": {
                //아이디찾기
                //phoneNumber 여부 확인
                if (accountService.getAllByPhoneNumber(phoneNumber).isEmpty()) {
                    return ResponseEntity.ok(Map.of("result", "wrongNumber"));
                }
                break;
            }
            //findPassword 요청
            case "findPassword": {
                AccountDTO getUser = accountService.getByUsername(username);
                //입력한 연락처가 username에 등록되어 있는 연락처랑 다를 경우
                if (getUser == null) {
                    return ResponseEntity.ok(Map.of("result", "notFoundUsername"));
                }
                //입력한 연락처가 username에 등록되어 있는 연락처랑 다를 경우
                if (!getUser.getPhoneNumber().equals(phoneNumber)) {
                    return ResponseEntity.ok(Map.of("result", "wrongNumber"));
                }
                break;
            }
        }

        session.setAttribute("codePurpose", purpose);
        session.setAttribute("codePhoneNumber", phoneNumber);
        session.setAttribute("codeTime", LocalDateTime.now());
        session.setAttribute("code", String.format("%04d", new Random().nextInt(10000)));
        System.out.println(session.getAttribute("codePurpose"));
        System.out.println(session.getAttribute("codePhoneNumber"));
        System.out.println(session.getAttribute("codeTime"));
        System.out.println(session.getAttribute("code"));
        return ResponseEntity.ok(Map.of("result", "success"));
    }

    @RequestMapping("/ajax/codeCheck")
    public ResponseEntity<Map<String, String>> checkProcess(@RequestBody Map<String, String> map, HttpSession session) {
        if (
                session.getAttribute("codeTime") == null
                        || LocalDateTime.now().isAfter(((LocalDateTime) session.getAttribute("codeTime")).plusMinutes(3))
                        || session.getAttribute("codePurpose") == null
                        || !session.getAttribute("codePurpose").equals(map.get("codePurpose"))
                        || session.getAttribute("codePhoneNumber") == null
                        || !session.getAttribute("codePhoneNumber").equals(map.get("codePhoneNumber"))
        ) {
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
