package com.example.poasystentrekrutacji.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Kierunek {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazwa;
    private String opis;
    private String uwagiDoReguly;

    @OneToOne
    @JoinColumn(name = "plan_studiow_id", referencedColumnName = "id")
    private PlanStudiow planStudiow;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kierunek", cascade = CascadeType.ALL)
    @Size(min = 3)
    private List<RegulaWskaznikaRekrutacyjnego> regulyWskaznikaRekrutacyjnego;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kierunek")
    private List<HonorowaneOsiagniecia> honorowaneOsiagniecia;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kierunek")
//    private List<PunktyRekrutacyjneZaKierunek> punktyRekrutacyjneZaKierunek;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "kierunek")
    private Rekrutacja rekrutacja;
}
