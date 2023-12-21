package com.example.poasystentrekrutacji.entity;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import com.example.poasystentrekrutacji.dto.kierunek.RegulaWskaznikaRekrutacyjnegoDTO;
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

    public static RegulaWskaznikaRekrutacyjnego of(RegulaWskaznikaRekrutacyjnegoDTO ruleDTO) {
        return RegulaWskaznikaRekrutacyjnego.builder()
                .przedmiot(ruleDTO.przedmiot())
                .stopienMatury(ruleDTO.stopienMatury())
                .waga(ruleDTO.waga())
                .build();
    }
}
