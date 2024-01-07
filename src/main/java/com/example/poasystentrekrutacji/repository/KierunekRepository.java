package com.example.poasystentrekrutacji.repository;

import com.example.poasystentrekrutacji.constant.StopienStudiow;
import com.example.poasystentrekrutacji.entity.Kierunek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KierunekRepository extends JpaRepository<Kierunek, Long> {
    List<Kierunek> findKieruneksByNazwaAndWydzialAndDziedzinaAndStopienStudiow(String nazwa, String wydzial, String dziedzina, StopienStudiow stopienStudiow);
}
