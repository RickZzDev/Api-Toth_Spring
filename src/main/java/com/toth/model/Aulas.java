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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, targetEntity = Materia.class)
    @JoinColumn(name = "id_materia", insertable = false, updatable = false)
    @JsonIgnore
    private Materia materia;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, targetEntity = Professor.class)
    @JoinColumn(name = "id_professor", insertable = false, updatable = false)
    @JsonIgnore
    private Professor professor;

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
