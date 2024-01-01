package com.example.poasystentrekrutacji.repository;

import com.example.poasystentrekrutacji.entity.PrzeprowadzoneRekrutacje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrzeprowadzoneRekrutacjeRepository extends JpaRepository<PrzeprowadzoneRekrutacje, Long> {

    List<PrzeprowadzoneRekrutacje> findAllByrekruterId(Long rekruterId);
}
