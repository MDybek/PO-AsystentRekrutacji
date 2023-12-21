package com.example.poasystentrekrutacji.dto.daneRekrutacyjne;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;

public record WynikZMaturyDTO(Przedmiot przedmiot, StopienMatury stopienMatury, int wartosc) {
}
