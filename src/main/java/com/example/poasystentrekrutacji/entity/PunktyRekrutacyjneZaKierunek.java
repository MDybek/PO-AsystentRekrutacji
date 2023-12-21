package com.example.poasystentrekrutacji.entity;

import com.example.poasystentrekrutacji.dto.kierunek.PunktyRekrutacyjneZaKierunekDTO;
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
public class PunktyRekrutacyjneZaKierunek {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int liczbaPunktow;
    @OneToOne
    @JoinColumn(name = "kierunek_id", referencedColumnName = "id")
    private Kierunek kierunek;

    public static PunktyRekrutacyjneZaKierunek of(PunktyRekrutacyjneZaKierunekDTO pointsDTO) {
        return PunktyRekrutacyjneZaKierunek.builder()
                .liczbaPunktow(pointsDTO.liczbaPunktow())
                .build();
    }
}
