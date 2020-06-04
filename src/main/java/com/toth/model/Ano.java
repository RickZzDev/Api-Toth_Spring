package com.toth.model;

import javax.persistence.*;

@Entity
@Table(name = "ano")
public class Ano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ano")
    private Long id_ano;

    private Integer ano;

    public Long getId_ano() {
        return id_ano;
    }

    public void setId_ano(Long id_ano) {
        this.id_ano = id_ano;
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
                "id=" + id_ano +
                ", ano=" + ano +
                '}';
    }
}
