package com.example.poasystentrekrutacji.service;

import com.example.poasystentrekrutacji.dto.raport.AplikacjaNaKierunekDTO;
import com.example.poasystentrekrutacji.dto.raport.KierunekDTO;
import com.example.poasystentrekrutacji.dto.raport.RaportDTO;
import com.example.poasystentrekrutacji.entity.AplikacjaNaKierunek;
import com.example.poasystentrekrutacji.entity.PrzeprowadzoneRekrutacje;
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

    public List<PrzeprowadzoneRekrutacje> wypiszRekrutacjeDoRaportu(Principal principal) {
        val user = userRepository.findByEmail(principal.getName()).orElseThrow();
        return przeprowadzoneRekrutacjeRepository.findAllByrekruterId(user.getId());
    }

    public RaportDTO generateRaport(Long raportId, Principal principal) {
        val user = userRepository.findByEmail(principal.getName()).orElseThrow();
        if (!przeprowadzoneRekrutacjeRepository.existsById(raportId)) {
            throw new RuntimeException("Raport with given id not found");
        }
        if (!Objects.equals(przeprowadzoneRekrutacjeRepository.findById(raportId).orElseThrow().getRekruterId(), user.getId())) {
            throw new RuntimeException("User with given id is not allowed to generate this report");
        }
        return generateRaport(raportId);
    }

    private RaportDTO generateRaport(Long raportId) {
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
