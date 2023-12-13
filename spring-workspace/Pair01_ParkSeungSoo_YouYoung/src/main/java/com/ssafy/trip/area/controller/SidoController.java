package com.ssafy.trip.area.controller;

import com.ssafy.trip.area.model.service.SidoService;
import com.ssafy.trip.global.util.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sido")
@RequiredArgsConstructor
public class SidoController {
    private final SidoService sidoService;

    @GetMapping("refresh")
    public ResponseEntity<?> refreshSido() throws MyException {
        return ResponseEntity.ok(sidoService.refreshSido());
    }

    @GetMapping
    public ResponseEntity<?> getSidoList() throws MyException {
        return ResponseEntity.ok(sidoService.getSidoList());
    }
}
