package com.example.poasystentrekrutacji.dto.kierunek;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import com.example.poasystentrekrutacji.constant.StopienStudiow;
import lombok.Builder;

import java.util.List;

@Builder
public record RegisterKierunekFormDataDTO(List<String> dziedziny, List<String> wydzialy, List<StopienStudiow> stopnieStudiow,
                                          List<Przedmiot> przedmiotyMaturalne, List<StopienMatury> stopnieMatury,
                                          List<KierunekDTO> kierunki) {
}
