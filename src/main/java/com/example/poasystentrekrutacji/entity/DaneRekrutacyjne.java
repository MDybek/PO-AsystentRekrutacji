package com.example.poasystentrekrutacji.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class DaneRekrutacyjne {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "daneRekrutacyjne")
    private List<WynikZMatury> wynikiZMatury;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "daneRekrutacyjne")
    private List<UkonczoneStudia> ukonczoneStudia;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "daneRekrutacyjne")
    private List<HonorowaneOsiagniecia> honorowaneOsiagniecia;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "daneRekrutacyjne")
    private AplikacjaNaKierunek aplikacjaNaKierunek;
}
