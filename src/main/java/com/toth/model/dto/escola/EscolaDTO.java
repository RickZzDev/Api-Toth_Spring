package com.toth.model.dto.escola;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toth.enums.TypeEscola;
import com.toth.model.*;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class EscolaDTO {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String nome;

    @JsonIgnore
    private Acesso acesso;

    @JsonIgnore
    private String cnpj;

    @JsonIgnore
    private Endereco endereco;

    private List<Materia> materias;

    @JsonIgnore
    private Boolean pagamentoStatus;

    @JsonProperty("tipo_escola")
    private TypeEscola typeEscola;

    private List<Ano> anos;

    @JsonIgnore
    private List<Professor> professores;

    public Long getId() {
        return id;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public TypeEscola getTypeEscola() {
        return typeEscola;
    }

    public void setTypeEscola(TypeEscola typeEscola) {
        this.typeEscola = typeEscola;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ano> getAnos() {
        return anos;
    }

    public void setAnos(List<Ano> anos) {
        this.anos = anos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getPagamentoStatus() {
        return pagamentoStatus;
    }

    public void setPagamentoStatus(Boolean pagamentoStatus) {
        this.pagamentoStatus = pagamentoStatus;
    }

    public Escola toEscola(Escola escola) {
        escola.setMaterias(this.materias);
        escola.setTypeEscola(this.typeEscola);
        escola.setAnos(this.anos);

        return escola;
    }

    @Override
    public String toString() {
        return "EscolaDTO{" +
                "id=" + id +
                ", materias=" + materias +
                ", typeEscola=" + typeEscola +
                ", anos=" + anos +
                '}';
    }
}
