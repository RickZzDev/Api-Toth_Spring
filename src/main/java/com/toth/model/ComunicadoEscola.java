package com.toth.model;

import java.util.List;

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

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Dynamic;

@Entity
@Table(name = "comunicado_escola")
public class ComunicadoEscola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comunicado_escola")
    private Long id;

    private String description;

    private String title;

    private String publico_alvo;

    private boolean geral;

    private boolean somente_professores;

    private boolean somente_alunos;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private List<Professor> professor;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private List<Aluno> aluno;

    @ManyToMany
    @JoinTable(name = "comunicado_turma", joinColumns = @JoinColumn(name = "id_comunicado"), inverseJoinColumns = @JoinColumn(name = "id_turma"))
    private List<Turma> turmas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getSomenteProfessores() {
        return somente_professores;
    }

    public void setSomenteProfessores(boolean somente_professores) {
        this.somente_professores = somente_professores;
    }

    public boolean getSomenteAlunos() {
        return somente_alunos;
    }

    public void setSomenteAlunos(boolean somente_alunos) {
        this.somente_alunos = somente_alunos;
    }

    public boolean getGeral() {
        return geral;
    }

    public void setGeral(boolean geral) {
        this.geral = geral;
    }

    public List<Professor> getProfessor() {
        return professor;
    }

    public void setProfessor(List<Professor> professor) {
        this.professor = professor;
    }

    public List<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(List<Aluno> alunos) {
        this.aluno = alunos;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurma(List<Turma> turma) {
        this.turmas = turma;
    }

    public String getPublicoAlvo() {
        return publico_alvo;
    }

    public void setPublicoAlvo(String publico_alvo) {
        this.publico_alvo = publico_alvo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}