package com.toth.model;

public class AuthenticationResponse {

    private Escola escola;
    private String jwt;

    public AuthenticationResponse() {

    }

    public AuthenticationResponse(Escola escola, String jwt) {
        this.escola = escola;
        this.jwt = jwt;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
