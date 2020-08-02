package com.toth.repository;

import java.util.List;

import com.google.common.base.Optional;
import com.toth.model.Aula;
import com.toth.model.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AulaRepository extends JpaRepository<Aula, Long> {
    Aula findByProfessor(Professor professor);
}
