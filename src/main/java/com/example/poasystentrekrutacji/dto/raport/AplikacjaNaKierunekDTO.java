package com.example.poasystentrekrutacji.dto.raport;

import com.example.poasystentrekrutacji.entity.DaneRekrutacyjne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToOne;

import java.util.List;

public record AplikacjaNaKierunekDTO(Double wskaznikRekrutacyjny,
                                      List<Integer> liczbaPunktowZaZadanieRekrutacyjne,
                                      String imie,
                                      String nazwisko,
                                      DaneRekrutacyjneDTO daneRekrutacyjne) {
}