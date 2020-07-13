package com.toth.enums;

public enum TipoQuestao {

    DISSERTATIVA("Dissertativa"),
    VERDADEIRO_OU_FALSO("Verdadeiro ou falso"),
    MULTIPLA_ESCOLHA("Múltipla escolha");

    private String descricao;

    TipoQuestao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
