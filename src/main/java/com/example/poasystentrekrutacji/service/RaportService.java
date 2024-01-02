package com.example.poasystentrekrutacji.service;

import com.example.poasystentrekrutacji.dto.raport.AplikacjaNaKierunekDTO;
import com.example.poasystentrekrutacji.dto.raport.KierunekDTO;
import com.example.poasystentrekrutacji.dto.raport.PrzeprowadzoneRekrutacjeWithIdDTO;
import com.example.poasystentrekrutacji.dto.raport.RaportDTO;
import com.example.poasystentrekrutacji.entity.AplikacjaNaKierunek;
import com.example.poasystentrekrutacji.repository.PrzeprowadzoneRekrutacjeRepository;
import com.example.poasystentrekrutacji.repository.RekrutacjaRepository;
import com.example.poasystentrekrutacji.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RaportService {
    private final UserRepository userRepository;
    private final PrzeprowadzoneRekrutacjeRepository przeprowadzoneRekrutacjeRepository;
    private final RekrutacjaRepository rekrutacjaRepository;

    public List<PrzeprowadzoneRekrutacjeWithIdDTO> wypiszRekrutacjeDoRaportu(Long userId) {
        return przeprowadzoneRekrutacjeRepository.findAllByrekruterId(userId);
    }

    public RaportDTO generateRaport(Long raportId) {
        if (!przeprowadzoneRekrutacjeRepository.existsById(raportId)) {
            throw new RuntimeException("Raport with given id not found");
        }
        return generateRaportEngine(raportId);
    }

    private RaportDTO generateRaportEngine(Long raportId) {
        val przeprowadzonaRekrutacja = przeprowadzoneRekrutacjeRepository.findById(raportId).orElseThrow();
        val raport = rekrutacjaRepository.findById(przeprowadzonaRekrutacja.getRekrutacjaId()).orElseThrow();

        return RaportDTO.builder()
                .dataRozpoczecia(raport.getDataRozpoczecia())
                .dataZakonczenia(raport.getDataZakonczenia())
                .limitOsob(raport.getLimitOsob())
                .liczbaKandydatow(raport.getLiczbaKandydatow())
                .liczbaKatndydatowNaMiejsce(raport.getLiczbaKatndydatowNaMiejsce())
                .sredniWskaznikRekrutacyjny(raport.getSredniWskaznikRekrutacyjny())
                .aplikacjaNaKierunek(getAplikacjeNaKierunek(raport.getAplikacjaNaKierunek()))
                .kierunek(KierunekDTO.of(raport.getKierunek()))
                .build();
    }

    private List<AplikacjaNaKierunekDTO> getAplikacjeNaKierunek(List<AplikacjaNaKierunek> aplikacjaNaKierunek) {
        return aplikacjaNaKierunek.stream().map(AplikacjaNaKierunekDTO::of).toList();
    }
}
