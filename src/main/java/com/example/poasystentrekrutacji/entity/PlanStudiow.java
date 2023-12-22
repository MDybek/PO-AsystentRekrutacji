package com.example.poasystentrekrutacji.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class PlanStudiow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "planStudiow", cascade = CascadeType.ALL)
    private List<PrzedmiotNaStudiach> przedmiotyNaStudiach;
}
