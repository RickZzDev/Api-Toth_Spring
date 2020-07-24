package com.toth.repository;

import java.util.List;

import com.toth.model.Acesso;
import com.toth.model.Aluno;
import com.toth.model.Turma;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Aluno findByAcesso(Acesso acesso);

    List<Aluno> findByTurma(Turma turma);
}
