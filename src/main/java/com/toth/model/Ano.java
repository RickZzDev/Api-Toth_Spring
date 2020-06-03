package com.toth.model;

import javax.persistence.*;

@Entity
@Table(name = "ano")
public class Ano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ano;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
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
