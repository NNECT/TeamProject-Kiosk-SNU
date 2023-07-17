package com.KioskSNU.view.outside;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.RoomService;
import com.KioskSNU.snu.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class OutsideLoginController {
    private final Map<Integer, UsageSeatDTO> seatMap;
    private final Map<Integer, UsageRoomDTO> roomMap;
    private final RSA rsa;
    private final SHA sha;
    private final AccountService accountService;
    private final SeatService seatService;
    private final RoomService roomService;

    @Autowired
    public OutsideLoginController(
            ConcurrentHashMap<Integer, UsageSeatDTO> seatMap,
            ConcurrentHashMap<Integer, UsageRoomDTO> roomMap,
            RSA rsa,
            SHA sha,
            AccountService accountService,
            SeatService seatService,
            RoomService roomService
    ) {
        this.seatMap = seatMap;
        this.roomMap = roomMap;
        this.rsa = rsa;
        this.sha = sha;
        this.accountService = accountService;
        this.seatService = seatService;
        this.roomService = roomService;
    }

    @GetMapping("/outside/login")
    public ModelAndView getProcess(String type, Integer number, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/login");
        mav.addObject("publicKey", rsa.getPublicKey());

        if (type == null && session.getAttribute("selectType") != null) {
            type = (String) session.getAttribute("selectType");
            number = (Integer) session.getAttribute("selectNumber");
        }

        if (type == null) {
            mav.setViewName("redirect:/outside/logout");
        } else if (type.equals("seat")) {
            UsageSeatDTO usage = new UsageSeatDTO();
            usage.setSeat_id(seatService.getBySeatNumber(number).getId());
            seatMap.put(number, usage);
            session.setAttribute("selectType", type);
            session.setAttribute("selectNumber", number);
        } else if (type.equals("room")) {
            UsageRoomDTO usage = new UsageRoomDTO();
            usage.setRoom_id(roomService.getByRoomNumber(number).getId());
            roomMap.put(number, usage);
            session.setAttribute("selectType", type);
            session.setAttribute("selectNumber", number);
        } else {
            mav.setViewName("redirect:/outside/select");
        }

        return mav;
    }

    @PostMapping("/outside/login")
    public ModelAndView postProcess(AccountDTO accountDTO, HttpSession session) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("outside/login");
        mav.addObject("publicKey", rsa.getPublicKey());

        if (accountDTO == null) {
            //accountDTO로 들어오는 내용이 null 이라면 첫화면으로 다시 보내기
            mav.setViewName("redirect:/outside/logout");
        } else if (accountDTO.getUsername() == null) {
            mav.setViewName("/outside/login");
        } else {
            //로그인 정보 먼저 받아오기 (*전체정보 모두 들어옴)
            AccountDTO getUser = accountService.getByUsername(accountDTO.getUsername());
            if (getUser == null) {
                mav.addObject("userNameError", "회원정보를 확인해주세요.");
                mav.setViewName("/outside/login");
            } else if (getUser.getPassword().equals(sha.encrypt(rsa.decrypt(accountDTO.getPassword())))) {
                //저장되어 있는 SHA 비밀번호와 입력된 비밀번호 비교
                //로그인성공 (세션에 author 이름으로 account 정보 저장)
                session.setAttribute("author", getUser);
                switch ((String) session.getAttribute("selectType")) {
                    case "seat":
                        mav.setViewName("redirect:/outside/ticket/seat");
                        seatMap.get((int) session.getAttribute("selectNumber")).setAccount_id(getUser.getId());
                        break;
                    case "room":
                        mav.setViewName("redirect:/outside/ticket/room");
                        roomMap.get((int) session.getAttribute("selectNumber")).setAccount_id(getUser.getId());
                        break;
                    default:
                        mav.setViewName("redirect:/outside/logout");
                        break;
                }
            } else {
                //비밀번호가 틀리면
                mav.addObject("userPasswordError", "비밀번호를 확인해주세요");
                mav.setViewName("/outside/login");
            }
        }

        return mav;
    }
}
