package com.example.poasystentrekrutacji.dto.daneRekrutacyjne;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import com.example.poasystentrekrutacji.constant.StopienStudiow;
import com.example.poasystentrekrutacji.entity.HonorowaneOsiagniecia;
import lombok.Builder;

import java.util.List;

@Builder
public record FormDataDTO(List<Przedmiot> przedmiotyMaturalne,
                          List<StopienMatury> stopnieMatury,
                          List<StopienStudiow> stopnieStudiow,
                          List<HonorowaneOsiagniecia> honorowaneOsiagniecia) {
}
