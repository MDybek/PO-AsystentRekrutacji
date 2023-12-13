package com.example.poasystentrekrutacji.entity;


import com.example.poasystentrekrutacji.constant.StopienStudiow;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class UkonczoneStudia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazwaUczelni;
    private StopienStudiow stopienStudiow;
    private Double sredniaZCalychStudiow;
    @ManyToOne
    @JsonBackReference
    private DaneRekrutacyjne daneRekrutacyjne;
}
