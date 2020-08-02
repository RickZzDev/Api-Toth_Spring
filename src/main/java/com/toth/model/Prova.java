package com.toth.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prova")
public class Prova {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prova")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_aula")
    private Aula aula;

    private String conteudo;

    private String pesoProva;

    private String atividadesParaEstudar;

    private Date diaProva;

    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getPesoProva() {
        return pesoProva;
    }

    public void setPesoProva(String pesoProva) {
        this.pesoProva = pesoProva;
    }

    public String getAtividadesParaEstudar() {
        return atividadesParaEstudar;
    }

    public void setAtividadesParaEstudar(String atividadesParaEstudar) {
        this.atividadesParaEstudar = atividadesParaEstudar;
    }

    public Date getDiaProva() {
        return diaProva;
    }

    public void setDiaProva(Date diaProva) {
        this.diaProva = diaProva;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}