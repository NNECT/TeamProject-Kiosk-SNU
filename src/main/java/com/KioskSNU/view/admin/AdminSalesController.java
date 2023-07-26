package com.KioskSNU.view.admin;

import com.KioskSNU.common.Scaler;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.service.ParticipationChallengeService;
import com.KioskSNU.snu.service.UsageLockerService;
import com.KioskSNU.snu.service.UsageRoomService;
import com.KioskSNU.snu.service.UsageSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminSalesController {
    private final UsageSeatService usageSeatService;
    private final UsageRoomService usageRoomService;
    private final UsageLockerService usageLockerService;
    private final ParticipationChallengeService participationChallengeService;
    private final Scaler scaler;

    @RequestMapping("/admin/sales")
    public ModelAndView sales() {
        ModelAndView mav = new ModelAndView();

        // 최근 1년간 월별 누적 사용시간 현황
        Map<LocalDate, Double> seatTimes = usageSeatService.get1YearTimes();
        List<String> seatTimesLabels = new ArrayList<>();
        List<Double> seatTimesData = new ArrayList<>();
        seatTimes.forEach((date, time) -> {
            seatTimesLabels.add(date.format(DateTimeFormatter.ofPattern("yyyy.MM")));
            seatTimesData.add(time);
        });
        mav.addObject("seatTimesLabels", seatTimesLabels);
        mav.addObject("seatTimesData", seatTimesData);

        // 챌린지 선택률/성공률
        Map<String, List<ParticipationChallengeDTO>> challengeSituationMap = participationChallengeService.getEachChallengeSituation();
        Map<String, Integer> challengeSituationScaledData = scaler.sumScaler(challengeSituationMap, 360);
        List<String> challengeSituationLabels = new ArrayList<>();
        List<Double> challengeSituationData = new ArrayList<>();
        List<Integer> challengeSituationAngles = new ArrayList<>();
        challengeSituationMap.forEach((label, dataList) -> {
            challengeSituationLabels.add(label);
            challengeSituationData.add(participationChallengeService.getSuccessRate(dataList));
            challengeSituationAngles.add(challengeSituationScaledData.get(label));
        });
        mav.addObject("challengeSituationLabels", challengeSituationLabels);
        mav.addObject("challengeSituationData", challengeSituationData);
        mav.addObject("challengeSituationAngles", challengeSituationAngles);

        mav.setViewName("admin/admin_sales");
        return mav;
    }
}
