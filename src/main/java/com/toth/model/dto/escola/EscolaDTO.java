package com.toth.model.dto.escola;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toth.enums.TypeEscola;
import com.toth.model.Endereco;
import com.toth.model.Escola;
import com.toth.model.Materia;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class EscolaDTO {

    @JsonIgnore
    private Long id;

    @Email(message = "O email deve ser válido!")
    @NotEmpty(message = "O email é obrigatório!")
    @Size(min = 5, max = 255, message = "O email deve conter entre 5 e 255 caracteres")
    private String email;

    @NotEmpty(message = "O nome é obrigatório!")
    @Size(min = 3, max = 255, message = "O nome deve conter entre 8 e 255 caracteres")
    private String nome;

    @JsonIgnore
    private String login;

    @NotEmpty(message = "A senha é obrigatória!")
    @Size(min = 5, max = 255, message = "A senha deve conter entre 5 e 255 caracteres")
    private String senha;

    @JsonIgnore
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Endereco endereco;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "escolas_materia",
            joinColumns = @JoinColumn(name = "id_escola"),
            inverseJoinColumns = @JoinColumn(name = "id_materia")
    )
    private List<Materia> materias;

    @JsonIgnore
    private Boolean pagamentoStatus;

    @Enumerated()
    @JsonProperty("tipo_escola")
    private TypeEscola typeEscola;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public Escola toEscola(Escola escola) {
        escola.setEmail(this.email);
        escola.setNome(this.nome);
        escola.setEndereco(this.endereco);
        escola.setMaterias(this.materias);
        escola.setTypeEscola(this.typeEscola);

        return escola;
    }

    @Override
    public String toString() {
        return "EscolaDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", endereco=" + endereco +
                ", materias=" + materias +
                ", pagamentoStatus=" + pagamentoStatus +
                ", typeEscola=" + typeEscola +
                '}';
    }
}
