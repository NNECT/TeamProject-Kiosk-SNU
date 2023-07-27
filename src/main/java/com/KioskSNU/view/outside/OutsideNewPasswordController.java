package com.KioskSNU.view.outside;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
@RequiredArgsConstructor
public class OutsideNewPasswordController {
    private final AccountService accountService;
    private final RSA rsa;
    private final SHA sha;


    @GetMapping("/outside/newPassword")
    public ModelAndView getProcess(HttpSession session) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ModelAndView mav = new ModelAndView();
        mav.addObject("publicKey", rsa.getPublicKey());

        mav.setViewName("outside/newPassword");
        return mav;
    }


    @PostMapping("/outside/newPassword")
    public ModelAndView postProcess(AccountDTO accountDTO, HttpSession session) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ModelAndView mav = new ModelAndView();

        AccountDTO getUser = accountService.getByUsername(accountDTO.getUsername());
        String prePassword = getUser.getPassword();
        String newPassword = sha.encrypt(rsa.decrypt(accountDTO.getPassword()));

        //기존 비밀번호와 새로운 비밀번호 동일하면
        if(prePassword.equals(newPassword)){
            //username은 이미 session값으로 등록되어 있으니 newPassword 과정 다시 진행하도록 보내기
            mav.addObject("publicKey", rsa.getPublicKey());
            mav.addObject("equalPW", "equalPW");
            mav.setViewName("/outside/newPassword");
            return mav;
        }

        //rsa로 넘어온 비밀번호를 decrypt
        //sha로 다시 암호화
        getUser.setPassword(newPassword);
        //비밀번호 변경 db
        accountService.update(getUser);

        //session값 해지해주기
        session.removeAttribute("findPasswordUser");

        //기존 session 에 들어있던 type과 number login으로 보내서 형태 맞춰주기 redirect 사용으로 get

        mav.setViewName(String.format("redirect:/outside/login?type=%s&number=%d", session.getAttribute("selectType"), (int) session.getAttribute("selectNumber")));
        return mav;
    }
}
