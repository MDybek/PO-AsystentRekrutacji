package com.example.poasystentrekrutacji.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @OneToOne
    @JoinColumn(name = "plan_studiow_id", referencedColumnName = "id")
    private PlanStudiow planStudiow;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kierunek", cascade = CascadeType.ALL)
    @Size(min = 3)
    private List<RegulaWskaznikaRekrutacyjnego> regulyWskaznikaRekrutacyjnego;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kierunek")
    private List<HonorowaneOsiagniecia> honorowaneOsiagniecia;
    @OneToOne
    @JoinColumn(name = "raport_id", referencedColumnName = "id")
    private Raport raport;
}
