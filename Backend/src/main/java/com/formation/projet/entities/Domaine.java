package com.formation.projet.entities;

import java.io.Serializable;

import jakarta.persistence.*;


@Entity
public class Domaine implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="domaineId")
	private long id;
	private String libelle;
	
	public Domaine(long id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
	public Domaine() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	
}
