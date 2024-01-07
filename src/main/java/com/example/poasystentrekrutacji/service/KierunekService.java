package com.example.poasystentrekrutacji.service;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import com.example.poasystentrekrutacji.constant.StopienStudiow;
import com.example.poasystentrekrutacji.dto.kierunek.*;
import com.example.poasystentrekrutacji.entity.HonorowaneOsiagniecia;
import com.example.poasystentrekrutacji.entity.Kierunek;
import com.example.poasystentrekrutacji.entity.PunktyRekrutacyjneZaKierunek;
import com.example.poasystentrekrutacji.entity.RegulaWskaznikaRekrutacyjnego;
import com.example.poasystentrekrutacji.repository.HonorowaneOsiagnieciaRepository;
import com.example.poasystentrekrutacji.repository.KierunekRepository;
import com.example.poasystentrekrutacji.repository.PunktyZaKierunekRepository;
import com.example.poasystentrekrutacji.repository.RegulaWskaznikaRekrutacyjnegoRepository;
import com.example.poasystentrekrutacji.utils.validator.ValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KierunekService {

    private final KierunekRepository kierunekRepository;
    private final HonorowaneOsiagnieciaRepository honorowaneOsiagnieciaRepository;
    private final RegulaWskaznikaRekrutacyjnegoRepository regulaWskaznikaRekrutacyjnegoRepository;
    private final PunktyZaKierunekRepository punktyZaKierunekRepository;

    @Autowired
    @Qualifier("strictStrategy")
    private ValidationStrategy<RegisterKierunekRequest> validationStrategy;

    public Long registerKierunek(RegisterKierunekRequest request) {
        validationStrategy.validate(request);

        Kierunek kierunek = Kierunek.builder()
                .nazwa(request.nazwa())
                .dziedzina(request.dziedzina())
                .wydzial(request.wydzial())
                .opis(request.opis())
                .stopienStudiow(request.stopienStudiow())
                .regulyWskaznikaRekrutacyjnego(getRecruitmentRules(request.regulyWskaznikaRekrutacyjnego()))
                .honorowaneOsiagniecia(getHonoredAchievements(request.honorowaneOsiagniecia()))
                .build();

        kierunek = kierunekRepository.save(kierunek);

        setKierunekReferenceForRules(kierunek.getRegulyWskaznikaRekrutacyjnego(), kierunek);
        setKierunekReferenceForAchievements(kierunek.getHonorowaneOsiagniecia(), kierunek);
        createPunktyRekrutacyjneZaKierunek(request.punktyRekrutacyjneZaKierunki());

        return kierunek.getId();
    }

    private List<RegulaWskaznikaRekrutacyjnego> getRecruitmentRules(List<RegulaWskaznikaRekrutacyjnegoDTO> rulesDTOs) {
        return rulesDTOs.stream().map(RegulaWskaznikaRekrutacyjnego::of).toList();
    }

    private List<HonorowaneOsiagniecia> getHonoredAchievements(List<HonorowaneOsiagnieciaDTO> achievementsDTOs) {
        return achievementsDTOs.stream().map(HonorowaneOsiagniecia::of).toList();
    }

    private void setKierunekReferenceForRules(List<RegulaWskaznikaRekrutacyjnego> rules, Kierunek kierunek) {
        rules.forEach(rule -> rule.setKierunek(kierunek));
        regulaWskaznikaRekrutacyjnegoRepository.saveAll(rules);
    }

    private void setKierunekReferenceForAchievements(List<HonorowaneOsiagniecia> achievements, Kierunek kierunek) {
        achievements.forEach(achievement -> achievement.setKierunek(kierunek));
        honorowaneOsiagnieciaRepository.saveAll(achievements);
    }

    private void createPunktyRekrutacyjneZaKierunek(List<PunktyRekrutacyjneZaKierunekDTO> pointsDTOs) {
        List<PunktyRekrutacyjneZaKierunek> points = new ArrayList<>();

        for (PunktyRekrutacyjneZaKierunekDTO pointsDTO : pointsDTOs) {
            var p = PunktyRekrutacyjneZaKierunek.of(pointsDTO);
            kierunekRepository.findById(pointsDTO.idKierunku())
                    .ifPresentOrElse(p::setKierunek,
                            () -> {
                                throw new RuntimeException(String.format("Kierunek of an id: %s not found", pointsDTO.idKierunku()));
                            }
                    );

            points.add(p);
        }

        punktyZaKierunekRepository.saveAll(points);
    }

    public RegisterKierunekFormDataDTO getRegisterKierunekFormData() {
        return RegisterKierunekFormDataDTO.builder()
                .dziedziny(List.of("Matematyka", "Fizyka", "Informatyka i Telekomunikacja", "Chemia", "Lingwistyka Stosowana"))
                .wydzialy(List.of("Matematyczny", "Fizyczny", "Chemiczny", "Lingwistyczny", "Sportowy"))
                .stopnieStudiow(Arrays.stream(StopienStudiow.values()).toList())
                .przedmiotyMaturalne(Arrays.stream(Przedmiot.values()).toList())
                .stopnieMatury(Arrays.stream(StopienMatury.values()).toList())
                .kierunki(kierunekRepository.findAll().stream().map(kierunek -> new KierunekDTO(kierunek.getId(), kierunek.getNazwa())).toList())
                .build();
    }
}
