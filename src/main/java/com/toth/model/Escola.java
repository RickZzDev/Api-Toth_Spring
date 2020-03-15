package com.toth.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.websocket.OnError;

@Entity
@Table(name = "escola")
public class Escola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String nome;

    @NotNull
    @Size(min = 3, max = 50, message = "O Login deve ter no mínimo 3 caracteres e no máximo 50.")
    private String login;

    @NotBlank(message = "Senha é obrigatório")
    private String senha;

    @Column(unique = true)
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Endereco endereco;

    private Boolean pagamentoStatus;

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

    @Override
    public String toString() {
        return "Escola{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", endereco='" + endereco + '\'' +
                ", pagamentoStatus=" + pagamentoStatus +
                '}';
    }
}
