package com.toth.model;

import javax.annotation.Generated;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "atividades")
public class Atividades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atividade")
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "id_materia")
    private Materia materia;

    @NotNull
    @NotEmpty
    private String tipo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "id_atividade"), inverseJoinColumns = @JoinColumn(name = "id_aluno"))
    private List<Aluno> alunos;

    private Boolean todosAlunos;

    @ManyToMany
    @JoinTable(
            name = "atividades_turma",
            joinColumns = @JoinColumn(name = "id_atividade"),
            inverseJoinColumns = @JoinColumn(name = "id_turma")
    )
    private List<Turma> turmas;

    public List<Turma> getTurma() {
        return turmas;
    }

    public void setTurma(List<Turma> turma) {
        this.turmas = turmas;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getTodosAlunos() {
        return todosAlunos;
    }

    public void setTodosAlunos(Boolean todosAlunos) {
        this.todosAlunos = todosAlunos;
    }

	@Override
	public String toString() {
		return "Atividades [id=" + id + ", nome=" + nome + ", materia=" + materia + ", tipo=" + tipo + ", alunos="
				+ alunos + ", todosAlunos=" + todosAlunos + ", turmas=" + turmas + "]";
	}

}