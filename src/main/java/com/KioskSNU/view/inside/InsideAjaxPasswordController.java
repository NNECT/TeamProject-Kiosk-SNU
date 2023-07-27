package com.KioskSNU.view.inside;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class InsideAjaxPasswordController {
    private final AccountService accountService;
    private final RSA rsa;
    private final SHA sha;

    @RequestMapping("/ajax/checkpwd")
    public ResponseEntity<Map<String, String>> checkPassWord(@RequestBody Map<String, String> map, HttpSession session) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {

        AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");
        String password = map.get("password");
        String decryptedPassword = rsa.decrypt(password);
//
//        System.out.println(decryptedPassword);
//        System.out.println(password);
//        System.out.println(accountDTO.getPassword());

        if(sha.encrypt(decryptedPassword).equals(accountDTO.getPassword()))
            return ResponseEntity.ok(Map.of("result", "success"));
        else
            return ResponseEntity.ok(Map.of("result", "fail"));
    }
    @RequestMapping("/ajax/changePhcodeSend")
    public ResponseEntity<Map<String, String>> checkPhone(@RequestBody Map<String, String> map, HttpSession session) {
       String phoneNumber = map.get("phoneNumber");
       session.setAttribute("phoneNumber", phoneNumber);
       session.setAttribute("codeTime", LocalDateTime.now());
       session.setAttribute("code", String.format("%04d", new Random().nextInt(10000)));
       System.out.println(session.getAttribute("code"));

       return ResponseEntity.ok(Map.of("result", "success"));
    }
    @RequestMapping("/ajax/checkCode")
    public ResponseEntity<Map<String, String>> checkCode(@RequestBody Map<String,String>map, HttpSession session){
        String code = map.get("code");
        if(session.getAttribute("code").equals(code)){
            return ResponseEntity.ok(Map.of("result", "success"));
        }else {

            return ResponseEntity.ok(Map.of("result", "fail"));
        }
    }
}
