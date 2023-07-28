package com.KioskSNU.view.admin;

import com.KioskSNU.common.Scaler;
import com.KioskSNU.snu.dto.ParticipationChallengeDTO;
import com.KioskSNU.snu.dto.PaymentDTO;
import com.KioskSNU.snu.service.*;
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
    private final PaymentService paymentService;
    private final UsageCommutationTicketService usageCommutationTicketService;
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

        // 최근 1년간 월별 매출 현황
        Map<LocalDate, List<PaymentDTO>> payments = paymentService.get1YearPayment();
        List<String> paymentLabels = new ArrayList<>();
        List<Integer> paymentData = new ArrayList<>();
        payments.forEach((date, paymentList) -> {
            paymentLabels.add(date.format(DateTimeFormatter.ofPattern("yyyy.MM")));
            paymentData.add(paymentList.stream().mapToInt(p -> p.getAmount() - p.getUsedPoint()).sum());
        });
        mav.addObject("paymentLabels", paymentLabels);
        mav.addObject("paymentData", paymentData);

        // 최근 1년간 월별 정기권 사용자 수 현황
        Map<LocalDate, Integer> commutationTicketUsers = usageCommutationTicketService.get1YearCommutationTicketUsers();
        List<String> commutationTicketUsersLabels = new ArrayList<>();
        List<Integer> commutationTicketUsersData = new ArrayList<>();
        commutationTicketUsers.forEach((date, users) -> {
            commutationTicketUsersLabels.add(date.format(DateTimeFormatter.ofPattern("yyyy.MM")));
            commutationTicketUsersData.add(users);
        });
        mav.addObject("commutationTicketUsersLabels", commutationTicketUsersLabels);
        mav.addObject("commutationTicketUsersData", commutationTicketUsersData);

        // 챌린지 선택률/성공률
        Map<String, List<ParticipationChallengeDTO>> challengeSituationMap = participationChallengeService.getEachChallengeSituation();
        Map<String, Integer> challengeSituationScaledData = scaler.sumMapScaler(challengeSituationMap, 360);
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
