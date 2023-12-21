package com.example.poasystentrekrutacji.dto.kierunek;

import java.util.List;

public record RegisterKierunekRequest(String nazwa,
                                      String opis,
                                      List<RegulaWskaznikaRekrutacyjnegoDTO> regulyWskaznikaRekrutacyjnego,
                                      List<HonorowaneOsiagnieciaDTO> honorowaneOsiagniecia,
                                      List<PunktyRekrutacyjneZaKierunekDTO> punktyRekrutacyjneZaKierunki) {
}
