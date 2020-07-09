package com.toth.model;

import java.util.Optional;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Long id;

    private String nome;

    @JoinColumn(name = "id_ano")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Ano.class)
    private Optional<Ano> ano;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Optional<Ano> getAno() {
        return ano;
    }

    public void setAno(Optional<Ano> ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Materia{" + "id=" + id + ", nome='" + nome + '\'' + ", ano=" + ano + '}';
    }
}
