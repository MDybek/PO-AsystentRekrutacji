package com.example.poasystentrekrutacji.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "daneRekrutacyjne")
//    private AplikacjaNaKierunek aplikacjaNaKierunek;
}
