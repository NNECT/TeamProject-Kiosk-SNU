package com.KioskSNU.view.admin;

import com.KioskSNU.snu.dto.ChallengeDTO;
import com.KioskSNU.snu.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/admin/challenge")
public class AdminChallengeController {
    @Autowired
    private final ChallengeService challengeService;

    @Autowired
    public AdminChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/admin/admin_challenge");

        try {
            List<ChallengeDTO> list = challengeService.getAll();
            mav.addObject("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @GetMapping("/read")
    public ModelAndView read(Integer id) {
        ModelAndView mav = new ModelAndView();

        try {
            ChallengeDTO challenge = challengeService.getById(id);
            mav.addObject("id", id);
            mav.addObject("challenge", challenge);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.setViewName("admin/admin_challengeEdit");
        return mav;
    }

    @GetMapping("/write")
    public String getWrite() {
        return "/admin/admin_challengeWrite";
    }

    @PostMapping("/write")
    public ModelAndView postWrite(ChallengeDTO challengeDTO, @RequestParam(value = "startTime", required = false) String activeStartTime,
                                  @RequestParam(value = "endTime", required = false) String activeEndTime, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();

        try {
            // 이미 현재 챌린지로 등록된 챌린지의 개수 확인
            int currentChallengesCount = challengeService.getCountOfActiveChallenges();
            System.out.println(currentChallengesCount);
            // 현재 챌린지로 등록되는 경우, 최대 등록 가능 개수를 초과하는지 확인
            if (challengeDTO.isActive() && currentChallengesCount >= 3) {
                // 최대 등록 가능 개수를 초과하는 경우, 메시지를 담아서 다시 등록 페이지로 돌려보냅니다.
                redirectAttributes.addFlashAttribute("msg", "error");
                mav.setViewName("redirect:/admin/challenge/write");
                return mav;
            }

            // activeStartTime, activeEndTime 값이 없을 경우 기본값으로 설정
            LocalTime startTime = activeEndTime != null ? LocalTime.parse(activeStartTime, DateTimeFormatter.ofPattern("HH:mm")) : LocalTime.now();
            LocalTime endTime = activeEndTime != null ? LocalTime.parse(activeEndTime, DateTimeFormatter.ofPattern("HH:mm")) : LocalTime.now();

            challengeDTO.setActiveStartTime(startTime);
            challengeDTO.setActiveEndTime(endTime);
            challengeDTO.setVisible(true);
            challengeService.insert(challengeDTO);
            mav.addObject("challenge", challengeService.getById(challengeDTO.getId()));
        } catch (org.springframework.jdbc.BadSqlGrammarException e) {
            e.printStackTrace();
            // 여기에 원하는 오류 처리 로직을 추가합니다.
            // 예를 들어, 오류 메시지를 설정하거나 로그를 남기는 등의 처리를 할 수 있습니다.
            redirectAttributes.addFlashAttribute("msg", "error");
            mav.setViewName("redirect:/admin/challenge/write");
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            // 그 외의 예외가 발생한 경우에도 처리할 수 있습니다.
            // 여기에 다른 예외 처리 로직을 추가하시면 됩니다.
        }

        redirectAttributes.addFlashAttribute("msg", "챌린지가 추가되었습니다.");
        mav.setViewName("redirect:/admin/challenge/list");
        return mav;
    }


    @GetMapping("/remove")
    public String getRemove() {
        return "/admin/admin_challengeEdit";
    }

    @PostMapping("/remove")
    public String remove(Model m, Integer id, RedirectAttributes rattr) {
        try {
            int rowCnt = challengeService.delete(challengeService.getById(id));
            if (rowCnt != 1)
                throw new Exception("error");
            rattr.addFlashAttribute("msg", "gooooood");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "error");
        }
        return "redirect:/admin/challenge/list";
    }

    @GetMapping("/edit")
    public String getEdit() {
        return "/admin/admin_challengeEdit";
    }


    @PostMapping("/edit")
    public ModelAndView edit(ChallengeDTO challengeDTO, @RequestParam(value = "StartTime") String StartTime,
                             @RequestParam(value = "EndTime") String EndTime, RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView();

        try {

            // 이미 현재 챌린지로 등록된 챌린지의 개수 확인
            int currentChallengesCount = challengeService.getCountOfActiveChallenges();
            // 현재 챌린지로 등록되는 경우, 최대 등록 가능 개수를 초과하는지 확인
            if (challengeDTO.isActive() && currentChallengesCount >= 3) {
                // 최대 등록 가능 개수를 초과하는 경우, 메시지를 담아서 다시 등록 페이지로 돌려보냅니다.
                redirectAttributes.addFlashAttribute("msg", "error");
                mav.setViewName("redirect:/admin/challenge/read?id="+challengeDTO.getId());
                return mav;
            }
            // 시간 형식을 "HH:mm"으로 지정하여 LocalTime 객체 생성
            LocalTime startTime = LocalTime.parse(StartTime, DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime endTime = LocalTime.parse(EndTime, DateTimeFormatter.ofPattern("HH:mm"));

            challengeDTO.setActiveStartTime(startTime);
            challengeDTO.setActiveEndTime(endTime);
            challengeDTO.setVisible(true);

            // 개행 문자(\n)를 <br> 태그로 변환하여 challengeDTO 객체의 description 속성에 적용
            String descriptionWithBreaks = challengeDTO.getDescription().replace("\n", "<br>");
            challengeDTO.setDescription(descriptionWithBreaks);

            challengeService.update(challengeDTO);
            mav.addObject("challenge", challengeService.getById(challengeDTO.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.setViewName("redirect:/admin/challenge/list");
        return mav;
    }


}
