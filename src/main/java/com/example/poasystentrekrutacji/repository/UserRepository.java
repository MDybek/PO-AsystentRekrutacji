package com.example.poasystentrekrutacji.repository;

import com.example.poasystentrekrutacji.entity.DaneUzytkownika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DaneUzytkownika, Long> {
    Optional<DaneUzytkownika> findByEmail(String username);
}
