package com.toth.enums;

public enum TypeEscola {

    FUNDAMENTAL("Fundamental"),
    MEDIO("Medio"),
    FUNDAMENTAL_MEDIO("Fundamental/Medio");

    private String descricao;

    TypeEscola(String fundamental) {
        this.descricao = fundamental;
    }

    public String getDescricao() {
        return this.descricao;
    }

}
