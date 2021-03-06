package com.toth.repository;

import com.toth.model.Acesso;
import com.toth.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByRg(String rg);

    Boolean existsByRg(String rg);

    Boolean existsByAcesso(Professor professor);

    Professor findByAcesso(Acesso acesso);
}
