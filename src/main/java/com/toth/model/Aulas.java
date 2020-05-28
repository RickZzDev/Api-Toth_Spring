package com.toth.model;

import javax.persistence.*;
import java.sql.Time;


@Entity
@Table(name="aula")
public class Aulas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Time hora_inicio;

    private Time hora_fim;

    private String dia;

    @OneToOne
    @JoinColumn(name="id")
    private Materia materia;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id")
    private Professor professor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(Time hora_fim) {
        this.hora_fim = hora_fim;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
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
        return "Aulas{" +
                "id=" + id +
                ", hora_inicio=" + hora_inicio +
                ", hora_fim=" + hora_fim +
                ", dia='" + dia + '\'' +
                ", materia=" + materia +
                ", professor=" + professor +
                '}';
    }
}
