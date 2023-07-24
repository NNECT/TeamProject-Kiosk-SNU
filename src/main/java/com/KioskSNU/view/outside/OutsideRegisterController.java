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
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
@RequiredArgsConstructor
public class OutsideRegisterController {
    private final RSA rsa;
    private final SHA sha;
    private final AccountService accountService;

    @GetMapping("/outside/register")
    public ModelAndView getProcess() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/register");
        mav.addObject("publicKey", rsa.getPublicKey());
        return mav;
    }

    @PostMapping("/outside/register")
    public ModelAndView postProcess(AccountDTO accountDTO) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        System.out.println(accountDTO.getUsername());
        System.out.println(accountDTO.getPassword());
        System.out.println(accountDTO.getPhoneNumber());

        ModelAndView mav = new ModelAndView();
        accountDTO.setPassword(sha.encrypt(rsa.decrypt(accountDTO.getPassword())));
        accountDTO.setPoint(0);
        accountDTO.setRemainTime(0);
        accountService.insert(accountDTO);
        mav.setViewName("outside/registerSuccess");
        return mav;
    }
}
