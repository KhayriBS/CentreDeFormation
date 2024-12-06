package com.formation.projet.entities;



import jakarta.persistence.*;

import java.io.Serializable;



@Entity
public class Pays implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Pays_id")
	private long id;
	private String libelle;
	
	public Pays(long id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	public Pays() {
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
