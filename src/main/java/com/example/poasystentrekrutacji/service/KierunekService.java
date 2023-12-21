package com.example.poasystentrekrutacji.service;

import com.example.poasystentrekrutacji.dto.kierunek.HonorowaneOsiagnieciaDTO;
import com.example.poasystentrekrutacji.dto.kierunek.PunktyRekrutacyjneZaKierunekDTO;
import com.example.poasystentrekrutacji.dto.kierunek.RegisterKierunekRequest;
import com.example.poasystentrekrutacji.dto.kierunek.RegulaWskaznikaRekrutacyjnegoDTO;
import com.example.poasystentrekrutacji.entity.HonorowaneOsiagniecia;
import com.example.poasystentrekrutacji.entity.Kierunek;
import com.example.poasystentrekrutacji.entity.PunktyRekrutacyjneZaKierunek;
import com.example.poasystentrekrutacji.entity.RegulaWskaznikaRekrutacyjnego;
import com.example.poasystentrekrutacji.repository.HonorowaneOsiagnieciaRepository;
import com.example.poasystentrekrutacji.repository.KierunekRepository;
import com.example.poasystentrekrutacji.repository.PunktyZaKierunekRepository;
import com.example.poasystentrekrutacji.repository.RegulaWskaznikaRekrutacyjnegoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KierunekService {

    private final KierunekRepository kierunekRepository;
    private final HonorowaneOsiagnieciaRepository honorowaneOsiagnieciaRepository;
    private final RegulaWskaznikaRekrutacyjnegoRepository regulaWskaznikaRekrutacyjnegoRepository;
    private final PunktyZaKierunekRepository punktyZaKierunekRepository;

    public Long registerKierunek(RegisterKierunekRequest request) {
        Kierunek kierunek = Kierunek.builder()
                .nazwa(request.nazwa())
                .opis(request.opis())
                .regulyWskaznikaRekrutacyjnego(getRecruitmentRules(request.regulyWskaznikaRekrutacyjnego()))
                .honorowaneOsiagniecia(getHonoredAchievements(request.honorowaneOsiagniecia()))
                .build();

        kierunek = kierunekRepository.save(kierunek);

        setKierunekReferenceForRules(kierunek.getRegulyWskaznikaRekrutacyjnego(), kierunek);
        setKierunekReferenceForAchievements(kierunek.getHonorowaneOsiagniecia(), kierunek);
        createPunktyRekrutacyjneZaKierunek(request.punktyRekrutacyjneZaKierunki(), kierunek);

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

    private void createPunktyRekrutacyjneZaKierunek(List<PunktyRekrutacyjneZaKierunekDTO> pointsDTOs, Kierunek kierunek) {
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
}
