package com.formation.projet.entities;

import jakarta.persistence.*;

import java.io.Serializable;



@Entity
public class Organisme implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="organisme_id")
	private Long id;
	private String libelle;
	
	public Organisme(Long id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	public Organisme() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	

}
