package com.toth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "turma")
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_turma")
	private Long id;

	@ManyToOne
	@Cascade({ CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "id_ano")
	private Ano ano;

	@NotNull
	@NotEmpty
	private String identificador;

	private Integer numero_sala;

	@NotNull
	@NotEmpty
	private String turno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
