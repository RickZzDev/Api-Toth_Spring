package com.toth.model.dto.professores;

import com.toth.model.Aulas;
import com.toth.model.Materia;
import com.toth.model.Professor;

import javax.persistence.*;

public class ProfessorAulasResponse {

    private Long id;
    private Materia materia;

    public ProfessorAulasResponse(Aulas aula) {
        this.id = id;
        this.materia = materia;
    }
}
