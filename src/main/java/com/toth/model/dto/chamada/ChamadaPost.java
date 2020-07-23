package com.toth.model.dto.chamada;

import java.util.Date;
import java.util.List;

public class ChamadaPost {

    private Date diaChamada;

    private Long idTurma;

    private List<Long> idAlunos;

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public List<Long> getIdAlunos() {
        return idAlunos;
    }

    public void setIdAlunos(List<Long> idAlunos) {
        this.idAlunos = idAlunos;
    }

    public Date getDiaChamada() {
        return diaChamada;
    }

    public void setDiaChamada(Date diaChamada) {
        this.diaChamada = diaChamada;
    }

}