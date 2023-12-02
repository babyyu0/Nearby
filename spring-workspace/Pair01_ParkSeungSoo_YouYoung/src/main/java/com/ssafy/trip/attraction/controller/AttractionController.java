package com.ssafy.trip.attraction.controller;

import com.ssafy.trip.attraction.model.service.AttractionService;
import com.ssafy.trip.global.util.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attraction")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping("content-type/refresh")
    public ResponseEntity<?> refreshContentType() throws MyException {
        return ResponseEntity.ok(attractionService.refreshContentType());
    }
    @GetMapping("popular")
    public ResponseEntity<?> getPopularAttraction() {
        return ResponseEntity.ok(attractionService.getPopularAttraction());
    }
}
