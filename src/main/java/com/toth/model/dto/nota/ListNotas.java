package com.toth.model.dto.nota;

import java.util.List;

public class ListNotas {

    List<NotaByAluno> notaByAlunos;

    public List<NotaByAluno> getNotaByAlunos() {
        return notaByAlunos;
    }

    public void setNotaByAlunos(List<NotaByAluno> notaByAlunos) {
        this.notaByAlunos = notaByAlunos;
    }

}