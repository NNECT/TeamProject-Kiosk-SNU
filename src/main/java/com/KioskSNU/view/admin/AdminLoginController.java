package com.KioskSNU.view.admin;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.AdminDTO;
import com.KioskSNU.snu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Controller
public class AdminLoginController {
    private final AdminService adminService;
    private final RSA rsa;
    private final SHA sha;

    @Autowired
    public AdminLoginController(
            AdminService adminService,
            RSA rsa,
            SHA sha
    ) {
        this.adminService = adminService;
        this.rsa = rsa;
        this.sha = sha;

    }

    @GetMapping("/admin/adminLogin")
    public ModelAndView getProcess(HttpSession session) {
        //adminLogin 으로 처음 들어온 페이지 publicKey 함께 로그인 페이지로 넘겨주기
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/admin_login");
        mav.addObject("publicKey", rsa.getPublicKey());
        return mav;

    }

    @PostMapping("/admin/adminLogin")
    public ModelAndView postProcess(AdminDTO adminDTO, HttpSession session) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/admin/admin_main");

        //accountDTO로 들어오는 내용이 null 이라면 로그인화면
        if (adminDTO == null) {
            mav.setViewName("redirect:/admin/adminLogin");
            return mav;
        }

        //관리자 목록에서 username 없으면 로그인 화면으로
        if (adminService.getByUsername(adminDTO.getUsername()) == null) {
            mav.addObject("publicKey", rsa.getPublicKey());
            mav.addObject("loginFail","loginFail");
            mav.setViewName("/admin/admin_login");
            return mav;
        }

        //비밀번호 틀리면
        if (!adminService.getByUsername(adminDTO.getUsername()).getPassword().equals(sha.encrypt(rsa.decrypt(adminDTO.getPassword())))) {
            mav.addObject("publicKey", rsa.getPublicKey());
            mav.addObject("loginFail","loginFail");
            mav.setViewName("/admin/admin_login");
            return mav;
        }

        //로그인 성공 (세션에 admin 이름으로 admin 정보 저장)
        AdminDTO getAdmin = adminService.getByUsername(adminDTO.getUsername());
        session.setAttribute("admin", getAdmin);

        return mav;

    }

}
