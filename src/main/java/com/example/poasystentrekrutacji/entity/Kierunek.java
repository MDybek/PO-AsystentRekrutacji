package com.example.poasystentrekrutacji.entity;

import com.example.poasystentrekrutacji.constant.StopienStudiow;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private String dziedzina;
    private String wydzial;
    private StopienStudiow stopienStudiow;
    private String uwagiDoReguly;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kierunek", cascade = CascadeType.ALL)
    private List<PrzedmiotNaStudiach> przedmiotyNaStudiach;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kierunek", cascade = CascadeType.ALL)
    @Size(min = 3)
    private List<RegulaWskaznikaRekrutacyjnego> regulyWskaznikaRekrutacyjnego;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kierunek")
    private List<HonorowaneOsiagniecia> honorowaneOsiagniecia;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kierunek")
    private List<PunktyRekrutacyjneZaKierunek> punktyRekrutacyjneZaKierunek;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "kierunek")
    private Rekrutacja rekrutacja;
}
