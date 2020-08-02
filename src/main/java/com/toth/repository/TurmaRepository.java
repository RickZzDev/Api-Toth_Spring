package com.toth.repository;

import java.util.List;
import java.util.Optional;

import com.toth.model.Aula;
import com.toth.model.Professor;
import com.toth.model.Turma;
import com.toth.model.dto.turma.TurmaAnoIden;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    // List<Turma> findByProfessor(Professor professor);
    List<Turma> findByAulas(Aula aula);
}
