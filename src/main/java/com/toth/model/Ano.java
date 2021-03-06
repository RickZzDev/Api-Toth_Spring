package com.toth.model;

import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.Synchronize;

import javax.persistence.*;

@Entity
@Table(name = "ano")
public class Ano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ano")
    private Long id;

    private String ano;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }


    @Override
    public String toString() {
        return "Ano{" +
                "id=" + id +
                ", ano=" + ano +
                '}';
    }
}
