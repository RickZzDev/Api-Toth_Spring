package com.toth.repository;

import com.toth.model.Atividades;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividades, Long> {
}
