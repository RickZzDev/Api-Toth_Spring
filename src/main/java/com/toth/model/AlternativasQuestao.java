package com.toth.model;


import javax.persistence.*;

@Entity
@Table(name = "alternativas_questao")
public class AlternativasQuestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alternativa")
    private Long id;

    private char alternativa;

    private String enunciadoAlternativa;

    private Boolean correto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(char alternativa) {
        this.alternativa = alternativa;
    }

    public String getEnunciadoAlternativa() {
        return enunciadoAlternativa;
    }

    public void setEnunciadoAlternativa(String enunciadoAlternativa) {
        this.enunciadoAlternativa = enunciadoAlternativa;
    }

    public Boolean getCorreto() {
        return correto;
    }

    public void setCorreto(Boolean correto) {
        this.correto = correto;
    }

}
