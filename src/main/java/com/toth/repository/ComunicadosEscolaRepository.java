package com.toth.repository;

import java.util.Optional;

import com.toth.model.Acesso;
import com.toth.model.ComunicadoEscola;

import org.codehaus.plexus.component.annotations.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComunicadosEscolaRepository extends JpaRepository<ComunicadoEscola, Long> {

}
