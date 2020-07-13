package com.toth.model.dto.cronograma;

import java.util.List;

public class CronogramaRequest {

    private List<Long> idsDiasLetivos;

    public List<Long> getIdsDiasLetivos() {
        return idsDiasLetivos;
    }

    public void setIdsDiasLetivos(List<Long> idsDiasLetivos) {
        this.idsDiasLetivos = idsDiasLetivos;
    }
}
