package com.toth.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "aula")
public class Aulas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aula")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_materia")
    private Materia materia;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

    public Aulas() {

    }

    public Aulas(Materia materia, Professor professor) {
        this.materia = materia;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Aulas{" + "id=" + id + ", materia=" + materia + ", professor=" + professor + '}';
    }
}
