package com.example.poasystentrekrutacji.entity;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WynikZMatury {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Przedmiot przedmiot;
    private StopienMatury stopien;
    private int wartosc;
}
