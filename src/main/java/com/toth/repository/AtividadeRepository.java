package com.toth.repository;

import com.toth.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
