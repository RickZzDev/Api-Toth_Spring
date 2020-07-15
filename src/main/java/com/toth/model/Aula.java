package com.toth.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "aula")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aula")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Professor professor;

    @ManyToOne(cascade = CascadeType.ALL)
    private Materia materia;

    @ManyToMany(cascade = CascadeType.REFRESH, targetEntity = Atividade.class)
    @JoinTable(
            name = "aula_atividades",
            joinColumns = @JoinColumn(name = "id_aula"),
            inverseJoinColumns = @JoinColumn(name = "id_atividade")
    )
    private List<Atividade> atividades;

    public Aula(Materia materia, Professor professor) {
        this.materia = materia;
        this.professor = professor;
    }

    public Aula() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", professor=" + professor +
                ", materia=" + materia +
                '}';
    }

}
