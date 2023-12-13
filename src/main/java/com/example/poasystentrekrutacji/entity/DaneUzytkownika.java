package com.example.poasystentrekrutacji.entity;

import com.example.poasystentrekrutacji.constant.Plec;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class DaneUzytkownika {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imie;
    private String nazwisko;
    private String email;
    private String haslo;
    private String pesel;
    private String numerTelefonu;
    private LocalDate dataUrodzenia;
    private LocalDate dataZalozeniaKonta;
    private Plec plec;
}
