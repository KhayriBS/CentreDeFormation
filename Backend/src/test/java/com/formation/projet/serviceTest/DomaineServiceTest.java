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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        Domaine domaine1 = new Domaine(1L, "Technology");
        Domaine domaine2 = new Domaine(2L, "Education");
        when(domaineRepository.findAll()).thenReturn(Arrays.asList(domaine1, domaine2));

        // Act
        List<Domaine> result = domaineService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Technology", result.get(0).getLibelle());
        verify(domaineRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        Domaine domaine = new Domaine(1L, "Technology");
        when(domaineRepository.findById(1L)).thenReturn(Optional.of(domaine));

        // Act
        Domaine result = domaineService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Technology", result.getLibelle());
        verify(domaineRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        // Arrange
        Domaine domaine = new Domaine(0, "Health");
        when(domaineRepository.save(domaine)).thenReturn(domaine);

        // Act
        MessageResponse response = domaineService.save(domaine);

        // Assert
        assertNotNull(response);
        assertEquals("Domaine saved successfully!", response.getMessage());
        verify(domaineRepository, times(1)).save(domaine);
    }

    @Test
    void testUpdate() {
        // Arrange
        Domaine domaine = new Domaine(1L, "Health");
        when(domaineRepository.findById(1L)).thenReturn(Optional.of(domaine));
        when(domaineRepository.save(domaine)).thenReturn(domaine);

        // Act
        MessageResponse response = domaineService.update(1, domaine);

        // Assert
        assertNotNull(response);
        assertEquals("Domaine updated successfully!", response.getMessage());
        verify(domaineRepository, times(1)).findById(1L);
        verify(domaineRepository, times(1)).save(domaine);
    }

    @Test
    void testDelete() {
        // Arrange
        Domaine domaine = new Domaine(1L, "Finance");
        when(domaineRepository.findById(1L)).thenReturn(Optional.of(domaine));

        // Act
        MessageResponse response = domaineService.delete(1);

        // Assert
        assertNotNull(response);
        assertEquals("Domaine deleted successfully!", response.getMessage());
        verify(domaineRepository, times(1)).findById(1L);
        verify(domaineRepository, times(1)).deleteById(1L);
    }
}
