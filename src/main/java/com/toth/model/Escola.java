package com.toth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.websocket.OnError;

@Entity
@Table(name = "escola")
public class Escola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "O email deve ser válido!")
    @NotEmpty(message = "O email é obrigatório!")
    @Size(min = 15, max = 255, message = "O email deve conter entre 15 e 255 caracteres")
    private String email;

    @NotEmpty(message = "O nome é obrigatório!")
    @Size(min = 3, max = 255, message = "O nome deve conter entre 8 e 255 caracteres")
    private String nome;

    @NotEmpty
    @Size(min = 3, max = 255, message = "O Login deve ter no mínimo 3 caracteres e no máximo 255.")
    private String login;

    @NotEmpty(message = "A senha é obrigatória!")
    @Size(min = 5, max = 255, message = "A senha deve conter entre 15 e 255 caracteres")
    private String senha;

    @NotEmpty(message = "O cnpj é obrigatório!")
    @Size(min = 3, max = 255, message = "O cnpj deve conter entre 3 e 255 caracteres")
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
