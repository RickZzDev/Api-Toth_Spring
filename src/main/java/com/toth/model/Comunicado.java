package com.toth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Dynamic;

@Entity
@Table(name = "comunicado")
public class Comunicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comunicado")
    private Long id;

    private String description;

    private String title;

    private Dynamic emissor;

    private Materia materia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Materia getMateria(Materia materia) {
        return materia;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Dynamic getEmissor() {
        return emissor;
    }

    public void setEmissor(Dynamic emissor) {
        this.emissor = emissor;
    }

    @Override
    public String toString() {
        return "Ano{" + "id=" + id + ", materia=" + materia + ", descrição=" + description + "titulo=" + title
                + "emissor=" + emissor + '}';
    }

}