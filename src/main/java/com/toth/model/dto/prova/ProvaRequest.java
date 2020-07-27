package com.toth.model.dto.prova;

import java.util.Date;

public class ProvaRequest {
    private Long id_aula;

    private String conteudo;

    private String pesoProva;

    private String atividadesParaEstudar;

    private Date diaProva;

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

}