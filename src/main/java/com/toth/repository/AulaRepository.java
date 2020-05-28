package com.toth.repository;

import com.toth.model.Aulas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AulaRepository extends JpaRepository<Aulas,Long> {
}
