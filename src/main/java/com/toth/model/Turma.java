package com.toth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "turma")
public class Turma {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turma")
    private Long id;



    @ManyToOne
    @JoinColumn(name = "id_ano")
    private Ano ano;

    @NotNull
    @NotEmpty
    private String identificador;

    @NotNull
    @NotEmpty
    private String turno;



    public String getTurno(){return turno;}

    public void setTurno(String turno){this.turno = turno;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ano getAno() {
        return ano;
    }

    public void setAno(Ano ano) {
        this.ano = ano;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }



    @Override
    public String toString() {
        return "Turma{" +
                "id=" + id +
                ", ano=" + ano +
                ", turno=" + turno +
                ", identificador='" + identificador + '\'' +
                '}';
    }
}
