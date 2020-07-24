package com.toth.model.dto.aluno;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toth.model.Acesso;
import com.toth.model.Contato;
import com.toth.model.Responsavel;
import com.toth.model.Turma;

public class AlunoPostDTO {

    private Long id;

    private Responsavel responsavel;

    private Contato contato;

    private Acesso acesso;

    private Long idTurma;

    @JsonIgnore
    private Turma turma;

    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

}