package com.formation.projet.entities;

import java.io.Serializable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sessionId")
	private long id;

	private String lieu;
	private String date_debut;
	private String date_fin;
	private int nb;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "organisme_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Organisme organisme;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "formateur_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Formateur formateur;
	
	
	@ManyToMany(cascade = {
	    CascadeType.PERSIST,
	    CascadeType.MERGE
	})
	@JoinTable(name = "participant_session",
	    joinColumns = @JoinColumn(name = "session_id"),
	    inverseJoinColumns = @JoinColumn(name = "participant_id")
	)

	private Set<Participant> participant = new HashSet<>();
	/*@ManyToMany(cascade = {
		    CascadeType.PERSIST,
		    CascadeType.MERGE})
		@JoinTable(name = "formation_session",
		    joinColumns = @JoinColumn(name = "session_id"),
		    inverseJoinColumns = @JoinColumn(name = "formation_id")
		)*/
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},mappedBy = "session") //ena zedthaaa

	@JsonIgnore
		private Set<Formation> formation = new HashSet<>();
	/*public Session(Long id, String lieu, String date_debut, String date_fin, int nb, Organisme organisme,
			Formateur formateur, Set<Participant> participant, Set<Formation> formation) {
		super();
		this.id = id;
		this.lieu = lieu;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.nb = nb;
		this.organisme = organisme;
		this.formateur = formateur;
		this.participant = participant;
		this.formation = formation;
	}
*/
	public Session() {
		//super();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(String date_debut) {
		this.date_debut = date_debut;
	}

	public String getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}

	public int getNb() {
		return nb;
	}

	public void setNb(int nb) {
		this.nb = nb;
	}

	public Organisme getOrganisme() {
		return organisme;
	}

	public void setOrganisme(Organisme organisme) {
		this.organisme = organisme;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	/*public Collection<Participant> getParticipant() {
		return participant;
	}
	public void setParticipant(Collection<Participant> vehicles) {
		this.participant = participant;
	}
	*/
	public Set<Participant> getParticipant() {
		return participant;
	}

	public void setParticipant(Set<Participant> participant) {
		this.participant = participant;
	}

	public Set<Formation> getFormation() {
		return formation;
	}

	public void setFormation(Set<Formation> formation) {
		this.formation = formation;
	}



}
