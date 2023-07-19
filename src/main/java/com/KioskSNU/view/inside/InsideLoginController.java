package com.KioskSNU.view.inside;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
public class InsideLoginController {
    private final RSA rsa;
    private final SHA sha;


    @Autowired
    public InsideLoginController(
            RSA rsa,
            SHA sha
    ){
        this.rsa = rsa;
        this.sha = sha;
    }

    @GetMapping("inside/login")
    public ModelAndView getProcess(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("publicKey", rsa.getPublicKey());
        mav.setViewName("inside/inside_login");
        return mav;
    }

    @PostMapping("inside/login")
    public ModelAndView postProcess(String password, HttpSession session) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ModelAndView mav = new ModelAndView();

        AccountDTO getUser = (AccountDTO) session.getAttribute("author");

        //로그인 실패시
        if(!sha.encrypt(rsa.decrypt(password)).equals(getUser.getPassword())){
            mav.addObject("publicKey", rsa.getPublicKey());
            mav.addObject("loginFail","loginFail");
            mav.setViewName("inside/inside_login");
            return mav;
        }
        
        //성공시
        mav.setViewName("redirect:/inside/menu");
        return mav;
    }







}



