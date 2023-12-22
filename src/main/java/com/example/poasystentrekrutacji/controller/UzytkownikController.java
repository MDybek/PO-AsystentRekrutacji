package com.example.poasystentrekrutacji.controller;

import com.example.poasystentrekrutacji.dto.AuthToken;
import com.example.poasystentrekrutacji.dto.DaneRejestracyjneUzytkownika;
import com.example.poasystentrekrutacji.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<AuthToken> register(@RequestBody DaneRejestracyjneUzytkownika request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
}




