package com.example.poasystentrekrutacji.service;

import com.example.poasystentrekrutacji.dto.raport.RaportDTO;
import com.example.poasystentrekrutacji.entity.PrzeprowadzoneRekrutacje;
import com.example.poasystentrekrutacji.repository.PrzeprowadzoneRekrutacjeRepository;
import com.example.poasystentrekrutacji.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.hibernate.resource.beans.container.internal.NotYetReadyException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RaportService {
    private final UserRepository userRepository;
    private final PrzeprowadzoneRekrutacjeRepository przeprowadzoneRekrutacjeRepository;
    public List<PrzeprowadzoneRekrutacje> wypiszRekrutacjeDoRaportu(Principal principal) {
        val user = userRepository.findByEmail(principal.getName()).orElseThrow();
        return przeprowadzoneRekrutacjeRepository.findAllByrekruterId(user.getId());
    }

    public RaportDTO generateRaport(Long raportId, Principal principal) {
        val user = userRepository.findByEmail(principal.getName()).orElseThrow();
        if (!przeprowadzoneRekrutacjeRepository.existsById(raportId)) {
            throw new RuntimeException("Raport with given id not found");
        }
        if (!Objects.equals(przeprowadzoneRekrutacjeRepository.findById(raportId).orElseThrow().getRekruterId(), user.getId())) {
            throw new RuntimeException("User with given id is not allowed to generate this report");
        }
        return generateRaport(raportId);
    }

    private RaportDTO generateRaport(Long raportId) {
        return null;
    }
}
