package com.toth.repository;

import com.toth.model.Acesso;
import com.toth.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EscolaRepository extends JpaRepository<Escola, Long> {
    Escola findByAcesso(Acesso acesso);

    Boolean existsByAcesso(Escola escola);

    Boolean existsByCnpj(String cnpj);

    Optional<Escola> findByCnpj(String cnpj);
}
