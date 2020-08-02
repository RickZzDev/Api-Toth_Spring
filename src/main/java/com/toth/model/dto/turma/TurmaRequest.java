package com.toth.model.dto.turma;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import com.toth.model.Ano;
import com.toth.model.Aula;
import com.toth.model.Cronograma;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

public class TurmaRequest {

    private Long idAno;

    @JsonIgnore
    private Ano ano;

    private List<Long> idsAulas;

    @JsonIgnore
    private List<Aula> aulas;

    @NotNull
    @NotEmpty
    private String identificador;

    @JsonProperty("numero_sala")
    private Integer numeroSala;

    @NotNull
    @NotEmpty
    private String turno;

    // @JsonProperty("id_cronograma")
    // private Long idCronograma;

    @JsonIgnore
    private Cronograma cronograma;

    public Ano getAno() {
        return ano;
    }

    public void setAno(Ano ano) {
        this.ano = ano;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Cronograma getCronograma() {
        return cronograma;
    }

    public void setCronograma(Cronograma cronograma) {
        this.cronograma = cronograma;
    }

    // public Long getIdCronograma() {
    // return idCronograma;
    // }

    // public void setIdCronograma(Long idCronograma) {
    // this.idCronograma = idCronograma;
    // }

    public Long getIdAno() {
        return idAno;
    }

    public void setIdAno(Long idAno) {
        this.idAno = idAno;
    }

    public List<Long> getIdsAulas() {
        return idsAulas;
    }

    public void setIdsAulas(List<Long> idsAulas) {
        this.idsAulas = idsAulas;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }
}
