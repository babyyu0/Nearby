package com.ssafy.trip.area.controller;

import com.ssafy.trip.area.model.service.GugunService;
import com.ssafy.trip.global.util.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gugun")
@RequiredArgsConstructor
public class GugunController {
    private final GugunService gugunService;

    @GetMapping("refresh")
    public ResponseEntity<?> refreshGugun() throws MyException {
        return ResponseEntity.ok(gugunService.refreshGugun());
    }

    @GetMapping
    public ResponseEntity<?> getGugunList() throws MyException {
        return ResponseEntity.ok(gugunService.getGugunList());
    }

}
