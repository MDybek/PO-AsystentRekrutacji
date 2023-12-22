package com.example.poasystentrekrutacji.dto.raport;

import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.UkonczoneStudiaDTO;
import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.WynikZMaturyDTO;
import com.example.poasystentrekrutacji.dto.kierunek.HonorowaneOsiagnieciaDTO;
import com.example.poasystentrekrutacji.entity.DaneRekrutacyjne;
import com.example.poasystentrekrutacji.entity.HonorowaneOsiagniecia;
import com.example.poasystentrekrutacji.entity.UkonczoneStudia;
import com.example.poasystentrekrutacji.entity.WynikZMatury;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record DaneRekrutacyjneDTO(List<WynikZMaturyDTO> wynikiZMatury,
                                  List<UkonczoneStudiaDTO> ukonczoneStudia,
                                  List<HonorowaneOsiagnieciaDTO> honorowaneOsiagniecia) {
    public static DaneRekrutacyjneDTO of(DaneRekrutacyjne daneRekrutacyjne) {
        return DaneRekrutacyjneDTO.builder()
                .wynikiZMatury(convertWynikiZMatury(daneRekrutacyjne.getWynikiZMatury()))
                .ukonczoneStudia(convertUkonczoneStudia(daneRekrutacyjne.getUkonczoneStudia()))
                .honorowaneOsiagniecia(convertHonorowaneOsiagniecia(daneRekrutacyjne.getHonorowaneOsiagniecia()))
                .build();
    }

    private static List<WynikZMaturyDTO> convertWynikiZMatury(List<WynikZMatury> wynikiZMatury) {
        return wynikiZMatury.stream()
                .map(WynikZMaturyDTO::of)
                .collect(Collectors.toList());
    }

    private static List<UkonczoneStudiaDTO> convertUkonczoneStudia(List<UkonczoneStudia> ukonczoneStudias) {
        return ukonczoneStudias.stream()
                .map(UkonczoneStudiaDTO::of)
                .collect(Collectors.toList());
    }

    private static List<HonorowaneOsiagnieciaDTO> convertHonorowaneOsiagniecia(List<HonorowaneOsiagniecia> honorowaneOsiagniecia) {
        return honorowaneOsiagniecia.stream()
                .map(HonorowaneOsiagnieciaDTO::of)
                .collect(Collectors.toList());
    }
}
