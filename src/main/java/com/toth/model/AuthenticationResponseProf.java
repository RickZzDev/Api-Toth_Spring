package com.toth.model;

public class AuthenticationResponseProf {

    private Professor professor;
    private String jwt;

    public AuthenticationResponseProf() {

    }

    public AuthenticationResponseProf(Professor professor, String jwt) {
        this.professor = professor;
        this.jwt = jwt;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
