package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dao.UsageLockerDAO;
import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.service.UsageLockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminAjaxGetLocker {
    private final UsageLockerService usageLockerService;

    @RequestMapping("/ajax/getLocker")
    @AdminLoginRequired
    public ResponseEntity<Map<String,Object>> process(@RequestBody Map<String,String> map){

        int id = Integer.parseInt(map.get("lockerId"));
        UsageLockerDTO usageLockerDTO = usageLockerService.getById(id);

        Map<String, Object> resultMap = new HashMap<>();

        if(usageLockerDTO!=null){
            resultMap.put("memberId",usageLockerDTO.getAccount_id());
            resultMap.put("memberName",usageLockerDTO.getAccount_username());
            resultMap.put("memberPhone",usageLockerDTO.getAccount_phoneNumber());
            resultMap.put("memberRemainTime",usageLockerDTO.getAccount_remainTime());
        }else{
            resultMap.put("memberId","");
            resultMap.put("memberName","");
            resultMap.put("memberPhone","");
            resultMap.put("memberRemainTime","");
        }
        resultMap.put("result","success");
        return ResponseEntity.ok(resultMap);
    }

}
