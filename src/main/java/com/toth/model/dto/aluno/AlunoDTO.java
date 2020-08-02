package com.toth.model.dto.aluno;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toth.model.Aluno;

public class AlunoDTO {
    private Long id;

    private String nome;

    @JsonIgnore
    private Aluno aluno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<AlunoDTO> toAluno(List<Aluno> alunos) {
        List<AlunoDTO> alunosNovos = new ArrayList<>();

        alunos.forEach(aluno -> {

            AlunoDTO alunoObj = new AlunoDTO();

            alunoObj.setId(aluno.getId());
            alunoObj.setNome(aluno.getNome());

            alunosNovos.add(alunoObj);

        });

        return alunosNovos;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

}