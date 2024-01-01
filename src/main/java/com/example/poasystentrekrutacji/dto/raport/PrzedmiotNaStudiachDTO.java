package com.example.poasystentrekrutacji.dto.raport;

import com.example.poasystentrekrutacji.entity.PrzedmiotNaStudiach;

public record PrzedmiotNaStudiachDTO(Integer semestr,
                                     String opis,
                                     Integer liczbaECTS,
                                     String nazwa) {
    public static PrzedmiotNaStudiachDTO of(PrzedmiotNaStudiach przedmiotNaStudiach) {
        return new PrzedmiotNaStudiachDTO(przedmiotNaStudiach.getSemestr(), przedmiotNaStudiach.getOpis(),
                przedmiotNaStudiach.getLiczbaECTS(), przedmiotNaStudiach.getNazwa());
    }
}