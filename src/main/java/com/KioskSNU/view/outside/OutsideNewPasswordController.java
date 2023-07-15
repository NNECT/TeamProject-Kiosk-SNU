package com.KioskSNU.view.outside;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
public class OutsideNewPasswordController {

    private final AccountService accountService;
    private final RSA rsa;
    private final SHA sha;

    @Autowired
    public OutsideNewPasswordController(
            AccountService accountService,
            RSA rsa,
            SHA sha
    ){
        this.accountService = accountService;
        this.rsa = rsa;
        this.sha = sha;
    }


    @GetMapping("/outside/newPassword")
    public ModelAndView getProcess(HttpSession session) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ModelAndView mav = new ModelAndView();
        mav.addObject("publicKey", rsa.getPublicKey());

        mav.setViewName("outside/newPassword");
        return mav;
    }


    @PostMapping("/outside/newPassword")
    public ModelAndView postProcess(AccountDTO accountDTO,HttpSession session) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ModelAndView mav = new ModelAndView();

        AccountDTO getUser = accountService.getByUsername(accountDTO.getUsername());
        //rsa로 넘어온 비밀번호를 decrypt
        //sha로 다시 암호화
        getUser.setPassword(sha.encrypt(rsa.decrypt(accountDTO.getPassword())));
        //비밀번호 변경 db
        accountService.update(getUser);

        //session값 해지해주기
        session.removeAttribute("findPasswordUser");

        //기존 session 에 들어있던 type과 number login으로 보내서 형태 맞춰주기 redirect 사용으로 get
        mav.setViewName(String.format("redirect:/outside/login?type=%s&number=%d", session.getAttribute("selectType"), (int) session.getAttribute("selectNumber")));
        return mav;
    }
}
