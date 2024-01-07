package com.example.poasystentrekrutacji.service;

import com.example.poasystentrekrutacji.dto.AuthToken;
import com.example.poasystentrekrutacji.dto.DaneRejestracyjneUzytkownika;
import com.example.poasystentrekrutacji.entity.DaneUzytkownika;
import com.example.poasystentrekrutacji.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthToken register(DaneRejestracyjneUzytkownika request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent())
            throw new RuntimeException("User with given email already exists");
        if (userRepository.findByPesel(request.getPesel()).isPresent())
            throw new RuntimeException("User with given pesel already exists");
        if (userRepository.findByNumerTelefonu(request.getNumerTelefonu()).isPresent())
            throw new RuntimeException("User with given telephone number already exists");

        var user = userRepository.save(DaneUzytkownika.of(request));

        return new AuthToken(jwtService.generateToken(user));
    }

}
