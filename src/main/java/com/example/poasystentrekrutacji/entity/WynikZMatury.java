package com.example.poasystentrekrutacji.entity;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.WynikZMaturyDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WynikZMatury {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Przedmiot przedmiot;
    private StopienMatury stopien;
    private int wartosc;
    @ManyToOne
    @JsonBackReference
    private DaneRekrutacyjne daneRekrutacyjne;

    public static WynikZMatury of(WynikZMaturyDTO wynikZMaturyDTO) {
        return WynikZMatury.builder()
                .przedmiot(wynikZMaturyDTO.przedmiot())
                .stopien(wynikZMaturyDTO.stopienMatury())
                .wartosc(wynikZMaturyDTO.wartosc())
                .build();
    }
}
