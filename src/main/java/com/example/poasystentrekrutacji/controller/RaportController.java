package com.example.poasystentrekrutacji.controller;

import com.example.poasystentrekrutacji.dto.kierunek.RegisterKierunekRequest;
import com.example.poasystentrekrutacji.dto.raport.RaportDTO;
import com.example.poasystentrekrutacji.entity.PrzeprowadzoneRekrutacje;
import com.example.poasystentrekrutacji.entity.Raport;
import com.example.poasystentrekrutacji.service.KierunekService;
import com.example.poasystentrekrutacji.service.RaportService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.NotActiveException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

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
