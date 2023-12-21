package com.example.poasystentrekrutacji.dto.kierunek;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;

public record RegulaWskaznikaRekrutacyjnegoDTO(Przedmiot przedmiot, StopienMatury stopienMatury, Double waga) {
}
