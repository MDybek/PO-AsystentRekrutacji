package com.example.poasystentrekrutacji.entity;


import com.example.poasystentrekrutacji.constant.OcenaKoncowa;
import com.example.poasystentrekrutacji.constant.StopienStudiow;
import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.UkonczoneStudiaDTO;
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
public class UkonczoneStudia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazwaUczelni;
    private StopienStudiow stopienStudiow;
    private Double sredniaZCalychStudiow;
    private OcenaKoncowa ocenaKoncowa;
    private String kierunek;
    private String wydzial;
    @ManyToOne
    @JsonBackReference
    private DaneRekrutacyjne daneRekrutacyjne;

    public static UkonczoneStudia of(UkonczoneStudiaDTO ukonczoneStudiaDTO) {
        return UkonczoneStudia.builder()
                .nazwaUczelni(ukonczoneStudiaDTO.nazwaUczelni())
                .stopienStudiow(ukonczoneStudiaDTO.stopienStudiow())
                .sredniaZCalychStudiow(ukonczoneStudiaDTO.sredniaZCalychStudiow())
                .ocenaKoncowa(ukonczoneStudiaDTO.ocenaKoncowa())
                .kierunek(ukonczoneStudiaDTO.kierunek())
                .wydzial(ukonczoneStudiaDTO.wydzial())
                .build();
    }
}
