package com.toth.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cronograma")
public class Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cronograma")
    private Long id;

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = DiaLetivo.class)
    private List<DiaLetivo> diasLetivos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DiaLetivo> getDiasLetivos() {
        return diasLetivos;
    }

    public void setDiasLetivos(List<DiaLetivo> diasLetivos) {
        this.diasLetivos = diasLetivos;
    }

    @Override
    public String toString() {
        return "Cronograma{" +
                "id=" + id +
                ", diasLetivos=" + diasLetivos +
                '}';
    }
}
