package com.toth.model;


import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "Campo rg obrigatorio")
    @Column(unique = true)
    @Size(min= 9, max = 9, message = "Quantidade de caracteres incorreta")
    private String rg;

    @Size(min = 3, message = "O nome deve possuir mais de 3 caracteres")
    private String nome;

    @NotBlank(message = "Campo login deve ser obrigatorio")
    private String login;

    @NotBlank(message = "Campo rg obrigatorio")
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Endereco endereco;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", rg=" + rg +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
