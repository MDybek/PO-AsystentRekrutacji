package com.example.poasystentrekrutacji.dto.raport;

import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.UkonczoneStudiaDTO;
import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.WynikZMaturyDTO;
import com.example.poasystentrekrutacji.dto.kierunek.HonorowaneOsiagnieciaDTO;

import java.util.List;

public record DaneRekrutacyjneDTO(List<WynikZMaturyDTO> wynikiZMatury,
                                  List<UkonczoneStudiaDTO> ukonczoneStudia,
                                  List<HonorowaneOsiagnieciaDTO> honorowaneOsiagniecia) {
}
