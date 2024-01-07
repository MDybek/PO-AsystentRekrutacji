package com.example.poasystentrekrutacji;

import com.example.poasystentrekrutacji.constant.Plec;
import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import com.example.poasystentrekrutacji.constant.StopienStudiow;
import com.example.poasystentrekrutacji.dto.DaneRejestracyjneUzytkownika;
import com.example.poasystentrekrutacji.dto.kierunek.HonorowaneOsiagnieciaDTO;
import com.example.poasystentrekrutacji.dto.kierunek.PunktyRekrutacyjneZaKierunekDTO;
import com.example.poasystentrekrutacji.dto.kierunek.RegisterKierunekRequest;
import com.example.poasystentrekrutacji.dto.kierunek.RegulaWskaznikaRekrutacyjnegoDTO;
import com.example.poasystentrekrutacji.dto.raport.PrzeprowadzoneRekrutacjeWithIdDTO;
import com.example.poasystentrekrutacji.entity.DaneUzytkownika;
import com.example.poasystentrekrutacji.entity.Kierunek;
import com.example.poasystentrekrutacji.repository.KierunekRepository;
import com.example.poasystentrekrutacji.repository.UserRepository;
import com.example.poasystentrekrutacji.service.AuthenticationService;
import com.example.poasystentrekrutacji.service.KierunekService;
import com.example.poasystentrekrutacji.service.RaportService;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PoAsystentRekrutacjiApplicationTests {

    @Autowired
    private KierunekService kierunekService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private KierunekRepository kierunekRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RaportService raportService;

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

        var registerKierunekRequest = RegisterKierunekRequest.builder()
                .nazwa("test")
                .opis("test")
                .wydzial("test")
                .dziedzina("test")
                .stopienStudiow(StopienStudiow.I)
                .punktyRekrutacyjneZaKierunki(List.of())
                .regulyWskaznikaRekrutacyjnego(List.of(regulaWskaznikaRekrutacyjnego))
                .honorowaneOsiagniecia(List.of(honorowaneOsiagniecie))
                .build();

        // when
        kierunekService.registerKierunek(registerKierunekRequest);
        var kierunek = kierunekRepository.findKierunekByNazwaAndWydzialAndDziedzinaAndStopienStudiow("test", "test", "test", StopienStudiow.I);

        // then
        var osiagniecie = kierunek.getHonorowaneOsiagniecia().get(0);
        var regula = kierunek.getRegulyWskaznikaRekrutacyjnego().get(0);

        assertEquals(osiagniecie.getNazwa(), honorowaneOsiagniecie.nazwa());
        assertEquals(osiagniecie.getOpis(), honorowaneOsiagniecie.opis());
        assertEquals(osiagniecie.getLiczbaPunktow(), honorowaneOsiagniecie.liczbaPunktow());

        assertEquals(regula.getPrzedmiot(), regulaWskaznikaRekrutacyjnego.przedmiot());
        assertEquals(regula.getStopienMatury(), regulaWskaznikaRekrutacyjnego.stopienMatury());
        assertEquals(regula.getWaga(), regulaWskaznikaRekrutacyjnego.waga());
    }

    @Test
    @Rollback
    void registerUserWithExistingEmailShouldThrowException() {
        // given
        var user1 = DaneUzytkownika.builder()
                .imie("Kamil")
                .nazwisko("Nowak")
                .email("kamil.nowak@gmail.com")
                .haslo("test")
                .pesel("12312312311")
                .numerTelefonu("123123123")
                .dataUrodzenia(LocalDate.of(1999, 1, 1))
                .dataZalozeniaKonta(LocalDate.now())
                .plec(Plec.M)
                .build();

        var user2 = DaneRejestracyjneUzytkownika.builder()
                .imie("Kamil")
                .nazwisko("Nowak")
                .email("kamil.nowak@gmail.com")
                .haslo("test")
                .pesel("32132132111")
                .numerTelefonu("321321321")
                .dataUrodzenia(LocalDate.of(2001, 1, 1))
                .dataZalozeniaKonta(LocalDate.now())
                .plec(Plec.M)
                .build();

        // when && then
        userRepository.save(user1);
        assertThrows(RuntimeException.class, () -> authenticationService.register(user2));
    }

    @Test
    @Rollback
    void registerUserWithExistingPeselShouldThrowException() {
        // given
        var user1 = DaneUzytkownika.builder()
                .imie("Kamil")
                .nazwisko("Nowak")
                .email("kamil.nowak@gmail.com")
                .haslo("test")
                .pesel("12312312311")
                .numerTelefonu("123123123")
                .dataUrodzenia(LocalDate.of(1999, 1, 1))
                .dataZalozeniaKonta(LocalDate.now())
                .plec(Plec.M)
                .build();

        var user2 = DaneRejestracyjneUzytkownika.builder()
                .imie("Kamil")
                .nazwisko("Nowak")
                .email("kamil.1.nowak@gmail.com")
                .haslo("test")
                .pesel("12312312311")
                .numerTelefonu("321321321")
                .dataUrodzenia(LocalDate.of(2001, 1, 1))
                .dataZalozeniaKonta(LocalDate.now())
                .plec(Plec.M)
                .build();

        // when && then
        userRepository.save(user1);
        assertThrows(RuntimeException.class, () -> authenticationService.register(user2));
    }

    @Test
    @Rollback
    void registerUserWithExistingTelephoneNumberShouldThrowException() {
        // given
        var user1 = DaneUzytkownika.builder()
                .imie("Kamil")
                .nazwisko("Nowak")
                .email("kamil.nowak@gmail.com")
                .haslo("test")
                .pesel("12312312311")
                .numerTelefonu("123123123")
                .dataUrodzenia(LocalDate.of(1999, 1, 1))
                .dataZalozeniaKonta(LocalDate.now())
                .plec(Plec.M)
                .build();

        var user2 = DaneRejestracyjneUzytkownika.builder()
                .imie("Kamil")
                .nazwisko("Nowak")
                .email("kamil.1.nowak@gmail.com")
                .haslo("test")
                .pesel("32132132111")
                .numerTelefonu("123123123")
                .dataUrodzenia(LocalDate.of(2001, 1, 1))
                .dataZalozeniaKonta(LocalDate.now())
                .plec(Plec.M)
                .build();

        // when && then
        userRepository.save(user1);
        assertThrows(RuntimeException.class, () -> authenticationService.register(user2));
    }

    @Test
    @Rollback
    void registerUserWithProperDataShouldRegisterUser() {
        // given
        var user1 = DaneUzytkownika.builder()
                .imie("Kamil")
                .nazwisko("Nowak")
                .email("kamil.nowak@gmail.com")
                .haslo("test")
                .pesel("12312312311")
                .numerTelefonu("123123123")
                .dataUrodzenia(LocalDate.of(1999, 1, 1))
                .dataZalozeniaKonta(LocalDate.now())
                .plec(Plec.M)
                .build();

        // when && then
        userRepository.save(user1);
        var userFromDb = userRepository.findByEmail("kamil.nowak@gmail.com").orElseThrow();
        assertEquals(userFromDb.getImie(), user1.getImie());
        assertEquals(userFromDb.getNazwisko(), user1.getNazwisko());
        assertEquals(userFromDb.getEmail(), user1.getEmail());
        assertEquals(userFromDb.getHaslo(), user1.getHaslo());
        assertEquals(userFromDb.getPesel(), user1.getPesel());
        assertEquals(userFromDb.getNumerTelefonu(), user1.getNumerTelefonu());
        assertEquals(userFromDb.getDataUrodzenia(), user1.getDataUrodzenia());
        assertEquals(userFromDb.getDataZalozeniaKonta(), user1.getDataZalozeniaKonta());
        assertEquals(userFromDb.getPlec(), user1.getPlec());
    }

    @Test
    void listAllRaportsForInvalidGivenUserIdShouldReturnEmptyList() {
        // given
        Long invalidUserId = 123123L;
        // when && then
        List<PrzeprowadzoneRekrutacjeWithIdDTO> raports = raportService.wypiszRekrutacjeDoRaportu(invalidUserId);
        assertEquals(raports.size(), 0);
    }

    @Test
    void listAllRaportsForValidGivenUserIdShouldListRaports() {
        // given
        Long validUserId = 1L;
        // when && then
        assertDoesNotThrow(() -> raportService.wypiszRekrutacjeDoRaportu(validUserId));
    }

    @Test
    void generateRaportForInvalidRaportIdShouldThrowError() {
        // given
        Long invalidRaportId = 123123L;
        // when && then
        assertThrows(RuntimeException.class, () -> raportService.generateRaport(invalidRaportId));
    }

    @Test
    @Transactional
    void generateRaportForValidRaportIdShouldReturnRaport() {
        Long validUserId = 1L;
        assertDoesNotThrow(() -> raportService.generateRaport(validUserId));
    }
}
