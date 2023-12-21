package com.example.poasystentrekrutacji.service;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import com.example.poasystentrekrutacji.constant.StopienStudiow;
import com.example.poasystentrekrutacji.dto.daneRekrutacyjne.FormDataDTO;
import com.example.poasystentrekrutacji.repository.HonorowaneOsiagnieciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DaneRekrutacyjneService {
    private final HonorowaneOsiagnieciaRepository honorowaneOsiagnieciaRepository;

    public FormDataDTO getFormData() {
        return FormDataDTO.builder()
                .przedmiotyMaturalne(Arrays.stream(Przedmiot.values()).toList())
                .stopnieMatury(Arrays.stream(StopienMatury.values()).toList())
                .stopnieStudiow(Arrays.stream(StopienStudiow.values()).toList())
                .honorowaneOsiagniecia(honorowaneOsiagnieciaRepository.findAll())
                .build();
    }
}
