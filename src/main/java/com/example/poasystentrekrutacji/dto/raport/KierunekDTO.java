package com.example.poasystentrekrutacji.dto.raport;

import com.example.poasystentrekrutacji.dto.kierunek.HonorowaneOsiagnieciaDTO;
import com.example.poasystentrekrutacji.dto.kierunek.RegulaWskaznikaRekrutacyjnegoDTO;
import com.example.poasystentrekrutacji.entity.HonorowaneOsiagniecia;


import java.util.List;

public record KierunekDTO(String nazwa,
                          String opis,
                          String uwagiDoReguly,
                          PlanStudiowDTO planStudiow,
                          List<RegulaWskaznikaRekrutacyjnegoDTO> regulyWskaznikaRekrutacyjnego,
                          List<HonorowaneOsiagnieciaDTO> honorowaneOsiagniecia) {
}