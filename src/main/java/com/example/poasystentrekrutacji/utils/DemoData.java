package com.example.poasystentrekrutacji.utils;

import com.example.poasystentrekrutacji.constant.Przedmiot;
import com.example.poasystentrekrutacji.constant.StopienMatury;
import com.example.poasystentrekrutacji.entity.*;
import com.example.poasystentrekrutacji.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class DemoData {

    private final AplikacjaNaKierunekRepository aplikacjaNaKierunekRepository;
    private final DaneRekrutacyjneRepository daneRekrutacyjneRepository;
    private final HonorowaneOsiagnieciaRepository honorowaneOsiagnieciaRepository;
    private final KierunekRepository kierunekRepository;
    private final PrzedmiotNaStudiachRepository przedmiotNaStudiachRepository;
    private final PrzeprowadzoneRekrutacjeRepository przeprowadzoneRekrutacjeRepository;
    private final PunktyZaKierunekRepository punktyZaKierunekRepository;
    private final RegulaWskaznikaRekrutacyjnegoRepository regulaWskaznikaRekrutacyjnegoRepository;
    private final RekrutacjaRepository rekrutacjaRepository;
    private final UkonczoneStudiaRepository ukonczoneStudiaRepository;
    private final UserRepository userRepository;
    private final WynikZMaturyRepository wynikZMaturyRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        var kierunek = kierunekRepository.save(Kierunek.builder()
                .nazwa("Informatyka stosowana")
                .opis("M + PD + 0,1JO + 0,1JP")
                .dziedzina("Informatyka")
                .wydzial("Wydział Informatyki i Telekomunikacji - W04n")
                .uwagiDoReguly("M, PD, JO jest równe większej z liczb:\n" +
                        "PODSTAWA albo PODSTAWA + 1,5 ROZSZERZENIE albo 2,5 ROZSZERZENIE\nJP jest równy większej wartości PODSTAWA lub ROZSZERZENIE\n" +
                        "PD to Fizyka lub Informatyka")
                .build());

        List<AplikacjaNaKierunek> aplikacjeNaKierunek = new ArrayList<>();
        aplikacjeNaKierunek.add(aplikacjaNaKierunekRepository.save(AplikacjaNaKierunek.builder()
                .wskaznikRekrutacyjny(500.0)
                .liczbaPunktowZaZadanieRekrutacyjne(Collections.emptyList())
                .imie("Piotr")
                .nazwisko("Kowalski")
                .build()));

        List<WynikZMatury> wynikZMatury1 = new ArrayList<>();
        wynikZMatury1.add(wynikZMaturyRepository.save(WynikZMatury.builder()
                .przedmiot(Przedmiot.INFORMATYKA)
                .stopien(StopienMatury.ROZSZERZONA)
                .wartosc(100)
                .build()));

        wynikZMatury1.add(wynikZMaturyRepository.save(WynikZMatury.builder()
                .przedmiot(Przedmiot.MATEMATYKA)
                .stopien(StopienMatury.ROZSZERZONA)
                .wartosc(100)
                .build()));

        wynikZMatury1.add(wynikZMaturyRepository.save(WynikZMatury.builder()
                .przedmiot(Przedmiot.MATEMATYKA)
                .stopien(StopienMatury.PODSTAWOWA)
                .wartosc(100)
                .build()));

        wynikZMatury1.add(wynikZMaturyRepository.save(WynikZMatury.builder()
                .przedmiot(Przedmiot.JEZYK_POLSKI)
                .stopien(StopienMatury.PODSTAWOWA)
                .wartosc(90)
                .build()));

        wynikZMatury1.add(wynikZMaturyRepository.save(WynikZMatury.builder()
                .przedmiot(Przedmiot.JEZYK_ANGIELSKI)
                .stopien(StopienMatury.ROZSZERZONA)
                .wartosc(80)
                .build()));

        wynikZMatury1.add(wynikZMaturyRepository.save(WynikZMatury.builder()
                .przedmiot(Przedmiot.JEZYK_ANGIELSKI)
                .stopien(StopienMatury.PODSTAWOWA)
                .wartosc(98)
                .build()));

        var daneRek1 = daneRekrutacyjneRepository.save(DaneRekrutacyjne.builder()
                .aplikacjaNaKierunek(aplikacjeNaKierunek.get(0))
                .ukonczoneStudia(Collections.emptyList())
                .wynikiZMatury(wynikZMatury1)
                .honorowaneOsiagniecia(Collections.emptyList())
                .build());

        wynikZMatury1.forEach(wynikZMatury -> {
            wynikZMatury.setDaneRekrutacyjne(daneRek1);
            wynikZMaturyRepository.save(wynikZMatury);
        });

        aplikacjeNaKierunek.get(0).setDaneRekrutacyjne(daneRek1);
        aplikacjaNaKierunekRepository.save(aplikacjeNaKierunek.get(0));

        List<RegulaWskaznikaRekrutacyjnego> reguly = new ArrayList<>();
        reguly.add(regulaWskaznikaRekrutacyjnegoRepository.save(RegulaWskaznikaRekrutacyjnego.builder()
                .przedmiot(Przedmiot.INFORMATYKA)
                .waga(2.5)
                .stopienMatury(StopienMatury.ROZSZERZONA)
                .kierunek(kierunek)
                .build()));

        reguly.add(regulaWskaznikaRekrutacyjnegoRepository.save(RegulaWskaznikaRekrutacyjnego.builder()
                .przedmiot(Przedmiot.MATEMATYKA)
                .waga(2.5)
                .stopienMatury(StopienMatury.ROZSZERZONA)
                .kierunek(kierunek)
                .build()));

        reguly.add(regulaWskaznikaRekrutacyjnegoRepository.save(RegulaWskaznikaRekrutacyjnego.builder()
                .przedmiot(Przedmiot.FIZYKA)
                .waga(2.5)
                .stopienMatury(StopienMatury.ROZSZERZONA)
                .kierunek(kierunek)
                .build()));

        reguly.add(regulaWskaznikaRekrutacyjnegoRepository.save(RegulaWskaznikaRekrutacyjnego.builder()
                .przedmiot(Przedmiot.MATEMATYKA)
                .waga(1.0)
                .stopienMatury(StopienMatury.PODSTAWOWA)
                .kierunek(kierunek)
                .build()));

        reguly.add(regulaWskaznikaRekrutacyjnegoRepository.save(RegulaWskaznikaRekrutacyjnego.builder()
                .przedmiot(Przedmiot.JEZYK_POLSKI)
                .waga(0.1)
                .stopienMatury(StopienMatury.PODSTAWOWA)
                .kierunek(kierunek)
                .build()));

        reguly.add(regulaWskaznikaRekrutacyjnegoRepository.save(RegulaWskaznikaRekrutacyjnego.builder()
                .przedmiot(Przedmiot.JEZYK_ANGIELSKI)
                .waga(0.15)
                .stopienMatury(StopienMatury.PODSTAWOWA)
                .kierunek(kierunek)
                .build()));

        List<HonorowaneOsiagniecia> honorowaneOsiagniecia = new ArrayList<>();
        honorowaneOsiagniecia.add(honorowaneOsiagnieciaRepository.save(HonorowaneOsiagniecia.builder()
                .nazwa("Olimpiada Informatyczna")
                .opis("Status olimpijczyka na etapie centralnym")
                .liczbaPunktow(535)
                .kierunek(kierunek)
                .build()));

        honorowaneOsiagniecia.add(honorowaneOsiagnieciaRepository.save(HonorowaneOsiagniecia.builder()
                .nazwa("Olimpiada Matematyczna")
                .opis("Status olimpijczyka na etapie centralnym")
                .liczbaPunktow(535)
                .kierunek(kierunek)
                .build()));

        honorowaneOsiagniecia.add(honorowaneOsiagnieciaRepository.save(HonorowaneOsiagniecia.builder()
                .nazwa("Studium talent")
                .opis("Ocena bardzo dobra lub bardzo dobra plus")
                .liczbaPunktow(535)
                .kierunek(kierunek)
                .build()));

        honorowaneOsiagniecia.add(honorowaneOsiagnieciaRepository.save(HonorowaneOsiagniecia.builder()
                .nazwa("Studium talent")
                .opis("Ocena dobra")
                .liczbaPunktow(40)
                .kierunek(kierunek)
                .build()));

        honorowaneOsiagniecia.add(honorowaneOsiagnieciaRepository.save(HonorowaneOsiagniecia.builder()
                .nazwa("Studium talent")
                .opis("Ocena mierna")
                .liczbaPunktow(30)
                .kierunek(kierunek)
                .build()));

        List<PrzedmiotNaStudiach> przedmioty = new ArrayList<>();
        przedmioty.add(przedmiotNaStudiachRepository.save(PrzedmiotNaStudiach.builder()
                .nazwa("Logika dla informatyków")
                .semestr(1)
                .liczbaECTS(5)
                .opis("Kurs składający się z ćwiczeń i wykładu kończący się egzaminem")
                .kierunek(kierunek)
                .build()));

        przedmioty.add(przedmiotNaStudiachRepository.save(PrzedmiotNaStudiach.builder()
                .nazwa("Analiza matematyczna I")
                .semestr(1)
                .liczbaECTS(4)
                .opis("Kurs składający się z ćwiczeń i wykładu kończący się egzaminem")
                .kierunek(kierunek)
                .build()));

        przedmioty.add(przedmiotNaStudiachRepository.save(PrzedmiotNaStudiach.builder()
                .nazwa("Algebra z geometrią analityczną")
                .semestr(1)
                .liczbaECTS(5)
                .opis("Kurs składający się z ćwiczeń i wykładu kończący się egzaminem")
                .kierunek(kierunek)
                .build()));

        przedmioty.add(przedmiotNaStudiachRepository.save(PrzedmiotNaStudiach.builder()
                .nazwa("Fizka I")
                .semestr(1)
                .liczbaECTS(4)
                .opis("Kurs składający się z ćwiczeń i wykładu kończący się egzaminem")
                .kierunek(kierunek)
                .build()));

        przedmioty.add(przedmiotNaStudiachRepository.save(PrzedmiotNaStudiach.builder()
                .nazwa("Projektowanie strukturalne i obiektowe")
                .semestr(1)
                .liczbaECTS(2)
                .opis("Labolatoria")
                .kierunek(kierunek)
                .build()));


        kierunek.setPrzedmiotyNaStudiach(przedmioty);
        kierunek.setRegulyWskaznikaRekrutacyjnego(reguly);
        kierunek.setHonorowaneOsiagniecia(honorowaneOsiagniecia);
        kierunekRepository.save(kierunek);

        var rekrutacja1 = rekrutacjaRepository.save(Rekrutacja.builder()
                .dataRozpoczecia(LocalDate.of(2021, 1, 1))
                .dataZakonczenia(LocalDate.of(2021, 2, 12))
                .limitOsob(150)
                .liczbaKandydatow(450)
                .liczbaKatndydatowNaMiejsce(3.0)
                .sredniWskaznikRekrutacyjny(496.3)
                .kierunek(kierunek)
                .aplikacjaNaKierunek(aplikacjeNaKierunek)
                .minimalnyWskaznikRekrutacyjny(465.5)
                .build());

        kierunek.setRekrutacja(rekrutacja1);
        kierunekRepository.save(kierunek);

        aplikacjeNaKierunek.forEach(aplikacjaNaKierunek1 -> {
            aplikacjaNaKierunek1.setRekrutacja(rekrutacja1);
            aplikacjaNaKierunekRepository.save(aplikacjaNaKierunek1);
        });

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2022)
                .nazwaKierunku("Informatyka stosowana 2022")
                .rekruterId(1L)
                .rekrutacjaId(rekrutacja1.getId())
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2021)
                .nazwaKierunku("Informatyka stosowana 2021")
                .rekruterId(1L)
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2020)
                .nazwaKierunku("Informatyka stosowana 2020")
                .rekruterId(1L)
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2022)
                .nazwaKierunku("Informatyka algorytmiczna 2022")
                .rekruterId(1L)
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2021)
                .nazwaKierunku("Informatyka algorytmiczna 2021")
                .rekruterId(1L)
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2020)
                .nazwaKierunku("Informatyka algorytmiczna 2020")
                .rekruterId(1L)
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2022)
                .nazwaKierunku("Informatyka techniczna 2022")
                .rekruterId(1L)
                .build());

        przeprowadzoneRekrutacjeRepository.save(PrzeprowadzonaRekrutacja.builder()
                .rok(2021)
                .nazwaKierunku("Informatyka techniczna 2021")
                .rekruterId(1L)
                .build());
    }
}