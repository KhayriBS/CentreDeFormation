package com.formation.projet.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.projet.entities.*;
import com.formation.projet.repository.DomaineRepository;
import com.formation.projet.response.MessageResponse;

@Service

public class DomaineServiceImpl implements DomaineService {

	@Autowired
	DomaineRepository domaineRepository;

    @Transactional
	@Override
	public MessageResponse save(Domaine domaine) {
		boolean existe = domaineRepository.existsByLibelle(domaine.getLibelle());
        if (existe){
        	return new MessageResponse(false,"Echec !","Ce domaine existe déja !");   
        } else {
        domaineRepository.save(domaine);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
        }
    }

    public Domaine updateDomaine(Domaine domaine) {
        return domaineRepository.save(domaine);
    }
    
    @Transactional
    @Override
    public MessageResponse update(long id, Domaine domaine) {
    	Domaine existe = findById(id);
        if (existe == null){
        	return new MessageResponse(false,"Echec !","Ce domaine n'existe pas !");   
        } else {
        //delete(id);
        domaine.setId(id);
        domaineRepository.save(domaine);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
        }
    }

    @Transactional
    @Override
    public MessageResponse delete(long id) {
        Domaine domaine = domaineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ce domaine n'existe pas !"));

        domaineRepository.delete(domaine);
        return new MessageResponse(true, "Succès", "Opération réalisée avec succès.");
    }



    @Override
    public List<Domaine> findAll() {

        return domaineRepository.findAll();
    }


	@Override
	public Domaine findById(long id) {
		Domaine domaine = domaineRepository.findById(id).orElse(null);
        return domaine;
	}

}
