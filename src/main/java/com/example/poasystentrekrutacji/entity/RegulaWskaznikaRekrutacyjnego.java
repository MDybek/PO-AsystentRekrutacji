package com.example.poasystentrekrutacji.entity;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class RegulaWskaznikaRekrutacyjnego {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Przedmiot przedmiot;
    private StopienMatury stopienMatury;
    private Double waga;
    @ManyToOne
    @JsonBackReference
    private Kierunek kierunek;
}
