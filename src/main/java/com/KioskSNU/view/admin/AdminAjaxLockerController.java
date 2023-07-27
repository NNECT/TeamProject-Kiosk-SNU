package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.dto.UsageLockerDTO;
import com.KioskSNU.snu.service.LockerService;
import com.KioskSNU.snu.service.UsageLockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminAjaxLockerController {
    private final UsageLockerService usageLockerService;
    private final LockerService lockerService;

    // 사물함 전체
    @RequestMapping("/ajax/getLocker")
    @AdminLoginRequired
    public ResponseEntity<Map<String,Object>> process(@RequestBody Map<String,String> map){

        int id = Integer.parseInt(map.get("lockerId"));
        UsageLockerDTO usageLockerDTO = usageLockerService.getById(id);
        LockerDTO lockerDTO = lockerService.getById(id);

        Map<String, Object> resultMap = new HashMap<>();

        if(usageLockerDTO!=null){
            resultMap.put("memberId",usageLockerDTO.getAccount_id());
            resultMap.put("memberName",usageLockerDTO.getAccount_username());
            resultMap.put("memberPhone",usageLockerDTO.getAccount_phoneNumber());
            resultMap.put("memberRemainTime",usageLockerDTO.getAccount_remainTime());
            resultMap.put("lockerActivation",lockerDTO.isUsable());
        }else{
            resultMap.put("memberId","");
            resultMap.put("memberName","");
            resultMap.put("memberPhone","");
            resultMap.put("memberRemainTime","");
            resultMap.put("lockerActivation",lockerDTO.isUsable());
        }
        System.out.println(lockerDTO.isUsable());
        resultMap.put("result","success");
        return ResponseEntity.ok(resultMap);
    }

    // 사물함 활성화
    @RequestMapping("/ajax/updateLockerUsable")
    @AdminLoginRequired
    public ResponseEntity<Map<String,String>> lockerUsable(@RequestBody Map<String,String> map){

        int lockerId = Integer.parseInt(map.get("lockerId"));
        boolean usable = Boolean.parseBoolean(map.get("usable"));

        LockerDTO lockerDTO = lockerService.getById(lockerId);
        lockerDTO.setUsable(usable);
        lockerService.update(lockerDTO);

        return ResponseEntity.ok(Map.of("result","success"));
    }

}
