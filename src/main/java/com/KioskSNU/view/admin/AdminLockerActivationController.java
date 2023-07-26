package com.KioskSNU.view.admin;

import com.KioskSNU.interceptor.AdminLoginRequired;
import com.KioskSNU.snu.dto.LockerDTO;
import com.KioskSNU.snu.service.LockerService;
import com.KioskSNU.snu.service.UsageLockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminLockerActivationController {
    private final LockerService lockerService;

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
