package com.example.poasystentrekrutacji.utils.validator.kierunek;

import com.example.poasystentrekrutacji.constant.StopienStudiow;
import com.example.poasystentrekrutacji.dto.kierunek.HonorowaneOsiagnieciaDTO;
import com.example.poasystentrekrutacji.dto.kierunek.PunktyRekrutacyjneZaKierunekDTO;
import com.example.poasystentrekrutacji.dto.kierunek.RegisterKierunekRequest;
import com.example.poasystentrekrutacji.dto.kierunek.RegulaWskaznikaRekrutacyjnegoDTO;
import com.example.poasystentrekrutacji.repository.KierunekRepository;
import com.example.poasystentrekrutacji.utils.validator.CollectionValidationUtils;
import com.example.poasystentrekrutacji.utils.validator.ValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("strictStrategy")
public class StrictKierunekValidationStrategy implements ValidationStrategy<RegisterKierunekRequest> {

    private final KierunekRepository kierunekRepository;

    @Override
    public void validate(RegisterKierunekRequest entity) {
        checkIfKierunekAlreadyExists(entity.nazwa(), entity.wydzial(), entity.dziedzina(), entity.stopienStudiow());
        checkIfHonorowaneOsiagnieciaDoNotRepeat(entity.honorowaneOsiagniecia());
        checkIfRegulyWskaznikaRekrutacyjnegoDoNotRepeat(entity.regulyWskaznikaRekrutacyjnego());
        checkIfPunktyRekrutacyjneZaKierunkiDoNotRepeat(entity.punktyRekrutacyjneZaKierunki());
    }

    private void checkIfPunktyRekrutacyjneZaKierunkiDoNotRepeat(List<PunktyRekrutacyjneZaKierunekDTO> punktyRekrutacyjneZaKierunekDTOs) {
        if (CollectionValidationUtils.isCollectionNonUnique(punktyRekrutacyjneZaKierunekDTOs)) {
            throw new RuntimeException("Punkty rekrutacyjne za kierunki are not unique!");
        }
    }

    private void checkIfKierunekAlreadyExists(String nazwa, String wydzial, String dziedzina, StopienStudiow stopienStudiow) {
        if (kierunekRepository.findKieruneksByNazwaAndWydzialAndDziedzinaAndStopienStudiow(nazwa, wydzial, dziedzina, stopienStudiow).size() > 1) {
            throw new RuntimeException(String.format("Kierunek of name: %s and stopien: %s already exists!", nazwa, stopienStudiow));
        }
    }

    private void checkIfHonorowaneOsiagnieciaDoNotRepeat(List<HonorowaneOsiagnieciaDTO> honorowaneOsiagnieciaDTOs) {
        if (CollectionValidationUtils.isCollectionNonUnique(honorowaneOsiagnieciaDTOs)) {
            throw new RuntimeException("Honorowane osiągnięcia are not unique!");
        }
    }

    private void checkIfRegulyWskaznikaRekrutacyjnegoDoNotRepeat(List<RegulaWskaznikaRekrutacyjnegoDTO> regulaWskaznikaRekrutacyjnegoDTOs) {
        if (CollectionValidationUtils.isCollectionNonUnique(regulaWskaznikaRekrutacyjnegoDTOs)) {
            throw new RuntimeException("Reguły wskaźnika rekrutacyjnego are not unique!");
        }
    }
}
