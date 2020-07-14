package com.toth.model.dto.professores;

import com.toth.model.Professor;
import com.toth.model.Turma;

import java.util.List;

public class ProfessorTurmaResponse {

    private Professor professor;
    private List<Turma> turmasDoProfessor;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Turma> getTurmasDoProfessor() {
        return turmasDoProfessor;
    }

    public void setTurmasDoProfessor(List<Turma> turmasDoProfessor) {
        this.turmasDoProfessor = turmasDoProfessor;
    }
}
