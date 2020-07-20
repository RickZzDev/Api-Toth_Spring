package com.toth.model;

public class AuthenticationResponseAluno {

    private Aluno aluno;
    private String jwt;

    public AuthenticationResponseAluno() {

    }

    public AuthenticationResponseAluno(Aluno aluno, String jwt) {
        this.aluno = aluno;
        this.jwt = jwt;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}