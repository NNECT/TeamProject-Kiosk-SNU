package com.KioskSNU.view.inside;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.secure.SHA;
import com.KioskSNU.snu.dto.AccountDTO;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.dto.PaymentDTO;
import com.KioskSNU.snu.service.AccountService;
import com.KioskSNU.snu.service.ParticipationChallengeService;
import com.KioskSNU.snu.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inside")
public class InsideMypageController {
    private final RSA rsa;
    private final SHA sha;
    private final AccountService accountService;
    private final ParticipationChallengeService participationChallengeService;
    private  final PaymentService paymentService;

    @RequestMapping("/mypage") // 새로 추가된 메서드
    public ModelAndView insideMypage(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("publicKey", rsa.getPublicKey());

        AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");
        try{
            //챌린지 리스트
            List<ParticipationChallengeDTO> challengeList = participationChallengeService.getAllByAccount(accountDTO);
            mav.addObject("challengeList", challengeList);

            List<PaymentDTO> paymentList = paymentService.getAllByAccount(accountDTO);
            mav.addObject("paymentList", paymentList);
            //결제내역 리스트
        }catch (Exception e){
            e.printStackTrace();
        }
        mav.setViewName("inside/inside_mypage");
        return mav;
    }

    //비밀번호 수정
    @PostMapping("/mypage/changepwd")
    public ModelAndView changePassword(AccountDTO accountDTO, HttpSession session) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        ModelAndView mav = new ModelAndView();

        try {
//            System.out.println("accountDTO.getPassword() = " + sha.encrypt(rsa.decrypt(accountDTO.getPassword())));
            //현재의 세션 객체
            AccountDTO currentUser = (AccountDTO) session.getAttribute("author");
            //현재 객체에서 정보를 가져옴
            AccountDTO getUser = accountService.getByUsername(currentUser.getUsername());
            sha.encrypt(rsa.decrypt(accountDTO.getPassword()));

            //그것의 비밀번호 수정
            getUser.setPassword(sha.encrypt(rsa.decrypt(accountDTO.getPassword())));
            accountService.update(getUser);
            session.setAttribute("author", getUser);
            mav.addObject(getUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.setViewName("redirect:/inside/mypage");
        return mav;
    }

    @PostMapping("/mypage/changephone")
    public ModelAndView changePhoneNumber(@RequestParam String phoneNumber, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        AccountDTO accountDTO;
        System.out.println(phoneNumber);

        try {
            accountDTO = (AccountDTO) session.getAttribute("author");
            AccountDTO getUser = accountService.getByUsername(accountDTO.getUsername());
            getUser.setPhoneNumber(phoneNumber);
            accountService.update(getUser);
            session.setAttribute("author", getUser);
            mav.addObject(getUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.setViewName("redirect:/inside/mypage");
        return mav;
    }
}
