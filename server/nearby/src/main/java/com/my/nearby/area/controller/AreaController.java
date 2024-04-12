package com.my.nearby.area.controller;

import com.my.nearby.area.model.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/area")
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;
    @GetMapping
    public ResponseEntity<?> getAreaList() {
        return ResponseEntity.ok(areaService.findAllArea());
    }
    @GetMapping("/{areaCode}/{sigunguCode}")
    public ResponseEntity<?> getArea(@PathVariable int areaCode, @PathVariable int sigunguCode) {
        return ResponseEntity.ok(areaService.findArea(areaCode, sigunguCode));
    }
}
