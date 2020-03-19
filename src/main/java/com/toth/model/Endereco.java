package com.toth.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O número deve ser preenchido")
    @Size(min = 1, max = 4, message = "O número deve ter entre 1 e 4 caracteres")
    private String numero;

    @NotEmpty(message = "O logradouro deve ser preenchido")
    @Size(min = 1, max = 4, message = "O número deve ter até 225 caracteres")
    private String logradouro;

    @NotEmpty(message = "O bairro deve ser preenchido")
    @Size(max = 50, message="O bairro deve ter até 50 caracteres")
    private String bairro;

    @NotEmpty(message = "O cidade deve ser preenchido")
    @Size(max = 50, message="A cidade deve ter até 50 caracteres")
    private String cidade;

    @NotEmpty(message = "O estado deve ser preenchido")
    @Size(max = 50, message="O estado deve ter até 50 caracteres")
    private String estado;

    @NotEmpty(message = "O CEP deve ser preenchido!")
    @Size(max = 9, message="O cep deve ter até 9 caracteres")
    private String cep;

    @Size(max = 225, message="O complemento deve ter até 225 caracteres")
    private String complemento;

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}
