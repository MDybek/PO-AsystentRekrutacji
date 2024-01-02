package com.example.poasystentrekrutacji.controller;

import com.example.poasystentrekrutacji.dto.kierunek.RegisterKierunekFormDataDTO;
import com.example.poasystentrekrutacji.dto.kierunek.RegisterKierunekRequest;
import com.example.poasystentrekrutacji.service.KierunekService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kierunek")
@RequiredArgsConstructor
public class KierunekController {
    private final KierunekService kierunekService;

    @PostMapping("/register")
    public ResponseEntity<Long> registerKierunek(@RequestBody RegisterKierunekRequest request) {
        return ResponseEntity.ok(kierunekService.registerKierunek(request));
    }

    @GetMapping("/register/form-data")
    public ResponseEntity<RegisterKierunekFormDataDTO> getFormData() {
        return ResponseEntity.ok(kierunekService.getRegisterKierunekFormData());
    }
}
