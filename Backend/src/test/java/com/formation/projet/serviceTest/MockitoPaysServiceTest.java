package com.formation.projet.serviceTest;
import com.formation.projet.entities.Pays;
import com.formation.projet.repository.PaysRepository;
import com.formation.projet.response.MessageResponse;
import com.formation.projet.service.PaysServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MockitoPaysServiceTest {
    @InjectMocks
    private PaysServiceImpl paysService;

    @Mock
    private PaysRepository paysRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Unit Tests
    @Test
    void testSave_Success() {
        // Arrange
        Pays pays = new Pays(1L, "France");
        when(paysRepository.existsByLibelle(pays.getLibelle())).thenReturn(false);
        when(paysRepository.save(pays)).thenReturn(pays);

        // Act
        MessageResponse response = paysService.save(pays);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Succès", response.getMessage());
        verify(paysRepository, times(1)).existsByLibelle(pays.getLibelle());
        verify(paysRepository, times(1)).save(pays);
    }

    @Test
    void testSave_AlreadyExists() {
        // Arrange
        Pays pays = new Pays(0L, "France");
        when(paysRepository.existsByLibelle(pays.getLibelle())).thenReturn(true);

        // Act
        MessageResponse response = paysService.save(pays);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Echec !", response.getMessage());
        verify(paysRepository, times(1)).existsByLibelle(pays.getLibelle());
        verify(paysRepository, never()).save(pays);
    }

    @Test
    void testUpdate_Success() {
        // Arrange
        Pays existingPays = new Pays(1L, "France");
        Pays updatedPays = new Pays(1L, "Germany");
        when(paysRepository.findById(1L)).thenReturn(Optional.of(existingPays));
        when(paysRepository.save(updatedPays)).thenReturn(updatedPays);

        // Act
        MessageResponse response = paysService.update(1L, updatedPays);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Succès", response.getMessage());
        verify(paysRepository, times(1)).findById(1L);
        verify(paysRepository, times(1)).save(updatedPays);
    }

    @Test
    void testUpdate_NotFound() {
        // Arrange
        Pays updatedPays = new Pays(1L, "Germany");
        when(paysRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        MessageResponse response = paysService.update(1L, updatedPays);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Echec !", response.getMessage());
        verify(paysRepository, times(1)).findById(1L);
        verify(paysRepository, never()).save(updatedPays);
    }

    @Test
    void testDelete_Success() {
        // Arrange
        Pays pays = new Pays(1L, "France");
        when(paysRepository.findById(1L)).thenReturn(Optional.of(pays));

        // Act
        MessageResponse response = paysService.delete(1L);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Succès", response.getMessage());
        verify(paysRepository, times(1)).findById(1L);
        verify(paysRepository, times(1)).delete(pays);
    }

    @Test
    void testDelete_NotFound() {
        // Arrange
        when(paysRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        MessageResponse response = paysService.delete(1L);
        assertFalse(response.isSuccess());
        assertEquals("Echec !", response.getMessage());
    }



    // Integration Test



    @Autowired
    private PaysRepository actualPaysRepository;
    @Test
    void testIntegration_SaveAndFind() {
        Pays pays = new Pays();
        pays.setLibelle("Spain");
        actualPaysRepository.save(pays);
        List<Pays> result = actualPaysRepository.findAll();

        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(p -> p.getLibelle().equals("Spain")));
    }

}
