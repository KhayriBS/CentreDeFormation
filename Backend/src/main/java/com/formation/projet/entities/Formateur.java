package com.formation.projet.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class Formateur implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId")
	private long id;
	private String nom;
	private String prenom;
	private String email;
	private int tel;
	private String type;
	
	
	//@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "organisme_id", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	//@JsonIgnore
	private Organisme organisme;


	public Formateur(long id, String nom, String prenom, String email, int tel, String type, Organisme organisme) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.type = type;
		this.organisme = organisme;
	}


	public Formateur(long l, String dupont) {
		super();
	}

    public Formateur() {

    }


    public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getTel() {
		return tel;
	}


	public void setTel(int tel) {
		this.tel = tel;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Organisme getOrganisme() {
		return organisme;
	}


	public void setOrganisme(Organisme organisme) {
		this.organisme = organisme;
	}

	
	
}
