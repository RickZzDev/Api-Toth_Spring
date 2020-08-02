package com.toth.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import com.toth.model.Nota;
import com.toth.model.Prova;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

public interface NotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findByProva(Prova prova);
}
