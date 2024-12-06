package com.formation.projet.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity(name = "participant")
public class Participant {

//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="participantId")
	private Long id;
	private String nom;
	private String prenom;
	private String email;
	private int tel;
	private String type;
	
	
	@ManyToOne
	@JoinColumn(name = "profil_id")
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private Profil profil;
	
	@ManyToOne
	@JoinColumn(name = "pays_id")
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private Pays pays;
	
	@ManyToOne
	@JoinColumn(name = "organisme_id")
	//@OnDelete(action = OnDeleteAction.CASCADE)

	private Organisme organisme;



	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "participant")
    

	@JsonIgnore
    private Set<Session> session = new HashSet<>();

	public Participant() {
		super();
	}


	public Participant(Long id, String nom, String prenom, String email, int tel,String type, Profil profil,
					   Pays pays, Organisme organisme, Set<Session> session) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.type = type;
		this.profil = profil;
		this.pays = pays;
		this.organisme = organisme;
		this.session = session;
		
	}


	public Long  getId() {
		return id;
	}


	public void setId(Long  id) {
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



	public Profil getProfil() {
		return profil;
	}


	public void setProfil(Profil profil) {
		this.profil = profil;
	}


	public Pays getPays() {
		return pays;
	}


	public void setPays(Pays pays) {
		this.pays = pays;
	}


	public Organisme getOrganisme() {
		return organisme; 
	}


	public void setOrganisme(Organisme organisme) {
		this.organisme = organisme;
	}


	public Set<Session> getSession() {
		return session;
	}

	public void setSession(Set<Session> session) {
		this.session = session;
	}
	
}
