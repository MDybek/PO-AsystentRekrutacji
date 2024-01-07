package com.example.poasystentrekrutacji.dto.kierunek;

import com.example.poasystentrekrutacji.entity.HonorowaneOsiagniecia;
import lombok.Builder;

@Builder
public record HonorowaneOsiagnieciaDTO(String nazwa, String opis, int liczbaPunktow) {
    public static HonorowaneOsiagnieciaDTO of(HonorowaneOsiagniecia honorowaneOsiagniecia) {
        return new HonorowaneOsiagnieciaDTO(honorowaneOsiagniecia.getNazwa(), honorowaneOsiagniecia.getOpis(),
                honorowaneOsiagniecia.getLiczbaPunktow());
    }
}
