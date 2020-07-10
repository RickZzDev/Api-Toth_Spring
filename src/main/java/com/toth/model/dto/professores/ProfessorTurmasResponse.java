package com.toth.model.dto.professores;

import com.sun.istack.NotNull;
import com.toth.model.Ano;
import com.toth.model.Aulas;
import com.toth.model.Turma;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ProfessorTurmasResponse {

    private Ano ano;
    private String identificador;
    private Integer numero_sala;
    private String turno;
    private List<Aulas> aulas;

    public ProfessorTurmasResponse(Turma turma) {
        this.ano = turma.getAno();
        this.identificador = turma.getIdentificador();
        this.numero_sala = turma.getNumeroSala();
        this.turno = turma.getTurno();
        this.aulas = turma.getAulas();
    }
}
