package com.KioskSNU.view.outside;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.UsageRoomDTO;
import com.KioskSNU.snu.dto.UsageSeatDTO;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.RoomService;
import com.KioskSNU.snu.service.SeatService;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequiredArgsConstructor
public class OutsideLoginController {
    private final ConcurrentHashMap<Integer, UsageSeatDTO> seatMap;
    private final ConcurrentHashMap<Integer, UsageRoomDTO> roomMap;
    private final RSA rsa;
    private final SHA sha;
    private final AccountService accountService;
    private final SeatService seatService;
    private final RoomService roomService;

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
            usage.setSeatDTO(seatService.getBySeatNumber(number));
            seatMap.put(number, usage);
            session.setAttribute("selectType", type);
            session.setAttribute("selectNumber", number);
        } else if (type.equals("room")) {
            UsageRoomDTO usage = new UsageRoomDTO();
            usage.setRoomDTO(roomService.getByRoomNumber(number));
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

        //accountDTO로 들어오는 내용이 null 이라면 첫화면으로 다시 보내기
        if (accountDTO == null) {
            mav.setViewName("redirect:/outside/logout");
            return mav;
        }

        //좌석선택이후 로그인으로 들어오는 경우, publicKey와 함께 로그인 화면으로 이동
        if (accountDTO.getUsername() == null) {
            mav.addObject("publicKey", rsa.getPublicKey());
            mav.setViewName("/outside/login");
            return mav;
        }

        //회원목록에서 username없으면 로그인 화면으로
        if (accountService.getByUsername(accountDTO.getUsername()) == null) {
            mav.addObject("publicKey", rsa.getPublicKey());
            mav.addObject("loginFail", "loginFail");
            mav.setViewName("/outside/login");
            return mav;
        }

        //비밀번호가 틀리면
        if (!accountService.getByUsername(accountDTO.getUsername()).getPassword().equals(sha.encrypt(rsa.decrypt(accountDTO.getPassword())))) {
            mav.addObject("publicKey", rsa.getPublicKey());
            mav.addObject("loginFail", "loginFail");
            mav.setViewName("/outside/login");
            return mav;
        }


        //로그인과정 (세션에 author 이름으로 account 정보 저장)
        AccountDTO getUser = accountService.getByUsername(accountDTO.getUsername());
        boolean isDuplicatedID = false;
        switch ((String) session.getAttribute("selectType")) {
            case "seat":
                //중복로그인 확인
                for(Map.Entry<Integer, UsageSeatDTO> entry : seatMap.entrySet()){
                    int num = entry.getKey();
                    UsageSeatDTO info = entry.getValue();
                    if(info.getAccount_id() == getUser.getId()){
                        isDuplicatedID = true;
                        mav.addObject("duplicatedNum",num);
                        break;
                    }
                }
                //이미 로그인되어 있는 정보가 있으면 login 페이지로 다시 이동
                if(isDuplicatedID){
                    mav.addObject("duplicatedID","duplicatedID");
                    mav.addObject("publicKey", rsa.getPublicKey());
                    mav.setViewName("/outside/login");
                    return mav;
                }
                //로그인성공 (세션에 author 이름으로 account 정보 저장)
                session.setAttribute("author", getUser);
                mav.setViewName("redirect:/outside/having");
                seatMap.get((int) session.getAttribute("selectNumber")).setAccountDTO(getUser);
                break;

            case "room":
                //중복로그인 확인
                for(Map.Entry<Integer, UsageRoomDTO> entry : roomMap.entrySet()){
                    int num = entry.getKey();
                    UsageRoomDTO info = entry.getValue();
                    if(info.getAccount_id() == getUser.getId()){
                        isDuplicatedID = true;
                        mav.addObject("duplicatedNum",num);
                        break;
                    }
                }
                //이미 로그인되어 있는 정보가 있으면 login 페이지로 다시 이동
                if(isDuplicatedID){
                    mav.addObject("duplicatedID","duplicatedID");
                    mav.addObject("publicKey", rsa.getPublicKey());
                    mav.setViewName("/outside/login");
                    return mav;
                }
                //로그인 성공
                session.setAttribute("author", getUser);
                mav.setViewName("redirect:/outside/ticket/room");
                roomMap.get((int) session.getAttribute("selectNumber")).setAccountDTO(getUser);
                break;
            default:
                mav.setViewName("redirect:/outside/logout");
                break;
        }
        return mav;
    }
}
