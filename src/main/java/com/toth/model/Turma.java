package com.toth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import com.toth.model.dto.turma.TurmaRequest;
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

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Ano.class, cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_ano", insertable = false, updatable = false)
	private Ano ano;

	@NotNull
	@NotEmpty
	private String identificador;

	@JsonProperty("numero_sala")
	private Integer numeroSala;

	@NotNull
	@NotEmpty
	private String turno;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_cronograma")
	private Cronograma cronograma;

	public Turma() {

	}

	public Turma(TurmaRequest turmaRequest) {
		this.ano = turmaRequest.getAno();
		this.identificador = turmaRequest.getIdentificador();
		this.numeroSala = turmaRequest.getNumeroSala();
		this.turno = turmaRequest.getTurno();
		this.cronograma = turmaRequest.getCronograma();
	}

	public Cronograma getCronograma() {
		return cronograma;
	}

	public void setCronograma(Cronograma cronograma) {
		this.cronograma = cronograma;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(Integer numero_sala) {
		this.numeroSala = numero_sala;
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

	@Override
	public String toString() {
		return "Turma{" +
				"id=" + id +
				", ano=" + ano +
				", identificador='" + identificador + '\'' +
				", numeroSala=" + numeroSala +
				", turno='" + turno + '\'' +
				", cronograma=" + cronograma +
				'}';
	}
}
