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
	@JoinColumn(name = "id_professor")
	private Professor professor;

	@ManyToOne
	@JoinColumn(name = "id_materia")
	private Materia materia;

	private boolean geral;

	// @ManyToMany(cascade = CascadeType.ALL)
	// @JoinTable(name = "comunicado_turmas", joinColumns = @JoinColumn(name =
	// "id_comunicado"), inverseJoinColumns = @JoinColumn(name = "id_turma"))
	// private List<Turma> turmas;

	// public List<Turma> getTurmas() {
	// return turmas;
	// }

	// public void setTurmas(List<Turma> turmas) {
	// this.turmas = turmas;
	// }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean getGeral() {
		return geral;
	}

	public void setGeral(boolean geral) {
		this.geral = geral;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getPublicoAlvo() {
		return publico_alvo;
	}

	public void setPublicoAlvo(String publico_alvo) {
		this.publico_alvo = publico_alvo;
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

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "ComunicadoProfessor{" + "id=" + id + ", description='" + description + '\'' + ", title='" + title + '\''
				+ ", publico_alvo='" + publico_alvo + '\'' + ", professor=" + professor + ", materia=" + materia
				+ ", geral=" + geral +
				// ", turmas=" + turmas
				+'}';
	}
}