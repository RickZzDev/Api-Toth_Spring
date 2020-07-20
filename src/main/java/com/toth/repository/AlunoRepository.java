package com.toth.repository;

import com.toth.model.Acesso;
import com.toth.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Aluno findByAcesso(Acesso acesso);
}
