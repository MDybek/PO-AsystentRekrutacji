package com.example.poasystentrekrutacji.controller;

import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.FormDataDTO;
import com.example.poasystentrekrutacji.service.DaneRekrutacyjneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dane-rekrutacyjne")
@RequiredArgsConstructor
public class DaneRekrutacyjneController {
    private final DaneRekrutacyjneService daneRekrutacyjneService;

    @GetMapping("/form-data")
    public ResponseEntity<FormDataDTO> getFormData() {
        return ResponseEntity.ok(daneRekrutacyjneService.getFormData());
    }
}
