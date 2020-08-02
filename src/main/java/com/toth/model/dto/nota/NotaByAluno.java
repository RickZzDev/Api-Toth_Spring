package com.toth.model.dto.nota;

public class NotaByAluno {

    private String nomeProva;

    private double nota;

    private double notaGeral;

    public String getNomeProva() {
        return nomeProva;
    }

    public void setNomeProva(String nomeProva) {
        this.nomeProva = nomeProva;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public double getNotaGeral() {
        return notaGeral;
    }

    public void setNotaGeral(double notaGeral) {
        this.notaGeral = notaGeral;
    }

}