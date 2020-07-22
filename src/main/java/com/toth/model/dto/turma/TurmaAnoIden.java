package com.toth.model.dto.turma;

import java.util.ArrayList;
import java.util.List;

import com.toth.model.Ano;
import com.toth.model.Turma;

public class TurmaAnoIden {
    private Long id;

    private String ano;

    private String identificador;

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public List<TurmaAnoIden> toTurma(List<Turma> turmas) {
        List<TurmaAnoIden> turmasNovas = new ArrayList<>();

        turmas.forEach(turma -> {

            TurmaAnoIden turmaObj = new TurmaAnoIden();

            turmaObj.setAno(turma.getAno().getAno());
            turmaObj.setIdentificador(turma.getIdentificador());
            turmaObj.setId(turma.getId());

            turmasNovas.add(turmaObj);

        });

        // turmaNova.setAno(turma.getAno());
        // turmaNova.setIdentificador(turma.getIdentificador());
        // turma.setAno(this.ano);
        // turma.setIdentificador(this.identificador);

        return turmasNovas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
