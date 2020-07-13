package com.toth.repository;

import com.toth.model.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CronogramaRepository extends JpaRepository<Cronograma, Long> {
}
