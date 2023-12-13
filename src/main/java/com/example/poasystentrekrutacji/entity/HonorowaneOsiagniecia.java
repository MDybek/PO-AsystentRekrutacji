package com.example.poasystentrekrutacji.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class HonorowaneOsiagniecia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazwa;
    private String opis;
    private int liczbaPunktow;
    @ManyToOne
    @JsonBackReference
    private Kierunek kierunek;
    @ManyToMany
    private List<DaneRekrutacyjne> daneRekrutacyjne;
}
