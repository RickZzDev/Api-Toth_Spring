package com.toth.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chamada")
public class Chamada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private Date diaEntrega;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_turma")
    private Turma turma;

    @ManyToMany(cascade = CascadeType.REFRESH, targetEntity = Aluno.class)
    @JoinTable(name = "chamada_aluno", joinColumns = @JoinColumn(name = "id_chamada"), inverseJoinColumns = @JoinColumn(name = "id_aluno"))
    private List<Aluno> alunos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Date getDiaEntrega() {
        return diaEntrega;
    }

    public void setDiaEntrega(Date diaEntrega) {
        this.diaEntrega = diaEntrega;
    }

}