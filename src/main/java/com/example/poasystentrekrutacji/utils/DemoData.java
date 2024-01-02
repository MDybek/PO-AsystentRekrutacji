package com.example.poasystentrekrutacji.utils;

import com.example.poasystentrekrutacji.entity.PrzeprowadzonaRekrutacja;
import com.example.poasystentrekrutacji.repository.PrzeprowadzoneRekrutacjeRepository;
import com.example.poasystentrekrutacji.repository.WynikZMaturyRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DemoData {

    private final WynikZMaturyRepository wynikZMaturyRepository;
    private final PrzeprowadzoneRekrutacjeRepository przeprowadzoneRekrutacjeRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2022)
                .nazwaKierunku("Informatyka stosowana")
                .rekruterId(1L)
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2021)
                .nazwaKierunku("Informatyka stosowana")
                .rekruterId(1L)
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2020)
                .nazwaKierunku("Informatyka stosowana")
                .rekruterId(1L)
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2022)
                .nazwaKierunku("Informatyka algorytmiczna")
                .rekruterId(1L)
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2021)
                .nazwaKierunku("Informatyka algorytmiczna")
                .rekruterId(1L)
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2020)
                .nazwaKierunku("Informatyka algorytmiczna")
                .rekruterId(1L)
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2022)
                .nazwaKierunku("Informatyka techniczna")
                .rekruterId(1L)
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2021)
                .nazwaKierunku("Informatyka techniczna")
                .rekruterId(1L)
                .build());

//        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
//                .rok(2020)
//                .nazwaKierunku("Informatyka techniczna")
//                .rekruterId(1L)
//                .build());
    }
}