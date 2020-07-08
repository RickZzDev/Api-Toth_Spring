package com.toth.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_professor")
    private Long id;

    @NotEmpty(message = "Campo rg obrigatorio")
    @Size(min = 9, max = 9, message = "Quantidade de caracteres incorreta")
    @Column(unique = true)
    private String rg;

    private String cpf;

    @Size(min = 3, message = "O nome deve possuir mais de 3 caracteres")
    @NotNull(message = "não pode ser null")
    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_acesso")
    private Acesso acesso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "professor_turma", joinColumns = @JoinColumn(name = "id_professor"), inverseJoinColumns = @JoinColumn(name = "id_turma"))
    @NotNull
    @NotEmpty
    private List<Turma> turmas;

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turma) {
        this.turmas = turma;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Professor{" + "id=" + id + "turmas=" + turmas + ", rg='" + rg + '\'' + ", cpf='" + cpf + '\''
                + ", nome='" + nome + '\'' + ", acesso='" + acesso + '\'' + '\'' + ", endereco=" + endereco + '}';
    }
}
