package com.toth.model.dto.prova;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toth.model.Aula;
import com.toth.model.Nota;
import com.toth.model.dto.nota.NotaDTO;

public class ProvaRequest {
    private Long id_aula;

    private String conteudo;

    private String pesoProva;

    private String atividadesParaEstudar;

    private Date diaProva;

    private List<NotaDTO> notas;

    private String nome;

    @JsonIgnore
    private Aula aula;

    public Long getId_aula() {
        return id_aula;
    }

    public void setId_aula(Long id_aula) {
        this.id_aula = id_aula;
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

    public List<NotaDTO> getNotas() {
        return notas;
    }

    public void setNotas(List<NotaDTO> notas) {
        this.notas = notas;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

}