package com.KioskSNU.view.outside;

import com.KioskSNU.secure.RSA;
import com.KioskSNU.snu.dto.*;
import com.KioskSNU.snu.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
public class OutsideAjaxPaymentController {
    private final HashMap<String, Object> ticketMap;
    private final ConcurrentHashMap<Integer, UsageSeatDTO> seatMap;
    private final ConcurrentHashMap<Integer, UsageRoomDTO> roomMap;
    private final Set<Integer> lockerSet;
    private final RSA rsa;
    private final AccountService accountService;
    private final RoomService roomService;
    private final UsageCommutationTicketService usageCommutationTicketService;
    private final UsageLockerService usageLockerService;
    private final PaymentService paymentService;

    @RequestMapping("/ajax/payment")
    public ResponseEntity<Map<String, String>> payProcess(@RequestBody Map<String, String> map, HttpSession session) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        // 입력 정보 확인
        String paymentType = map.get("paymentType");
        String paymentCode = map.get("paymentCode");
        if (paymentType == null || paymentType.isBlank() || paymentCode == null || paymentCode.isBlank()) {
            return ResponseEntity.ok(Map.of("result", "fail"));
        }
        paymentCode = rsa.decrypt(paymentCode);

        // 사용자 확인
        AccountDTO accountDTO = (AccountDTO) session.getAttribute("author");
        if (accountDTO == null) {
            return ResponseEntity.ok(Map.of("result", "fail"));
        }

        // 사용자 정보 정상 확인
        accountDTO = accountService.getById(accountDTO.getId());
        if (accountDTO == null) {
            return ResponseEntity.ok(Map.of("result", "fail"));
        }

        // 결제 정보 생성
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setDateTime(LocalDateTime.now());

        // 장바구니 정보 확인
        List<String> ticketList = new ArrayList<>();
        List<Integer> timeList = new ArrayList<>();
        List<Integer> priceList = new ArrayList<>();

        ticketMap.forEach((key, value) -> {
            switch (key) {
                case "timeTicket":
                    ticketList.add("시간권");
                    TimeTicketDTO timeTicket = (TimeTicketDTO) value;
                    timeList.add(timeTicket.getTime());
                    priceList.add(timeTicket.getPrice());
                    break;
                case "commutationTicket":
                    ticketList.add("정기권");
                    CommutationTicketDTO commutationTicket = (CommutationTicketDTO) value;
                    timeList.add(commutationTicket.getDay());
                    priceList.add(commutationTicket.getPrice());
                    break;
                case "room":
                    ticketList.add("룸");
                    timeList.add((Integer) value);
                    int roomNumber = (int) session.getAttribute("selectNumber");
                    RoomDTO roomDTO = roomService.getByRoomNumber(roomNumber);
                    priceList.add(((Integer) value) * roomDTO.getRoomType_price());
                    break;
                case "lockerTicket":
                    ticketList.add("사물함");
                    LockerTicketDTO lockerTicket = (LockerTicketDTO) value;
                    timeList.add(lockerTicket.getDay());
                    priceList.add(lockerTicket.getPrice());
                    break;
            }
        });

        // 결제 정보 생성
        int totalPrice = priceList.stream().mapToInt(Integer::intValue).sum();
        int payPoint = Math.min(accountDTO.getPoint(), totalPrice);
        int paymentPrice = totalPrice - payPoint;

        paymentDTO.setAmount(totalPrice);
        paymentDTO.setUsedPoint(payPoint);

        ////////////////////////////
        //        결제처리        //
        ////////////////////////////
        boolean paymentResult = true;
        if (!paymentResult) {
            return ResponseEntity.ok(Map.of("result", "fail"));
        }

        // 결제 성공 시

        // 포인트 차감
        accountDTO.setPoint(accountDTO.getPoint() - payPoint);

        // 결제 내역 로그
        StringBuilder logBuilder = new StringBuilder("{");
        logBuilder.append("\"result\":\"결제 성공\",");
        logBuilder.append("\"type\":\"").append(paymentType).append("\",");
        logBuilder.append("\"code\":\"").append(paymentCode).append("\",");
        logBuilder.append("\"amount\":").append(totalPrice).append(",");
        logBuilder.append("\"point\":").append(payPoint).append(",");
        logBuilder.append("\"pay\":").append(paymentPrice).append(",");

        logBuilder.append("\"breakdown\":[");

        // 실제 결과 처리 시작
        for (int i = 0; i < ticketList.size(); i++) {
            String ticket = ticketList.get(i);
            int time = timeList.get(i);
            int price = priceList.get(i);

            if (i > 0) logBuilder.append(",");
            logBuilder.append("{");

            switch (ticket) {
                case "시간권": {
                    // 잔여 시간 추가
                    accountDTO.setRemainTime(accountDTO.getRemainTime() + time * 60);
                    logBuilder.append("\"type\":\"Time Ticket\",");
                    logBuilder.append("\"time\":").append(time).append(",");
                    logBuilder.append("\"unit\":\"hour\",");
                    logBuilder.append("\"price\":").append(price);
                    break;
                }

                case "정기권": {
                    // 정기권 기존 보유 여부 확인
                    boolean hasCommutationTicket = accountService.hasCommutationTicket(accountDTO);
                    if (hasCommutationTicket) {
                        // 기존 정기권 기간 연장
                        UsageCommutationTicketDTO usageCommutationTicketDTO = accountService.getCommutationTicket(accountDTO);
                        usageCommutationTicketDTO.setEndDate(usageCommutationTicketDTO.getEndDate().plusDays(time));
                        usageCommutationTicketService.update(usageCommutationTicketDTO);
                    } else {
                        // 정기권 신규 발급
                        UsageCommutationTicketDTO usageCommutationTicketDTO = new UsageCommutationTicketDTO();
                        usageCommutationTicketDTO.setAccountDTO(accountDTO);
                        LocalDate startDate = LocalDate.now();
                        usageCommutationTicketDTO.setStartDate(startDate);
                        usageCommutationTicketDTO.setEndDate(startDate.plusDays(time));
                        usageCommutationTicketService.insert(usageCommutationTicketDTO);
                    }
                    logBuilder.append("\"type\":\"Commutation Ticket\",");
                    logBuilder.append("\"time\":").append(time).append(",");
                    logBuilder.append("\"unit\":\"day\",");
                    logBuilder.append("\"price\":").append(price);
                    break;
                }

                case "룸": {
                    // 선택된 룸 정보 확인
                    int roomNumber = (int) session.getAttribute("selectNumber");
                    UsageRoomDTO usageRoomDTO = roomMap.get(roomNumber);
                    logBuilder.append("\"type\":\"Room Ticket\",");
                    logBuilder.append("\"time\":").append(time).append(",");
                    logBuilder.append("\"unit\":\"hour\",");
                    logBuilder.append("\"price\":").append(price);
                    break;
                }

                case "사물함": {
                    // 사물함 기존 보유 여부 확인
                    boolean hasLocker = usageLockerService.hasLocker(accountDTO);
                    if (hasLocker) {
                        // 기존 사물함 기간 연장
                        UsageLockerDTO usageLockerDTO = usageLockerService.getLocker(accountDTO);
                        usageLockerDTO.setEndDate(usageLockerDTO.getEndDate().plusDays(time));
                        usageLockerService.update(usageLockerDTO);
                    } else {
                        // 사물함 신규 발급
                        UsageLockerDTO usageLockerDTO = new UsageLockerDTO();
                        usageLockerDTO.setAccountDTO(accountDTO);

                        // 선택된 사물함 정보 확인
                        LockerDTO lockerDTO = (LockerDTO) ticketMap.get("locker");
                        usageLockerDTO.setLockerDTO(lockerDTO);

                        // 사물함 사용 기간 설정
                        LocalDate startDate = LocalDate.now();
                        usageLockerDTO.setStartDate(startDate);
                        usageLockerDTO.setEndDate(startDate.plusDays(time));
                        usageLockerService.insert(usageLockerDTO);

                        // 사물함 결제중 정보 삭제
                        lockerSet.remove(lockerDTO.getLockerNumber());
                    }
                    logBuilder.append("\"type\":\"Locker Ticket\",");
                    logBuilder.append("\"time\":").append(time).append(",");
                    logBuilder.append("\"unit\":\"day\",");
                    logBuilder.append("\"price\":").append(price);
                    break;
                }
            }

            logBuilder.append("}");
        }

        logBuilder.append("]}");

        // 결제 정보 저장
        accountService.update(accountDTO);
        paymentDTO.setAccountDTO(accountDTO);
        paymentDTO.setLog(logBuilder.toString());
        paymentService.insert(paymentDTO);

        return ResponseEntity.ok(Map.of("result", "success"));
    }
}
