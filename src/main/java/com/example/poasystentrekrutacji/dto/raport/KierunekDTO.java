package com.example.poasystentrekrutacji.dto.raport;

import com.example.poasystentrekrutacji.dto.kierunek.HonorowaneOsiagnieciaDTO;
import com.example.poasystentrekrutacji.dto.kierunek.RegulaWskaznikaRekrutacyjnegoDTO;
import com.example.poasystentrekrutacji.entity.HonorowaneOsiagniecia;
import com.example.poasystentrekrutacji.entity.Kierunek;
import com.example.poasystentrekrutacji.entity.PrzedmiotNaStudiach;
import com.example.poasystentrekrutacji.entity.RegulaWskaznikaRekrutacyjnego;


import java.util.List;
import java.util.stream.Collectors;

public record KierunekDTO(String nazwa,
                          String opis,
                          String uwagiDoReguly,
                          List<PrzedmiotNaStudiachDTO> przedmioty,
                          List<RegulaWskaznikaRekrutacyjnegoDTO> regulyWskaznikaRekrutacyjnego,
                          List<HonorowaneOsiagnieciaDTO> honorowaneOsiagniecia) {
    public static KierunekDTO of(Kierunek kierunek) {
        return new KierunekDTO(kierunek.getNazwa(), kierunek.getOpis(), kierunek.getUwagiDoReguly(),
                convertPrzedmiotyNaStudiach(kierunek.getPrzedmiotyNaStudiach()),
                convertRegulyWskaznikaRekrutacyjnego(kierunek.getRegulyWskaznikaRekrutacyjnego()),
                convertHonorowaneOsiagniecia(kierunek.getHonorowaneOsiagniecia()));
    }

    private static List<RegulaWskaznikaRekrutacyjnegoDTO> convertRegulyWskaznikaRekrutacyjnego(List<RegulaWskaznikaRekrutacyjnego> regulyWskaznikaRekrutacyjnego) {
        return regulyWskaznikaRekrutacyjnego.stream()
                .map(RegulaWskaznikaRekrutacyjnegoDTO::of)
                .collect(Collectors.toList());
    }

    private static List<HonorowaneOsiagnieciaDTO> convertHonorowaneOsiagniecia(List<HonorowaneOsiagniecia> honorowaneOsiagniecia) {
        return honorowaneOsiagniecia.stream()
                .map(HonorowaneOsiagnieciaDTO::of)
                .collect(Collectors.toList());
    }

    private static List<PrzedmiotNaStudiachDTO> convertPrzedmiotyNaStudiach(List<PrzedmiotNaStudiach> przedmiotyNaStudiach) {
        return przedmiotyNaStudiach.stream()
                .map(PrzedmiotNaStudiachDTO::of)
                .collect(Collectors.toList());
    }
}