package com.example.poasystentrekrutacji.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
public class Rekrutacja {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dataRozpoczecia;
    private LocalDate dataZakonczenia;
    private Integer limitOsob;
    private Integer liczbaKandydatow;
    private Double liczbaKatndydatowNaMiejsce;
    private Double sredniWskaznikRekrutacyjny;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "raport")
    private List<AplikacjaNaKierunek> aplikacjaNaKierunek;
    @OneToOne
    @JsonBackReference
    private Kierunek kierunek;
}
