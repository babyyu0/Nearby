package com.my.nearby.area.controller;

import com.my.nearby.area.model.dto.AreaResponseDto;
import com.my.nearby.area.model.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/area")
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;

    @GetMapping
    public ResponseEntity<?> getAreaList() {
        return ResponseEntity.ok(areaService.findAllArea());
    }

    @GetMapping("refresh")
    public ResponseEntity<?> refreshAllArea() {
        List<AreaResponseDto> areaDtoList = areaService.refreshArea();
        for (AreaResponseDto areaDto : areaDtoList) {
            areaService.refreshSigungu(areaDto.areaCode());

        }
        return ResponseEntity.ok("");
    }
}
