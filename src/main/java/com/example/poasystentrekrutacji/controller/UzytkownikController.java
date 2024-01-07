package com.example.poasystentrekrutacji.controller;

import com.example.poasystentrekrutacji.dto.AuthToken;
import com.example.poasystentrekrutacji.dto.DaneRejestracyjneUzytkownika;
import com.example.poasystentrekrutacji.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/uzytkownik")
@RequiredArgsConstructor
public class UzytkownikController {
    private final AuthenticationService authenticationService;

    @PostMapping("/zarejestruj")
    public ResponseEntity<?> register(@RequestBody DaneRejestracyjneUzytkownika request) {
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}




