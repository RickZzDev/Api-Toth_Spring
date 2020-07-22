package com.toth.repository;

import java.util.Optional;

import com.toth.model.Turma;
import com.toth.model.dto.turma.TurmaAnoIden;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

}
