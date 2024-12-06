package com.formation.projet.entities;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
public class Profil implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="profilId")
	private long id;
	private String libelle;
	
	public Profil(long id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	public Profil() {
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
