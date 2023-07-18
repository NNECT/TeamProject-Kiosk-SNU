package com.KioskSNU.view.inside;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.AdminDTO;
import com.KioskSNU.snu.service.AdminService;
import com.KioskSNU.snu.service.RoomService;
import com.KioskSNU.snu.service.SeatService;
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
public class InsideInitializeController {
    private final RSA rsa;
    private final SHA sha;
    private final AdminService adminService;
    private final SeatService seatService;
    private final RoomService roomService;

    @Autowired
    public InsideInitializeController(
            RSA rsa,
            SHA sha,
            AdminService adminService,
            SeatService seatService,
            RoomService roomService
    ) {
        this.rsa = rsa;
        this.sha = sha;
        this.adminService = adminService;
        this.seatService = seatService;
        this.roomService = roomService;
    }

    @GetMapping("/inside")
    public ModelAndView getProcess() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("inside/initialize");
        modelAndView.addObject("publicKey", rsa.getPublicKey());
        return modelAndView;
    }

    @PostMapping("/inside")
    public ModelAndView postProcess(AdminDTO adminDTO, int number, String type, HttpSession session) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        ModelAndView modelAndView = new ModelAndView();

        // 데이터 입력 오류 처리
        if (adminDTO == null
                || adminDTO.getUsername() == null
                || adminDTO.getUsername().isBlank()
                || adminDTO.getPassword() == null
                || adminDTO.getPassword().isBlank()
                || type == null
        ) {
            modelAndView.setViewName("inside/initialize");
            modelAndView.addObject("publicKey", rsa.getPublicKey());
            return modelAndView;
        }

        // username 존재 여부 확인
        AdminDTO admin = adminService.getByUsername(adminDTO.getUsername());
        if (admin == null) {
            modelAndView.setViewName("inside/initialize");
            modelAndView.addObject("publicKey", rsa.getPublicKey());
            return modelAndView;
        }

        // password 일치 여부 확인
        if (!admin.getPassword().equals(sha.encrypt(rsa.decrypt(adminDTO.getPassword())))) {
            modelAndView.setViewName("inside/initialize");
            modelAndView.addObject("publicKey", rsa.getPublicKey());
            return modelAndView;
        }

        // type 분기 처리
        switch (type) {
            case "seat":
                // seatNumber 존재 여부 확인
                if (seatService.getBySeatNumber(number) == null) {
                    modelAndView.setViewName("inside/initialize");
                    modelAndView.addObject("publicKey", rsa.getPublicKey());
                    return modelAndView;
                }

                // 세션에 자리 등록
                session.setAttribute("insideType", "seat");
                session.setAttribute("insideNumber", number);
                break;

            case "room":
                // roomNumber 존재 여부 확인
                if (roomService.getByRoomNumber(number) == null) {
                    modelAndView.setViewName("inside/initialize");
                    modelAndView.addObject("publicKey", rsa.getPublicKey());
                    return modelAndView;
                }

                // 세션에 자리 등록
                session.setAttribute("insideType", "room");
                session.setAttribute("insideNumber", number);
                break;

            default:
                modelAndView.setViewName("inside/initialize");
                modelAndView.addObject("publicKey", rsa.getPublicKey());
                return modelAndView;
        }

        modelAndView.setViewName("redirect:/inside/index");
        return modelAndView;
    }
}
