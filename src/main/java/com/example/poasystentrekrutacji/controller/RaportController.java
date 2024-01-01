package com.example.poasystentrekrutacji.controller;

import com.example.poasystentrekrutacji.dto.raport.RaportDTO;
import com.example.poasystentrekrutacji.entity.PrzeprowadzoneRekrutacje;
import com.example.poasystentrekrutacji.service.RaportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/raport")
@RequiredArgsConstructor
public class RaportController {
    private final RaportService raportService;


    @GetMapping("/wypiszRekrutacjeDoRaportu")
    public ResponseEntity<List<PrzeprowadzoneRekrutacje>> wypiszRekrutacjeDoRaportu(Principal principal) {
        return ResponseEntity.ok(raportService.wypiszRekrutacjeDoRaportu(principal));
    }

    @GetMapping("/generujRaport")
    public ResponseEntity<RaportDTO> generateRaport(@RequestParam(value = "raportId") Long raportId, Principal principal) {
        return ResponseEntity.ok(raportService.generateRaport(raportId, principal));
    }
}
