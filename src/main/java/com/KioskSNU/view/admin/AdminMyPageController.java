package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.AdminDTO;
import com.KioskSNU.snu.service.AdminService;
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
public class AdminMyPageController {

    private final AdminService adminService;
    private final RSA rsa;
    private final SHA sha;

    @Autowired
    public AdminMyPageController(
            AdminService adminService,
            RSA rsa,
            SHA sha
    ) {
        this.adminService = adminService;
        this.rsa = rsa;
        this.sha = sha;
    }

    @GetMapping("/admin/adminmypage")
    @AdminLoginRequired
    public ModelAndView getProcess(HttpSession session){

        ModelAndView mav = new ModelAndView();
        mav.addObject("publicKey", rsa.getPublicKey());
        mav.setViewName("admin/admin_mypage");
        return mav;

    }


    @PostMapping("/admin/adminmypage")
    public ModelAndView postProcess(AdminDTO adminDTO,HttpSession session, String prePassword) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/admin_main");

        //들어온 username값으로 getAdmin
        AdminDTO getAdmin = adminService.getByUsername(adminDTO.getUsername());

        //이전 비밀번호 잘못 입력한 경우
        if(!sha.encrypt(rsa.decrypt(prePassword)).equals(getAdmin.getPassword())){
            mav.addObject("publicKey", rsa.getPublicKey());
            mav.addObject("checkFail","checkFail");
            mav.setViewName("/admin/admin_mypage");
            return mav;
        }

        //비밀번호 변경 성공과정
        //현재 관리자정보 비밀번호 암호화 , set
        getAdmin.setPassword((sha.encrypt(rsa.decrypt(adminDTO.getPassword()))));
        //업데이트
        adminService.update(getAdmin);
        //세션에 업데이트된 정보 덮어씌우기
        session.setAttribute("admin",adminService.getByUsername(getAdmin.getUsername()));

        return mav;

    }


}
