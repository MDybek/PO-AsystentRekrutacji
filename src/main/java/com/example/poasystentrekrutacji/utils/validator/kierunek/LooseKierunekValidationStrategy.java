package com.example.poasystentrekrutacji.utils.validator.kierunek;

import com.example.poasystentrekrutacji.constant.StopienStudiow;
import com.example.poasystentrekrutacji.dto.kierunek.RegisterKierunekRequest;
import com.example.poasystentrekrutacji.repository.KierunekRepository;
import com.example.poasystentrekrutacji.utils.validator.ValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("looseStrategy")
public class LooseKierunekValidationStrategy implements ValidationStrategy<RegisterKierunekRequest> {

    private final KierunekRepository kierunekRepository;

    @Override
    public void validate(RegisterKierunekRequest entity) {
        checkIfKierunekAlreadyExists(entity.nazwa(), entity.wydzial(), entity.dziedzina(), entity.stopienStudiow());
    }

    private void checkIfKierunekAlreadyExists(String nazwa, String wydzial, String dziedzina, StopienStudiow stopienStudiow) {
        if (kierunekRepository.findKieruneksByNazwaAndWydzialAndDziedzinaAndStopienStudiow(nazwa, wydzial, dziedzina, stopienStudiow).size() > 1) {
            throw new RuntimeException(String.format("Kierunek of name: %s and stopien: %s already exists!", nazwa, stopienStudiow));
        }
    }
}
