package com.toth.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contato")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contato")
    private Long id;

//    @Email(message = "O email deve ser válido!")
    @NotEmpty(message = "O email é obrigatório!")
    @Size(min = 5, max = 255, message = "O email deve conter entre 5 e 255 caracteres")
    private String email;

    @NotNull(message = "O celular é obrigatório!")
    @NotEmpty(message = "O celular é obrigatório!")
    @Size(min =9, max=15, message = "O celular deve ter entre 9 e 15 caracteres")
    private String celular;

    private String telefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", celular='" + celular + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
