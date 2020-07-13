package com.toth.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.br.CNPJ;

public class ProfessorDTO {

    @JsonIgnore
    private Long id;

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
