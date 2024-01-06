package com.example.poasystentrekrutacji.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrzedmiotNaStudiach {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer semestr;
    private String opis;
    private Integer liczbaECTS;
    private String nazwa;
    @ManyToOne
    @JsonBackReference
    private Kierunek kierunek;
}
