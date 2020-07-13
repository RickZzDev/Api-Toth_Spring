package com.toth.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "dia_letivo")
public class DiaLetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dia_letivo")
    private Long id;

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = Aula.class)
    private List<Aula> aulas;

    private String diaSemana;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    @Override
    public String toString() {
        return "DiaLetivo{" +
                "id=" + id +
                ", aulas=" + aulas +
                ", diaSemana='" + diaSemana + '\'' +
                '}';
    }
}
