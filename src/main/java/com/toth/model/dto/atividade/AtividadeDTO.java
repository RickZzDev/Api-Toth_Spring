package com.toth.model.dto.atividade;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toth.model.Aluno;
import com.toth.model.Aula;
import com.toth.model.Questao;
import com.toth.model.Resposta;
import com.toth.model.Turma;

public class AtividadeDTO {

    private String nome;

    private Date dataEntrega;

    private Long id;

    private Long id_aula;

    private List<Long> id_aluno;

    @JsonIgnore
    private List<Aluno> alunosCompletaram;

    private List<Long> id_turma;

    @JsonIgnore
    private List<Turma> turmas;

    @JsonIgnore
    private Aula aula;

    private List<Questao> questoes;

    private List<Resposta> respostas;

    private Double pontos;

    public Long getId() {
        return id;
    }

    public void setAulas(Long id_aula) {
        this.id_aula = id_aula;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

    public Double getPontos() {
        return pontos;
    }

    public void setPontos(Double pontos) {
        this.pontos = pontos;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    public Long getId_aula() {
        return id_aula;
    }

    public void setId_aula(Long id_aula) {
        this.id_aula = id_aula;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public List<Long> getId_turma() {
        return id_turma;
    }

    public void setId_turma(List<Long> id_turma) {
        this.id_turma = id_turma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public List<Long> getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(List<Long> id_aluno) {
        this.id_aluno = id_aluno;
    }

    public List<Aluno> getAlunosCompletaram() {
        return alunosCompletaram;
    }

    public void setAlunosCompletaram(List<Aluno> alunosCompletaram) {
        this.alunosCompletaram = alunosCompletaram;
    }

    @Override
    public String toString() {
        return "AtividadeDTO [alunosCompletaram=" + alunosCompletaram + ", aula=" + aula + ", dataEntrega="
                + dataEntrega + ", id=" + id + ", id_aluno=" + id_aluno + ", id_aula=" + id_aula + ", id_turma="
                + id_turma + ", nome=" + nome + ", pontos=" + pontos + ", questoes=" + questoes + ", respostas="
                + respostas + ", turmas=" + turmas + "]";
    }

}