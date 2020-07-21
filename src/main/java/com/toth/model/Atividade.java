package com.toth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toth.model.dto.atividade.AtividadeDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "atividade")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atividade")
    private Long id;

    @NotEmpty
    @NotNull
    private String nome;

    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Aula.class)
    @JoinColumn(name = "id_aula")
    @JsonProperty("aulas")
    private Aula aulas;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Questao.class)
    private List<Questao> questoes;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Resposta.class)
    private List<Resposta> respostas;

    @ManyToMany(cascade = CascadeType.REFRESH, targetEntity = Turma.class)
    @JoinTable(name = "atividade_turma", joinColumns = @JoinColumn(name = "id_turma"), inverseJoinColumns = @JoinColumn(name = "id_atividade"))
    private List<Turma> turmas;

    private Double pontos;

    private Date dataEntrega;

    public Atividade() {

    }

    public Atividade(AtividadeDTO atividadeDTO) {
        this.aulas = atividadeDTO.getAula();
        this.pontos = atividadeDTO.getPontos();
        this.questoes = atividadeDTO.getQuestoes();
        this.respostas = atividadeDTO.getRespostas();
        this.turmas = atividadeDTO.getTurmas();
        this.dataEntrega = atividadeDTO.getDataEntrega();
        this.nome = atividadeDTO.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aula getAulas() {
        return aulas;
    }

    public void setAulas(Aula aulas) {
        this.aulas = aulas;
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

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
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

    @Override
    public String toString() {
        return "Atividade [aulas=" + aulas + ", dataEntrega=" + dataEntrega + ", id=" + id + ", nome=" + nome
                + ", pontos=" + pontos + ", questoes=" + questoes + ", respostas=" + respostas + ", turmas=" + turmas
                + "]";
    }

}
