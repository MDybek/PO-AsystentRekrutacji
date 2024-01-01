package com.example.poasystentrekrutacji.dto.raport;

import com.example.poasystentrekrutacji.entity.AplikacjaNaKierunek;
import com.example.poasystentrekrutacji.entity.DaneRekrutacyjne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToOne;
import lombok.Builder;

import java.util.List;

@Builder
public record AplikacjaNaKierunekDTO(Double wskaznikRekrutacyjny,
                                      List<Integer> liczbaPunktowZaZadanieRekrutacyjne,
                                      String imie,
                                      String nazwisko,
                                      DaneRekrutacyjneDTO daneRekrutacyjne) {
    public static AplikacjaNaKierunekDTO of(AplikacjaNaKierunek aplikacjaNaKierunek) {
        return AplikacjaNaKierunekDTO.builder()
                .wskaznikRekrutacyjny(aplikacjaNaKierunek.getWskaznikRekrutacyjny())
                .liczbaPunktowZaZadanieRekrutacyjne(aplikacjaNaKierunek.getLiczbaPunktowZaZadanieRekrutacyjne())
                .imie(aplikacjaNaKierunek.getImie())
                .nazwisko(aplikacjaNaKierunek.getNazwisko())
                .daneRekrutacyjne(DaneRekrutacyjneDTO.of(aplikacjaNaKierunek.getDaneRekrutacyjne()))
                .build();
    }
}