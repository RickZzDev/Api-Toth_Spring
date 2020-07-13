package com.toth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "atividade")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atividade")
    private Long id;

    @ManyToMany(cascade = CascadeType.REFRESH, targetEntity = Aula.class)
    @JoinTable(
            name = "aula_atividades",
            joinColumns = @JoinColumn(name = "id_atividade"),
            inverseJoinColumns = @JoinColumn(name = "id_aula")
    )
    @JsonProperty("aulas")
    @JsonBackReference
    private List<Aula> aulas;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Questao.class)
    private List<Questao> questoes;

    private Double pontos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
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

    @Override
    public String toString() {
        return "Atividade{" +
                "id=" + id +
                ", aula=" + aulas +
                ", questoes=" + questoes +
                ", pontos=" + pontos +
                '}';
    }
}
