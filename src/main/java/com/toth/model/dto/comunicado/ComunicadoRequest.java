package com.toth.model.dto.comunicado;

import java.util.List;

import com.toth.model.Turma;

public class ComunicadoRequest {

    private String description;

    private String title;

    private String publico_alvo;

    private Long idAula;

    private boolean geral;

    private List<Turma> turmas;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublico_alvo() {
        return publico_alvo;
    }

    public void setPublico_alvo(String publico_alvo) {
        this.publico_alvo = publico_alvo;
    }

    public Long getIdAula() {
        return idAula;
    }

    public void setIdAula(Long idAula) {
        this.idAula = idAula;
    }

    public boolean isGeral() {
        return geral;
    }

    public void setGeral(boolean geral) {
        this.geral = geral;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

}