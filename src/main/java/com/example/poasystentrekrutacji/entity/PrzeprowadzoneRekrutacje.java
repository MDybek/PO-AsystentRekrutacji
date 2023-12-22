package com.example.poasystentrekrutacji.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class PrzeprowadzoneRekrutacje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer rok;
    private String nazwaKierunku;
    private Long rekruterId;
    private Long rekrutacjaId;
}
