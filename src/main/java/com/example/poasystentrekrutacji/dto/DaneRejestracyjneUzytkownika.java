package com.example.poasystentrekrutacji.dto;

import com.example.poasystentrekrutacji.constant.Plec;
import com.example.poasystentrekrutacji.entity.DaneUzytkownika;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DaneRejestracyjneUzytkownika {
    private final String imie;
    private final String nazwisko;
    private final String email;
    private final String haslo;
    private final String pesel;
    private final String numerTelefonu;
    private final LocalDate dataUrodzenia;
    private final LocalDate dataZalozeniaKonta;
    private final Plec plec;
}
