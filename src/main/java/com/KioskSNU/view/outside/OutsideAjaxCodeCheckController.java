package com.KioskSNU.view.outside;

import com.KioskSNU.snu.dao.AccountDAO;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class OutsideAjaxCodeCheckController {
    private final AccountService accountService;

    @Autowired
    public OutsideAjaxCodeCheckController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/ajax/codeSend")
    public ResponseEntity<Map<String, String>> sendProcess(@RequestBody Map<String, String> map, HttpSession session) {

        String purpose = map.get("codePurpose");
        String phoneNumber = map.get("codePhoneNumber");
        String username = map.get("username");

        if(purpose.equals("findUsername")){
            List<AccountDTO> getUserList = accountService.getAllByPhoneNumber(phoneNumber);
            //아이디찾기
            //phoneNumber 여부 확인
            if(getUserList.isEmpty()){
                return ResponseEntity.ok(Map.of("result", "wrongNumber"));
            }
        }else if(purpose.equals("findPassword")){
            //비밀번호 찾기
            //넘어온 username으로 고객 조회가 불가하면 잘못된 아이디
            //고객조회 성공하면 넘어온 phoneNumber 와 일치하면 성공 / 실패하면 잘못된 번호
            AccountDTO getUser = accountService.getByUsername(username);
            if(getUser == null){
                return ResponseEntity.ok(Map.of("result","notFoundUsername"));
            }else if(!getUser.getPhoneNumber().equals(phoneNumber)){
                return ResponseEntity.ok(Map.of("result", "wrongNumber"));
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
