package com.formation.projet.controller;



import java.util.List;

import com.formation.projet.entities.*;
import com.formation.projet.repository.*;
import com.formation.projet.service.SessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.formation.projet.response.MessageResponse;

import com.formation.projet.service.ParticipantServiceImpl;


@RestController
@CrossOrigin("*")
@RequestMapping("/participant")
public class ParticipantController {
	@Autowired
	   private ParticipantServiceImpl participantService;

    @Autowired
    private SessionServiceImpl sessionService;
    @Autowired
   private ParticipantRepository participantRepository;

    @Autowired
    private SessionRepository sessionRepository;
	
	@PostMapping
    @PreAuthorize("hasRole('ROLE_USER') ")
    public MessageResponse save(@RequestBody Participant participant) {
        return participantService.save(participant);
    }

 // hedhi manest7a9ouhesh
    @PostMapping("/two")
    public  MessageResponse  savePersonne (@RequestBody Participant participant) {
        return participantService.savePersonne(participant);
    }



//nest7a9ou hedhi
@PreAuthorize("hasRole('ROLE_USER') ")
    @PostMapping("/{sessionId}")
    public ResponseEntity<Participant> add(@PathVariable(value = "sessionId") Long sessionId,
                                          @RequestBody Participant participantRequest) {
              Participant participant = sessionRepository.findById(sessionId).map(session -> {
            long participantId = participantRequest.getId();
            // participant exsist déja
            if (participantId != 0L) {
                Participant _participant = participantRepository.findById(participantId);
              session.getParticipant().add(_participant);
             sessionRepository.save(session);
                return _participant;
            }

            return null;
        }).orElseThrow(() -> new IllegalStateException("Not found formation with id = " + sessionId));

        return new ResponseEntity<>(participant, HttpStatus.CREATED);

    }



    @GetMapping

    public List<Participant> findAll() {
        return participantService.findAll();
    }
	
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') ")
    public MessageResponse update(@PathVariable int id, @RequestBody Participant participant) {
    	return participantService.update(id, participant);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER') ")
    public ResponseEntity<Participant> updateEmployee(@RequestBody Participant participant) {
        Participant update = participantService.updateParticipant(participant);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') ")
    public MessageResponse delete(@PathVariable long id) {
    	return participantService.delete(id);
    }
    
    @GetMapping("/{id}")

    public Participant findById(@PathVariable long id) {
        return participantService.findById(id);
    }






	



	




}



