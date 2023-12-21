package com.example.poasystentrekrutacji.dto.daneRekrutacyjne;

import com.example.poasystentrekrutacji.constant.OcenaKoncowa;
import com.example.poasystentrekrutacji.constant.StopienStudiow;

public record UkonczoneStudiaDTO(String nazwaUczelni, StopienStudiow stopienStudiow, Double sredniaZCalychStudiow,
                                 OcenaKoncowa ocenaKoncowa, String kierunek, String wydzial) {
}
