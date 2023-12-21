package com.example.poasystentrekrutacji.controller;

import com.example.poasystentrekrutacji.dto.kierunek.RegisterKierunekRequest;
import com.example.poasystentrekrutacji.service.KierunekService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kierunek")
@RequiredArgsConstructor
public class KierunekController {
    private final KierunekService kierunekService;

    @PostMapping("/register")
    public ResponseEntity<Long> registerKierunek(@RequestBody RegisterKierunekRequest request) {
        return ResponseEntity.ok(kierunekService.registerKierunek(request));
    }
}
