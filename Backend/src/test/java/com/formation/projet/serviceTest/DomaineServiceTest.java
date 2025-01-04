package com.formation.projet.serviceTest;

import com.formation.projet.entities.Domaine;
import com.formation.projet.repository.DomaineRepository;
import com.formation.projet.response.MessageResponse;
import com.formation.projet.service.DomaineServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DomaineServiceTest {
    @InjectMocks
    private DomaineServiceImpl domaineService;

    @Mock
    private DomaineRepository domaineRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        Domaine domaine1 = new Domaine(1L, "Technologie");
        Domaine domaine2 = new Domaine(2L, "Éducation");
        when(domaineRepository.findAll()).thenReturn(Arrays.asList(domaine1, domaine2));

        // Act
        List<Domaine> result = domaineService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(domaineRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        Domaine domaine = new Domaine(1L, "Technologie");
        when(domaineRepository.findById(1L)).thenReturn(Optional.of(domaine));

        // Act
        Domaine result = domaineService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Technologie", result.getLibelle());
        verify(domaineRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        when(domaineRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> domaineService.delete(1L));
        assertEquals("Ce domaine n'existe pas !", exception.getMessage());
    }

    @Test
    void testSave() {
        // Arrange
        Domaine domaine = new Domaine(0L, "Santé");
        when(domaineRepository.existsByLibelle(domaine.getLibelle())).thenReturn(false);
        when(domaineRepository.save(domaine)).thenReturn(domaine);

        // Act
        MessageResponse response = domaineService.save(domaine);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Succès", response.getMessage());
        verify(domaineRepository, times(1)).existsByLibelle(domaine.getLibelle());
        verify(domaineRepository, times(1)).save(domaine);
    }

    @Test
    void testSave_DomaineExists() {
        // Arrange
        Domaine domaine = new Domaine(0L, "Santé");
        when(domaineRepository.existsByLibelle(domaine.getLibelle())).thenReturn(true);

        // Act
        MessageResponse response = domaineService.save(domaine);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Echec !", response.getMessage());
        verify(domaineRepository, times(1)).existsByLibelle(domaine.getLibelle());
        verify(domaineRepository, never()).save(domaine);
    }

    @Test
    void testUpdate() {
        // Arrange
        Domaine existingDomaine = new Domaine(1L, "Technologie");
        Domaine updatedDomaine = new Domaine(1L, "Science");
        when(domaineRepository.findById(1L)).thenReturn(Optional.of(existingDomaine));
        when(domaineRepository.save(updatedDomaine)).thenReturn(updatedDomaine);

        // Act
        MessageResponse response = domaineService.update(1L, updatedDomaine);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Succès", response.getMessage());
        verify(domaineRepository, times(1)).findById(1L);
        verify(domaineRepository, times(1)).save(updatedDomaine);
    }

    @Test
    void testUpdate_NotFound() {
        // Arrange
        Domaine updatedDomaine = new Domaine(1L, "Science");
        when(domaineRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        MessageResponse response = domaineService.update(1L, updatedDomaine);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Echec !", response.getMessage());
        verify(domaineRepository, times(1)).findById(1L);
        verify(domaineRepository, never()).save(updatedDomaine);
    }

    @Test
    void testDelete() {
        // Arrange
        Domaine domaine = new Domaine(1L, "Technologie");
        when(domaineRepository.findById(1L)).thenReturn(Optional.of(domaine));

        // Act
        MessageResponse response = domaineService.delete(1L);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Succès", response.getMessage());
        verify(domaineRepository, times(1)).findById(1L);
        verify(domaineRepository, times(1)).delete(domaine);
    }

    @Test
    void testDelete_NotFound() {
        // Arrange
        when(domaineRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> domaineService.delete(1L));
        assertEquals("Ce domaine n'existe pas !", exception.getMessage());
    }
}
