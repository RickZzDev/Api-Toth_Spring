package com.toth.model;

import java.util.List;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, targetEntity = Ano.class)
    @JoinTable(
            name = "materias_anos",
            joinColumns = @JoinColumn(name = "id_materia"),
            inverseJoinColumns = @JoinColumn(name = "id_ano")
    )
    private List<Ano> ano;

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

    public List<Ano> getAno() {
        return ano;
    }

    public void setAno(List<Ano> ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Materia{" + "id=" + id + ", nome='" + nome + '\'' + ", ano=" + ano + '}';
    }
}
