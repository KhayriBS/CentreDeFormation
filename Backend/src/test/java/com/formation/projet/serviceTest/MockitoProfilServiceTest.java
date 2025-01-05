package com.formation.projet.serviceTest;
import com.formation.projet.entities.Profil;
import com.formation.projet.repository.ProfilRepository;
import com.formation.projet.response.MessageResponse;
import com.formation.projet.service.ProfilServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
public class MockitoProfilServiceTest {
    @InjectMocks
    private ProfilServiceImpl profilService;

    @Mock
    private ProfilRepository profilRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSave_NewStudentProfil() {
        // Arrange
        Profil profil = new Profil();
        profil.setLibelle("Étudiant");

        when(profilRepository.existsByLibelle(profil.getLibelle())).thenReturn(false);
        when(profilRepository.save(profil)).thenReturn(profil);

        // Act
        MessageResponse response = profilService.save(profil);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Succès", response.getMessage());
        verify(profilRepository, times(1)).save(profil);
    }

    @Test
    void testSave_ExistingProfessionalProfil() {
        // Arrange
        Profil profil = new Profil();
        profil.setLibelle("Professionnel");

        when(profilRepository.existsByLibelle(profil.getLibelle())).thenReturn(true);

        // Act
        MessageResponse response = profilService.save(profil);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Echec !", response.getMessage());
        verify(profilRepository, never()).save(profil);
    }

    @Test
    void testFindAll() {
        // Arrange
        Profil studentProfil = new Profil(1L, "Étudiant");
        Profil professionalProfil = new Profil(2L, "Professionnel");

        when(profilRepository.findAll()).thenReturn(Arrays.asList(studentProfil, professionalProfil));

        // Act
        List<Profil> result = profilService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(profilRepository, times(1)).findAll();
    }

    @Test
    void testFindById_StudentProfil() {
        // Arrange
        Profil profil = new Profil(1L, "Étudiant");
        when(profilRepository.findById(1L)).thenReturn(Optional.of(profil));

        // Act
        Profil result = profilService.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Étudiant", result.getLibelle());
        verify(profilRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NonExistingProfil() {
        // Arrange
        when(profilRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Profil result = profilService.findById(1L);

        // Assert
        assertNull(result);
        verify(profilRepository, times(1)).findById(1L);
    }



}
