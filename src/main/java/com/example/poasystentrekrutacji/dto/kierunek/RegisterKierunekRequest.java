package com.example.poasystentrekrutacji.dto.kierunek;

import com.example.poasystentrekrutacji.constant.StopienStudiow;

import java.util.List;

public record RegisterKierunekRequest(String nazwa,
                                      String opis,
                                      String dziedzina,
                                      String wydzial,
                                      StopienStudiow stopienStudiow,
                                      List<RegulaWskaznikaRekrutacyjnegoDTO> regulyWskaznikaRekrutacyjnego,
                                      List<HonorowaneOsiagnieciaDTO> honorowaneOsiagniecia,
                                      List<PunktyRekrutacyjneZaKierunekDTO> punktyRekrutacyjneZaKierunki) {
}
