package com.example.poasystentrekrutacji.service;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import com.example.poasystentrekrutacji.constant.StopienStudiow;
import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.DaneRekrutacyjneDTO;
import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.FormDataDTO;
import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.UkonczoneStudiaDTO;
import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.WynikZMaturyDTO;
import com.example.poasystentrekrutacji.entity.DaneRekrutacyjne;
import com.example.poasystentrekrutacji.entity.HonorowaneOsiagniecia;
import com.example.poasystentrekrutacji.entity.UkonczoneStudia;
import com.example.poasystentrekrutacji.entity.WynikZMatury;
import com.example.poasystentrekrutacji.repository.DaneRekrutacyjneRepository;
import com.example.poasystentrekrutacji.repository.HonorowaneOsiagnieciaRepository;
import com.example.poasystentrekrutacji.repository.UkonczoneStudiaRepository;
import com.example.poasystentrekrutacji.repository.WynikZMaturyRepository;
import com.example.poasystentrekrutacji.utils.validator.CollectionValidationUtils;
import com.example.poasystentrekrutacji.utils.validator.ValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DaneRekrutacyjneService {
    private final HonorowaneOsiagnieciaRepository honorowaneOsiagnieciaRepository;
    private final WynikZMaturyRepository wynikZMaturyRepository;
    private final UkonczoneStudiaRepository ukonczoneStudiaRepository;
    private final DaneRekrutacyjneRepository daneRekrutacyjneRepository;
    private final ValidationStrategy<DaneRekrutacyjneDTO> validationStrategy;

    public FormDataDTO getFormData() {
        return FormDataDTO.builder()
                .przedmiotyMaturalne(Arrays.stream(Przedmiot.values()).toList())
                .stopnieMatury(Arrays.stream(StopienMatury.values()).toList())
                .stopnieStudiow(Arrays.stream(StopienStudiow.values()).toList())
                .honorowaneOsiagniecia(honorowaneOsiagnieciaRepository.findAll())
                .build();
    }

    public Long registerRecruitmentData(DaneRekrutacyjneDTO daneRekrutacyjneDTO) {
        validationStrategy.validate(daneRekrutacyjneDTO);

        DaneRekrutacyjne daneRekrutacyjne = DaneRekrutacyjne.builder()
                .wynikiZMatury(getWynikiZMatury(daneRekrutacyjneDTO.wynikiZMatury()))
                .ukonczoneStudia(getUkonczoneStudia(daneRekrutacyjneDTO.ukonczoneStudia()))
                .honorowaneOsiagniecia(getHonorowaneOsiagniecia(daneRekrutacyjneDTO.honorowaneOsiagnieciaIds()))
                .build();


        daneRekrutacyjne = daneRekrutacyjneRepository.save(daneRekrutacyjne);

        setDaneRekrutacyjneReferenceForWynikiZMatury(daneRekrutacyjne.getWynikiZMatury(), daneRekrutacyjne);
        setDaneRekrutacyjneReferenceForUkonczoneStudia(daneRekrutacyjne.getUkonczoneStudia(), daneRekrutacyjne);
        setHonorowaneOsiagnieciaReferenceForDaneRekrutacyjne(daneRekrutacyjne.getHonorowaneOsiagniecia(), daneRekrutacyjne);

        return daneRekrutacyjne.getId();
    }

    private List<WynikZMatury> getWynikiZMatury(List<WynikZMaturyDTO> wynikZMaturyDTOs) {
        return wynikZMaturyDTOs.stream().map(WynikZMatury::of).toList();
    }

    private List<UkonczoneStudia> getUkonczoneStudia(List<UkonczoneStudiaDTO> ukonczoneStudiaDTOs) {
        return ukonczoneStudiaDTOs.stream().map(UkonczoneStudia::of).toList();
    }

    private List<HonorowaneOsiagniecia> getHonorowaneOsiagniecia(List<Long> honorowaneOsiagnieciaIds) {
        List<HonorowaneOsiagniecia> honorowaneOsiagniecia = new ArrayList<>();

        for (Long id : honorowaneOsiagnieciaIds) {
            honorowaneOsiagniecia.add(
                    honorowaneOsiagnieciaRepository.findById(id).orElseThrow()
            );
        }

        return honorowaneOsiagniecia;
    }

    private void setDaneRekrutacyjneReferenceForWynikiZMatury(List<WynikZMatury> wynikiZMatury, DaneRekrutacyjne daneRekrutacyjne) {
        wynikiZMatury.forEach(w -> w.setDaneRekrutacyjne(daneRekrutacyjne));
        wynikZMaturyRepository.saveAll(wynikiZMatury);
    }

    private void setDaneRekrutacyjneReferenceForUkonczoneStudia(List<UkonczoneStudia> ukonczoneStudia, DaneRekrutacyjne daneRekrutacyjne) {
        ukonczoneStudia.forEach(u -> u.setDaneRekrutacyjne(daneRekrutacyjne));
        ukonczoneStudiaRepository.saveAll(ukonczoneStudia);
    }

    private void setHonorowaneOsiagnieciaReferenceForDaneRekrutacyjne(List<HonorowaneOsiagniecia> honorowaneOsiagniecia, DaneRekrutacyjne daneRekrutacyjne) {
        honorowaneOsiagniecia.forEach(h -> h.getDaneRekrutacyjne().add(daneRekrutacyjne));
        honorowaneOsiagnieciaRepository.saveAll(honorowaneOsiagniecia);
    }
}
