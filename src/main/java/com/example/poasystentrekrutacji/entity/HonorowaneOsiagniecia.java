package com.example.poasystentrekrutacji.entity;

import com.example.poasystentrekrutacji.dto.kierunek.HonorowaneOsiagnieciaDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    public static HonorowaneOsiagniecia of(HonorowaneOsiagnieciaDTO achievementDTO) {
        return HonorowaneOsiagniecia.builder()
                .nazwa(achievementDTO.nazwa())
                .opis(achievementDTO.opis())
                .liczbaPunktow(achievementDTO.liczbaPunktow())
                .build();
    }
}
