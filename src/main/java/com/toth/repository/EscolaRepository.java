package com.toth.repository;

import com.toth.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EscolaRepository extends JpaRepository<Escola, Long> {
    Optional<Escola> findByLogin(String login);
    Boolean existsByLogin(String login);
    Boolean existsByCnpj(String cnpj);
    Optional<Escola> findByCnpj(String cnpj);
}
