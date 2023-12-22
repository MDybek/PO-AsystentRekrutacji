package com.example.poasystentrekrutacji.dto.raport;

import com.example.poasystentrekrutacji.entity.PlanStudiow;
import com.example.poasystentrekrutacji.entity.PrzedmiotNaStudiach;

import java.util.List;
import java.util.stream.Collectors;

public record PlanStudiowDTO(List<PrzedmiotNaStudiachDTO> przedmiotyNaStudiach) {
    public static PlanStudiowDTO of(PlanStudiow planStudiow) {
        return new PlanStudiowDTO(convertPrzedmiotyNaStudiach(planStudiow.getPrzedmiotyNaStudiach()));
    }

    private static List<PrzedmiotNaStudiachDTO> convertPrzedmiotyNaStudiach(List<PrzedmiotNaStudiach> przedmiotyNaStudiach) {
        return przedmiotyNaStudiach.stream()
                .map(PrzedmiotNaStudiachDTO::of)
                .collect(Collectors.toList());
    }
}

