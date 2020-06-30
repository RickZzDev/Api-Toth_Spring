package com.toth.model;

public class EscolaAutenticationRequest {

    private Escola escola;
    private final String jwt;

    public EscolaAutenticationRequest(String jwt, Escola escola) {
        this.jwt = jwt;
        this.escola = escola;
    }

    public String getJwt() {
        return jwt;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

}
