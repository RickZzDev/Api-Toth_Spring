package com.toth.model.dto.escola;

import org.hibernate.validator.constraints.br.CNPJ;

public class EscolaCNPJDTO {

    @CNPJ
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
