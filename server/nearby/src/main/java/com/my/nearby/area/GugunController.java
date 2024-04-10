package com.my.nearby.area;

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
    @GetMapping
    public ResponseEntity<?> getGugunList() {
        return ResponseEntity.ok(gugunService.findAllGugun());
    }
}
