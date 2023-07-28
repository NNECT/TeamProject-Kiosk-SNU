package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.*;
import com.KioskSNU.snu.mapper.PaymentMapper;
import com.KioskSNU.snu.service.PaymentService;
import com.KioskSNU.snu.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Qualifier("paymentDAO")
    private final PaymentMapper paymentDAO;
    private final HashMap<String, Object> ticketMap;
    private final RoomService roomService;

    @Override
    public int insert(PaymentDTO paymentDTO) {
        return paymentDAO.insert(paymentDTO);
    }

    @Override
    public int update(PaymentDTO paymentDTO) {
        return paymentDAO.update(paymentDTO);
    }

    @Override
    public int delete(PaymentDTO paymentDTO) {
        return paymentDAO.delete(paymentDTO);
    }

    @Override
    public PaymentDTO getById(int id) {
        return paymentDAO.getById(id);
    }

    @Override
    public List<PaymentDTO> getAll() {
        return paymentDAO.getAll();
    }

    @Override
    public List<PaymentDTO> getAllByAccount(AccountDTO accountDTO) {
        return paymentDAO.getAllByAccount(accountDTO);
    }

    @Override
    public List<PaymentDTO> getAllBy1Year() {
        return paymentDAO.getAllBy1Year();
    }

    @Override
    public Map<LocalDate, List<PaymentDTO>> get1YearPayment() {
        LinkedHashMap<LocalDate, List<PaymentDTO>> datesMap = new LinkedHashMap<>();
        LocalDate ago1Year = LocalDate.now().minusYears(1).withDayOfMonth(1);

        // datesMap 1년치 초기화
        for (
                LocalDate date = ago1Year;
                !date.isAfter(LocalDate.now());
                date = date.plusMonths(1)
        ) {
            datesMap.put(date, new ArrayList<>());
        }

        // 1년치 데이터 가져오기
        List<PaymentDTO> paymentDTOList = getAllBy1Year();
        if (paymentDTOList == null || paymentDTOList.isEmpty()) return datesMap;

        // datesMap에 데이터 넣기
        paymentDTOList.forEach(paymentDTO -> {
            LocalDate date = LocalDate.from(paymentDTO.getDateTime()).withDayOfMonth(1);
            datesMap.get(date).add(paymentDTO);
        });

        return datesMap;
    }

    @Override
    public void setTicketMap(ModelMap modelMap, HttpSession session) {
        List<String> ticketList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();
        List<Integer> priceList = new ArrayList<>();

        ticketMap.forEach((key, value) -> {
            switch (key) {
                case "timeTicket":
                    ticketList.add("시간권");
                    TimeTicketDTO timeTicket = (TimeTicketDTO) value;
                    timeList.add(timeTicket.getTime() + "시간");
                    priceList.add(timeTicket.getPrice());
                    break;
                case "commutationTicket":
                    ticketList.add("정기권");
                    CommutationTicketDTO commutationTicket = (CommutationTicketDTO) value;
                    timeList.add(commutationTicket.getDay() + "일");
                    priceList.add(commutationTicket.getPrice());
                    break;
                case "room":
                    ticketList.add("룸");
                    timeList.add(value + "시간");
                    int roomNumber = (int) session.getAttribute("selectNumber");
                    RoomDTO roomDTO = roomService.getByRoomNumber(roomNumber);
                    priceList.add(((Integer) value) * roomDTO.getRoomType_price());
                    break;
                case "lockerTicket":
                    ticketList.add("사물함");
                    LockerTicketDTO lockerTicket = (LockerTicketDTO) value;
                    timeList.add(lockerTicket.getDay() + "일");
                    priceList.add(lockerTicket.getPrice());
                    break;
            }
        });

        modelMap.addAttribute("ticketList", ticketList);
        modelMap.addAttribute("timeList", timeList);
        modelMap.addAttribute("priceList", priceList);
        modelMap.addAttribute("totalPrice", priceList.stream().mapToInt(Integer::intValue).sum());
    }
}
