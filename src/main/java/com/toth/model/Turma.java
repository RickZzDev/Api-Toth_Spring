package com.toth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import net.bytebuddy.implementation.bind.annotation.Default;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "turma")
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_turma")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Ano.class)
	@Cascade(CascadeType.DETACH)
	@JoinColumn(name = "id_ano", insertable = false, updatable = false)
	private Ano ano;

	@NotNull
	@NotEmpty
	private String identificador;

	private Integer numero_sala;

	@NotNull
	@NotEmpty
	private String turno;

	@ManyToMany(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
	@JoinTable(name = "turma_aulas", joinColumns = @JoinColumn(name = "id_turma"), inverseJoinColumns = @JoinColumn(name = "id_aula"))
	@NotNull
	@NotEmpty
	private List<Aulas> aulas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// public Materia getMateria() {
	// return materia;
	// }

	// public void setmateria(Materia materia) {
	// this.materia = materia;
	// }

	public Integer getNumeroSala() {
		return numero_sala;
	}

	public void setNumeroSala(Integer numero_sala) {
		this.numero_sala = numero_sala;
	}

	public Ano getAno() {
		return ano;
	}

	public void setAno(Ano ano) {
		this.ano = ano;
	}

	public List<Aulas> getAula() {
		return aulas;
	}

	public void setAula(List<Aulas> aulas) {
		this.aulas = aulas;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

}
