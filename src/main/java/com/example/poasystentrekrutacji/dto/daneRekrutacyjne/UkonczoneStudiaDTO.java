package com.example.poasystentrekrutacji.dto.daneRekrutacyjne;

import com.example.poasystentrekrutacji.constant.OcenaKoncowa;
import com.example.poasystentrekrutacji.constant.StopienStudiow;
import com.example.poasystentrekrutacji.entity.UkonczoneStudia;

import java.util.List;

public record UkonczoneStudiaDTO(String nazwaUczelni, StopienStudiow stopienStudiow, Double sredniaZCalychStudiow,
                                 OcenaKoncowa ocenaKoncowa, String kierunek, String wydzial) {
    public static UkonczoneStudiaDTO of(UkonczoneStudia ukonczoneStudia) {
        return new UkonczoneStudiaDTO(ukonczoneStudia.getNazwaUczelni(), ukonczoneStudia.getStopienStudiow(),
                ukonczoneStudia.getSredniaZCalychStudiow(), ukonczoneStudia.getOcenaKoncowa(),
                ukonczoneStudia.getKierunek(), ukonczoneStudia.getWydzial());
    }
}
