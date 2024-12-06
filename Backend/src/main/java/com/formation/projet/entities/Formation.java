package com.formation.projet.entities;

import java.io.Serializable;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Formation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="formationId")
	private long id;
	private String titre;
	private String type;
	private int annee;
	private int nb;
	private int duree;
	private double bugdet;


	@ManyToOne
	@JoinColumn(name = "domaine_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	
	private Domaine domaine;

    //@ManyToMany(mappedBy = "formation")
   // private Set<Session> session = new HashSet<>();

//ena zedthaa
	@ManyToMany(cascade = {
		CascadeType.PERSIST,
				CascadeType.MERGE
	})
	@JoinTable(name = "formation_session",
			joinColumns = @JoinColumn(name = "formation_id"),
			inverseJoinColumns = @JoinColumn(name = "session_id")
	)

	private Set<Session> session = new HashSet<>();


	public Formation(Long id, String titre, String type, int annee, int nb, int duree, double bugdet, Domaine domaine
			) {
		//Set<Session> session
		super();
		this.id = id;
		this.titre = titre;
		this.type = type;
		this.annee = annee;
		this.nb = nb;
		this.duree = duree;
		this.bugdet = bugdet;
		this.domaine = domaine;
		//this.session = session;
	}

	public Formation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getNb() {
		return nb;
	}

	public void setNb(int nb) {
		this.nb = nb;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public double getBugdet() {
		return bugdet;
	}

	public void setBugdet(double bugdet) {
		this.bugdet = bugdet;
	}

	public Domaine getDomaine() {
		return domaine;
	}

	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}

	//@JsonIgnore
	public Set<Session> getSession() {
		return session;
	}


	public void setSession(Set<Session> session) {
		this.session = session;
	}


    
}
