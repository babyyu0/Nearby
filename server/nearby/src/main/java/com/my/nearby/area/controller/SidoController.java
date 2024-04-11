package com.my.nearby.area.controller;

import com.my.nearby.area.model.service.SidoService;
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
    @GetMapping
    public ResponseEntity<?> getSidoList() {
        return ResponseEntity.ok(sidoService.findAllSido());
    }
}
