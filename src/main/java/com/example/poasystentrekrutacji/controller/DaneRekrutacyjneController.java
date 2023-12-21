package com.example.poasystentrekrutacji.controller;

import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.DaneRekrutacyjneDTO;
import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.FormDataDTO;
import com.example.poasystentrekrutacji.service.DaneRekrutacyjneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dane-rekrutacyjne")
@RequiredArgsConstructor
public class DaneRekrutacyjneController {
    private final DaneRekrutacyjneService daneRekrutacyjneService;

    @GetMapping("/form-data")
    public ResponseEntity<FormDataDTO> getFormData() {
        return ResponseEntity.ok(daneRekrutacyjneService.getFormData());
    }

    @PostMapping("/register")
    public ResponseEntity<Long> registerRecruitmentData(@RequestBody DaneRekrutacyjneDTO daneRekrutacyjneDTO) {
        return ResponseEntity.ok(daneRekrutacyjneService.registerRecruitmentData(daneRekrutacyjneDTO));
    }
}
