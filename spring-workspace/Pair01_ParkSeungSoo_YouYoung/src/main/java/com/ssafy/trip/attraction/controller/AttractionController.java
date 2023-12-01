package com.ssafy.trip.attraction.controller;

import com.ssafy.trip.attraction.model.service.AttractionService;
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

    @GetMapping("popular")
    public ResponseEntity<?> getPopularAttraction() {
        return ResponseEntity.ok(attractionService.getPopularAttraction());
    }
}
