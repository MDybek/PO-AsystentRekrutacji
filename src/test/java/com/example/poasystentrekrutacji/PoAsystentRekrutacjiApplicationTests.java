package com.example.poasystentrekrutacji;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import com.example.poasystentrekrutacji.constant.StopienStudiow;
import com.example.poasystentrekrutacji.dto.kierunek.HonorowaneOsiagnieciaDTO;
import com.example.poasystentrekrutacji.dto.kierunek.PunktyRekrutacyjneZaKierunekDTO;
import com.example.poasystentrekrutacji.dto.kierunek.RegisterKierunekRequest;
import com.example.poasystentrekrutacji.dto.kierunek.RegulaWskaznikaRekrutacyjnegoDTO;
import com.example.poasystentrekrutacji.entity.Kierunek;
import com.example.poasystentrekrutacji.repository.KierunekRepository;
import com.example.poasystentrekrutacji.service.KierunekService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PoAsystentRekrutacjiApplicationTests {

    @Autowired
    private KierunekService kierunekService;
    @Autowired
    private KierunekRepository kierunekRepository;

    @Test
    @Rollback
    void registeringKierunekThatAlreadyExistsShouldThrowAnException() {
        // given
        var existingKierunek = Kierunek.builder()
                .nazwa("test")
                .opis("test")
                .dziedzina("test")
                .wydzial("test")
                .stopienStudiow(StopienStudiow.I)
                .build();

        var registerKierunekRequest = RegisterKierunekRequest.builder()
                .nazwa("test")
                .opis("test")
                .wydzial("test")
                .dziedzina("test")
                .stopienStudiow(StopienStudiow.I)
                .build();

        // when && then
        kierunekRepository.save(existingKierunek);
        assertThrows(RuntimeException.class, () -> kierunekService.registerKierunek(registerKierunekRequest));
    }

    @Test
    @Rollback
    void registeringKierunekWithRepeatedHonorowaneOsiagnieciaShouldThrowException() {
        // given
        var registerKierunekRequest = RegisterKierunekRequest.builder()
                .nazwa("test")
                .opis("test")
                .wydzial("test")
                .dziedzina("test")
                .stopienStudiow(StopienStudiow.I)
                .honorowaneOsiagniecia(
                        List.of(
                        HonorowaneOsiagnieciaDTO.builder()
                                .nazwa("test")
                                .opis("test")
                                .liczbaPunktow(23)
                                .build(),
                        HonorowaneOsiagnieciaDTO.builder()
                                .nazwa("test")
                                .opis("test")
                                .liczbaPunktow(23)
                                .build()
                )).build();

        // when && then
        assertThrows(RuntimeException.class, () -> kierunekService.registerKierunek(registerKierunekRequest));
    }

    @Test
    @Rollback
    void registeringKierunekWithProperDataShouldRegisterKierunek() {
        // given
        var honorowaneOsiagniecie = HonorowaneOsiagnieciaDTO.builder()
                .nazwa("test")
                .opis("test")
                .liczbaPunktow(23)
                .build();

        var regulaWskaznikaRekrutacyjnego = RegulaWskaznikaRekrutacyjnegoDTO.builder()
                .przedmiot(Przedmiot.BIOLOGIA)
                .stopienMatury(StopienMatury.PODSTAWOWA)
                .waga(3D)
                .build();

        var punktyZaKierunki = PunktyRekrutacyjneZaKierunekDTO.builder()
                .idKierunku(1L)
                .liczbaPunktow(30)
                .build();

        var registerKierunekRequest = RegisterKierunekRequest.builder()
                .nazwa("test")
                .opis("test")
                .wydzial("test")
                .dziedzina("test")
                .stopienStudiow(StopienStudiow.I)
                .punktyRekrutacyjneZaKierunki(List.of(punktyZaKierunki))
                .regulyWskaznikaRekrutacyjnego(List.of(regulaWskaznikaRekrutacyjnego))
                .honorowaneOsiagniecia(List.of(honorowaneOsiagniecie))
                .build();

        // when
        kierunekService.registerKierunek(registerKierunekRequest);
        var kierunek = kierunekRepository.findKierunekByNazwaAndWydzialAndDziedzinaAndStopienStudiow("test", "test", "test", StopienStudiow.I);

        // then
        var osiagniecie = kierunek.getHonorowaneOsiagniecia().get(0);
        var punkty = kierunek.getPunktyRekrutacyjneZaKierunek().get(0);
        var regula = kierunek.getRegulyWskaznikaRekrutacyjnego().get(0);

        assertEquals(osiagniecie.getNazwa(), honorowaneOsiagniecie.nazwa());
        assertEquals(osiagniecie.getOpis(), honorowaneOsiagniecie.opis());
        assertEquals(osiagniecie.getLiczbaPunktow(), honorowaneOsiagniecie.liczbaPunktow());

        assertEquals(punkty.getLiczbaPunktow(), punktyZaKierunki.liczbaPunktow());

        assertEquals(regula.getPrzedmiot(), regulaWskaznikaRekrutacyjnego.przedmiot());
        assertEquals(regula.getStopienMatury(), regulaWskaznikaRekrutacyjnego.stopienMatury());
        assertEquals(regula.getWaga(), regulaWskaznikaRekrutacyjnego.waga());
    }
}
