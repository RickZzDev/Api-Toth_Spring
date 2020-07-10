package com.toth.model.dto.professores;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toth.model.Acesso;
import com.toth.model.Endereco;
import com.toth.model.Professor;
import com.toth.model.Turma;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfessorResponse {

    @JsonIgnore
    private Optional<Turma> modelTurma;

    private Long id;
    private String nome;
    private String login;
    private Endereco endereco;
    private List<ProfessorTurmasResponse> turmas;

    private List<ProfessorTurmasResponse> turmasProfessor = new ArrayList<>();

    public ProfessorResponse(Professor professor) {
        this.id = professor.getId();
        this.nome = professor.getNome();
        this.login = professor.getAcesso().getLogin();
        this.endereco = professor.getEndereco();

        /* Transformando a lista de turmas recebida no parâmetro do construtor
         * Em uma lista de ProfessorTurmasResponse
         */
        for(Turma turma: professor.getTurmas()) {
            modelTurma = Optional.of(turma);
            turmasProfessor.add(modelTurma.map(ProfessorTurmasResponse::new).get());
        }

        this.turmas = turmasProfessor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
