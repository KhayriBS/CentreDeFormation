package com.formation.projet.serviceTest;

import com.formation.projet.entities.Formateur;
import com.formation.projet.repository.FormateurRepository;
import com.formation.projet.response.MessageResponse;
import com.formation.projet.service.FormateurServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

public class FormateurServiceTest {
    @InjectMocks
    private FormateurServiceImpl formateurService; // Implémentation réelle du service

    @Mock
    private FormateurRepository formateurRepository; // Hypothèse que vous utilisez un repository

    public FormateurServiceTest() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
    }

    @Test
    void testSaveFormateur() {
        Formateur formateur = new Formateur();
        formateur.setId(1L);
        formateur.setNom("Dupont");

        when(formateurRepository.save(formateur)).thenReturn(formateur);

        MessageResponse response = formateurService.save(formateur);

        assertNotNull(response);
        assertEquals("Succès", response.getMessage(), "Le message de succès ne correspond pas");
        verify(formateurRepository, times(1)).save(formateur);
    }

    @Test
    void testFindAllFormateurs() {
        List<Formateur> mockFormateurs = new ArrayList<>();
        mockFormateurs.add(new Formateur(1L, "Dupont"));
        mockFormateurs.add(new Formateur(2L, "Durand"));

        when(formateurRepository.findAll()).thenReturn(mockFormateurs);

        List<Formateur> formateurs = formateurService.findAll();

        assertNotNull(formateurs);
        assertEquals(2, formateurs.size());
        verify(formateurRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Mock de l'objet Formateur attendu
        Formateur formateurMock = new Formateur();
        formateurMock.setId(1L);
        formateurMock.setNom("Dupont");

        // Simulation du comportement du repository
        when(formateurRepository.findById(1L)).thenReturn(Optional.of(formateurMock));

        // Appel de la méthode à tester
        Formateur foundFormateur = formateurService.findById(1L);

        // Assertions
        assertNotNull(foundFormateur, "Le formateur trouvé ne doit pas être null");
        assertEquals("Dupont", foundFormateur.getNom(), "Le nom du formateur ne correspond pas");
        verify(formateurRepository, times(1)).findById(1L);
    }
}
