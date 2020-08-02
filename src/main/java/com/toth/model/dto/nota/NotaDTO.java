package com.toth.model.dto.nota;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toth.model.Aluno;
import com.toth.model.dto.aluno.AlunoDTO;

public class NotaDTO {
    private Long valor;

    private AlunoDTO alunoDTO;

    private Long idProva;

    // @JsonIgnore
    // private Aluno aluno;

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public AlunoDTO getAlunoDTO() {
        return alunoDTO;
    }

    public void setAlunoDTO(AlunoDTO alunoDTO) {
        this.alunoDTO = alunoDTO;
    }

    public Long getIdProva() {
        return idProva;
    }

    public void setIdProva(Long idProva) {
        this.idProva = idProva;
    }

    // public Aluno getAluno() {
    // return aluno;
    // }

    // public void setAluno(Aluno aluno) {
    // this.aluno = aluno;
    // }

}