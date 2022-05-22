package com.formation.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formation.projet.entities.*;

@Repository
public interface DomaineRepository extends JpaRepository<Domaine, Long>{

	boolean existsByLibelle(String libelle);

}
