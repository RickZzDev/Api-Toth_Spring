package com.toth.model;

import com.toth.enums.TipoQuestao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questao")
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questao")
    private Long id;
    
    @Enumerated(value = EnumType.STRING)
    private TipoQuestao tipo;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = AlternativasQuestao.class)
    private List<AlternativasQuestao> alternativasQuestao;

    private Double pontos;
    private String enunciado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo.getDescricao();
    }

    public void setTipo(TipoQuestao tipo) {
        this.tipo = tipo;
    }

    public List<AlternativasQuestao> getAlternativasQuestao() {
        return alternativasQuestao;
    }

    public void setAlternativasQuestao(List<AlternativasQuestao> alternativasQuestao) {
        this.alternativasQuestao = alternativasQuestao;
    }

    public Double getPontos() {
        return pontos;
    }

    public void setPontos(Double pontos) {
        this.pontos = pontos;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
}
