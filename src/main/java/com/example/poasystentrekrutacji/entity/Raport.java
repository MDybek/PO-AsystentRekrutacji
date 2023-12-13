package com.example.poasystentrekrutacji.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Raport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dataRozpoczecia;
    private LocalDate dataZakonczenia;
    private Integer limitOsob;
    private Integer liczbaKandydatow;
    private Double liczbaKatndydatowNaMiejsce;
    private Double liczbaKandydatowNaMiejsce;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "raport")
    @JsonBackReference
    private List<AplikacjaNaKierunek> aplikacjaNaKierunek;
    @OneToOne
    @JsonBackReference
    private Kierunek kierunek;
}
