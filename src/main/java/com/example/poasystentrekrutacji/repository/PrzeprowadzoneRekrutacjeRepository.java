package com.example.poasystentrekrutacji.repository;

import com.example.poasystentrekrutacji.dto.raport.PrzeprowadzoneRekrutacjeWithIdDTO;
import com.example.poasystentrekrutacji.entity.PrzeprowadzonaRekrutacja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrzeprowadzoneRekrutacjeRepository extends JpaRepository<PrzeprowadzonaRekrutacja, Long> {

    List<PrzeprowadzoneRekrutacjeWithIdDTO> findAllByrekruterId(Long rekruterId);
}
