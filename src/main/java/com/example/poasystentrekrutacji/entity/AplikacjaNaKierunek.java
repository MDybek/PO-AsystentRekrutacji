package com.example.poasystentrekrutacji.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class AplikacjaNaKierunek {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double wskaznikRekrutacyjny;
    @ElementCollection
    private List<Integer> liczbaPunktowZaZadanieRekrutacyjne;
    private String imie;
    private String nazwisko;
    @OneToOne
    @JsonBackReference
    private DaneRekrutacyjne daneRekrutacyjne;
    @ManyToOne
    @JsonBackReference
    private Raport raport;
}
