package com.example.poasystentrekrutacji.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Double minimalnyWskaznikRekrutacyjny;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rekrutacja")
    private List<AplikacjaNaKierunek> aplikacjaNaKierunek;
    @OneToOne
    @JsonBackReference
    private Kierunek kierunek;
}
