package com.toth.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resposta")
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resposta")
    private Long id;

    private String txtResposta;

    private String alternativa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTxtResposta() {
        return txtResposta;
    }

    public void setTxtResposta(String txtResposta) {
        this.txtResposta = txtResposta;
    }

    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "Resposta [alternativa=" + alternativa + ", aluno=" + aluno + ", id=" + id + ", txtResposta="
                + txtResposta + "]";
    }

}