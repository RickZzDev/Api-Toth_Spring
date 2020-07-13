package com.toth.repository;

import com.toth.model.ComunicadoProfessor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComunicadoRepository extends JpaRepository<ComunicadoProfessor, Long> {

}