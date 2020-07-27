package com.toth.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Dynamic;

@Entity
@Table(name = "comunicado")
public class ComunicadoProfessor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comunicado")
	private Long id;

	private String description;

	private String title;

	private String publico_alvo;

	@ManyToOne
	@JoinColumn(name = "id_aula")
	private Aula aula;

	private boolean geral;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "comunicado_turmas", joinColumns = @JoinColumn(name = "id_comunicado"), inverseJoinColumns = @JoinColumn(name = "id_turma"))
	private List<Turma> turmas;

	@Override
	public String toString() {
		return "ComunicadoProfessor [aula=" + aula + ", description=" + description + ", geral=" + geral + ", id=" + id
				+ ", publico_alvo=" + publico_alvo + ", title=" + title + ", turmas=" + turmas + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPublico_alvo() {
		return publico_alvo;
	}

	public void setPublico_alvo(String publico_alvo) {
		this.publico_alvo = publico_alvo;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public boolean isGeral() {
		return geral;
	}

	public void setGeral(boolean geral) {
		this.geral = geral;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
}