package com.example.poasystentrekrutacji.dto.kierunek;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import com.example.poasystentrekrutacji.entity.RegulaWskaznikaRekrutacyjnego;
import lombok.Builder;

import java.util.List;

@Builder
public record RegulaWskaznikaRekrutacyjnegoDTO(Przedmiot przedmiot, StopienMatury stopienMatury, Double waga) {
    public static RegulaWskaznikaRekrutacyjnegoDTO of(RegulaWskaznikaRekrutacyjnego regulyWskaznikaRekrutacyjnego) {
        return RegulaWskaznikaRekrutacyjnegoDTO.builder()
                .przedmiot(regulyWskaznikaRekrutacyjnego.getPrzedmiot())
                .stopienMatury(regulyWskaznikaRekrutacyjnego.getStopienMatury())
                .waga(regulyWskaznikaRekrutacyjnego.getWaga())
                .build();
    }
}
