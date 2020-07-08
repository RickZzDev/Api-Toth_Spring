package com.toth.repository;

import java.util.Optional;

import com.toth.model.Acesso;

import org.codehaus.plexus.component.annotations.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcessoRepository extends JpaRepository<Acesso, Long> {
    Optional<Acesso> findByLogin(String login);

    Boolean existsByLogin(String login);

}
