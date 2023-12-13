package com.example.poasystentrekrutacji.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Kierunek {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazwa;
    private String uwagiDoReguly;
    private String opisReguly;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kierunek", cascade = CascadeType.ALL)
    @Size(min = 3)
    private List<RegulaWskaznikaRekrutacyjnego> regulyWskaznikaRekrutacyjnego;
}
