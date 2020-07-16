package com.toth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toth.enums.TypeEscola;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.websocket.OnError;
import java.util.List;

@Entity
@Table(name = "escola")
public class Escola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escola")
    private Long id;

    @Email(message = "O email deve ser válido!")
    @NotEmpty(message = "O email é obrigatório!")
    @Size(min = 5, max = 255, message = "O email deve conter entre 5 e 255 caracteres")
    private String email;

    @NotEmpty(message = "O nome é obrigatório!")
    @Size(min = 3, max = 255, message = "O nome deve conter entre 8 e 255 caracteres")
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_acesso")
    private Acesso acesso;

    @NotEmpty(message = "O cnpj é obrigatório!")
    @Size(min = 3, max = 255, message = "O cnpj deve conter entre 3 e 255 caracteres")
    @Column(unique = true)
    @CNPJ
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

    private Boolean pagamentoStatus;

    @Enumerated(value = EnumType.STRING)
    @JsonProperty("tipo_escola")
    private TypeEscola typeEscola;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "escolas_anos",
            joinColumns = @JoinColumn(name = "id_escola"),
            inverseJoinColumns = @JoinColumn(name = "id_ano")
    )
    private List<Ano> anos;

    public Long getId() {
        return id;
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

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public String getTypeEscola() {
        return typeEscola == null ? null : typeEscola.getDescricao();
    }

    public TypeEscola getTipoDeEscola() {return typeEscola;}

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

    @Override
    public String toString() {
        return "Escola{" + "id=" + id + ", email='" + email + '\'' + ", nome='" + nome + '\'' + '\'' + ", acesso='"
                + acesso + '\'' + ", cnpj='" + cnpj + '\'' + ", endereco='" + endereco + '\'' + ", pagamentoStatus="
                + pagamentoStatus + '}';
    }
}
