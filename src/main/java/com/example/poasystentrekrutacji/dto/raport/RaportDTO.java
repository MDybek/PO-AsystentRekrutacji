package com.example.poasystentrekrutacji.dto.raport;

import com.example.poasystentrekrutacji.entity.AplikacjaNaKierunek;
import com.example.poasystentrekrutacji.entity.Kierunek;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.util.List;

public record RaportDTO(LocalDate dataRozpoczecia,
                        LocalDate dataZakonczenia,
                        Integer limitOsob,
                        Integer liczbaKandydatow,
                        Double liczbaKatndydatowNaMiejsce,
                        Double liczbaKandydatowNaMiejsce,
                        List<AplikacjaNaKierunekDTO> aplikacjaNaKierunek,
                        KierunekDTO kierunek) {

}
