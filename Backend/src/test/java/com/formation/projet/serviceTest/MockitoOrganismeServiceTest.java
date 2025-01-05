package com.formation.projet.serviceTest;
import com.formation.projet.entities.Organisme;
import com.formation.projet.repository.OrganismeRepository;
import com.formation.projet.response.MessageResponse;
import com.formation.projet.service.OrganismeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MockitoOrganismeServiceTest {
    @InjectMocks
    private OrganismeServiceImpl organismeService;

    @Mock
    private OrganismeRepository organismeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        Organisme organisme1 = new Organisme(1L, "Organisme1");
        Organisme organisme2 = new Organisme(2L, "Organisme2");
        when(organismeRepository.findAll()).thenReturn(Arrays.asList(organisme1, organisme2));

        // Act
        List<Organisme> result = organismeService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(organismeRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        Organisme organisme = new Organisme(1L, "Organisme1");
        when(organismeRepository.findById(1L)).thenReturn(Optional.of(organisme));

        // Act
        Organisme result = organismeService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Organisme1", result.getLibelle());
        verify(organismeRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        when(organismeRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Organisme result = organismeService.findById(1L);

        // Assert
        assertNull(result);
        verify(organismeRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        // Arrange
        Organisme organisme = new Organisme(0L, "Organisme1");
        when(organismeRepository.existsByLibelle(organisme.getLibelle())).thenReturn(false);
        when(organismeRepository.save(organisme)).thenReturn(organisme);

        // Act
        MessageResponse response = organismeService.save(organisme);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Succès", response.getMessage());
        verify(organismeRepository, times(1)).existsByLibelle(organisme.getLibelle());
        verify(organismeRepository, times(1)).save(organisme);
    }

    @Test
    void testSave_AlreadyExists() {
        // Arrange
        Organisme organisme = new Organisme(0L, "Organisme1");
        when(organismeRepository.existsByLibelle(organisme.getLibelle())).thenReturn(true);

        // Act
        MessageResponse response = organismeService.save(organisme);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Echec !", response.getMessage());
        verify(organismeRepository, times(1)).existsByLibelle(organisme.getLibelle());
        verify(organismeRepository, never()).save(organisme);
    }

    @Test
    void testUpdate() {
        // Arrange
        Organisme existingOrganisme = new Organisme(1L, "Organisme1");
        Organisme updatedOrganisme = new Organisme(1L, "UpdatedOrganisme");
        when(organismeRepository.findById(1L)).thenReturn(Optional.of(existingOrganisme));
        when(organismeRepository.save(updatedOrganisme)).thenReturn(updatedOrganisme);

        // Act
        MessageResponse response = organismeService.update(1L, updatedOrganisme);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Succès", response.getMessage());
        verify(organismeRepository, times(1)).findById(1L);
        verify(organismeRepository, times(1)).save(updatedOrganisme);
    }

    @Test
    void testUpdate_NotFound() {
        // Arrange
        Organisme updatedOrganisme = new Organisme(1L, "UpdatedOrganisme");
        when(organismeRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        MessageResponse response = organismeService.update(1L, updatedOrganisme);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Echec !", response.getMessage());
        verify(organismeRepository, times(1)).findById(1L);
        verify(organismeRepository, never()).save(updatedOrganisme);
    }

    @Test
    void testDelete() {
        // Arrange
        Organisme organisme = new Organisme(1L, "Organisme1");
        when(organismeRepository.findById(1L)).thenReturn(Optional.of(organisme));

        // Act
        MessageResponse response = organismeService.delete(1L);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Succès", response.getMessage());
        verify(organismeRepository, times(1)).findById(1L);
        verify(organismeRepository, times(1)).delete(organisme);
    }

    @Test
    void testDelete_NotFound() {
        // Arrange
        when(organismeRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        MessageResponse response = organismeService.delete(1L);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Echec !", response.getMessage());
        verify(organismeRepository, times(1)).findById(1L);
        verify(organismeRepository, never()).delete(any());
    }
}
