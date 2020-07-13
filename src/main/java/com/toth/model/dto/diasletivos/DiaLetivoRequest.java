package com.toth.model.dto.diasletivos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DiaLetivoRequest {

    @JsonProperty("ids_aulas")
    private List<Long> idsAulas;

    private String dia;

    public List<Long> getIdsAulas() {
        return idsAulas;
    }

    public void setIdsAulas(List<Long> idsAulas) {
        this.idsAulas = idsAulas;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
