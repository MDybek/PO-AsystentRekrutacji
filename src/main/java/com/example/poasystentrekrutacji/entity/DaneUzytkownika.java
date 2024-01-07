package com.example.poasystentrekrutacji.entity;

import com.example.poasystentrekrutacji.constant.Plec;
import com.example.poasystentrekrutacji.dto.DaneRejestracyjneUzytkownika;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DaneUzytkownika {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imie;
    private String nazwisko;
    private String email;
    private String haslo;
    private String pesel;
    private String numerTelefonu;
    private LocalDate dataUrodzenia;
    private LocalDate dataZalozeniaKonta;
    private Plec plec;

    public static DaneUzytkownika of(DaneRejestracyjneUzytkownika daneRejestracyjneUzytkownika) {
        DaneUzytkownika uzytkownik = new DaneUzytkownika();
        uzytkownik.setImie(daneRejestracyjneUzytkownika.getImie());
        uzytkownik.setNazwisko(daneRejestracyjneUzytkownika.getNazwisko());
        uzytkownik.setEmail(daneRejestracyjneUzytkownika.getEmail());
        uzytkownik.setHaslo(daneRejestracyjneUzytkownika.getHaslo());
        uzytkownik.setPesel(daneRejestracyjneUzytkownika.getPesel());
        uzytkownik.setNumerTelefonu(daneRejestracyjneUzytkownika.getNumerTelefonu());
        uzytkownik.setDataUrodzenia(daneRejestracyjneUzytkownika.getDataUrodzenia());
        uzytkownik.setDataZalozeniaKonta(LocalDate.now());
        uzytkownik.setPlec(daneRejestracyjneUzytkownika.getPlec());

        return uzytkownik;
    }
}