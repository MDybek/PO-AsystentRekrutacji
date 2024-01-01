package com.example.poasystentrekrutacji.dto.daneRekrutacyjne;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import com.example.poasystentrekrutacji.entity.WynikZMatury;

import java.util.List;

public record WynikZMaturyDTO(Przedmiot przedmiot, StopienMatury stopienMatury, int wartosc) {
    public static WynikZMaturyDTO of(WynikZMatury wynikiZMatury) {
        return new WynikZMaturyDTO(wynikiZMatury.getPrzedmiot(), wynikiZMatury.getStopien(), wynikiZMatury.getWartosc());
    }
}
