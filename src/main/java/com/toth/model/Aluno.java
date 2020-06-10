package com.toth.model;


import javax.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_responsavel")
    private Responsavel responsavel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contato")
    private Contato contato;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_acesso")
    private Acesso acesso;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="id_turma")
    private Turma turma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", responsavel=" + responsavel +
                ", contato=" + contato +
                ", acesso=" + acesso +
                ", turma=" + turma +
                '}';
    }
}
