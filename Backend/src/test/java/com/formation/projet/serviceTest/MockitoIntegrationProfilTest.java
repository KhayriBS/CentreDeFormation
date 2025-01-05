package com.formation.projet.serviceTest;
import com.formation.projet.entities.Profil;
import com.formation.projet.repository.ProfilRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MockitoIntegrationProfilTest {
    @Autowired
    private ProfilRepository profilRepository;
    @BeforeEach
    void setup() {
        profilRepository.deleteAll(); // Nettoyage avant chaque test
}
    @Test
    void testIntegration_SaveAndFindAll() {
        // Arrange
        Profil studentProfil = new Profil();
        studentProfil.setLibelle("Étudiant");

        Profil professionalProfil = new Profil();
        professionalProfil.setLibelle("Professionnel");

        // Act
        profilRepository.save(studentProfil);
        profilRepository.save(professionalProfil);
        List<Profil> result = profilRepository.findAll();

        // Assert
        assertFalse(result.isEmpty(), "The result list should not be empty");
        assertEquals(2, result.size(), "The result list should contain 2 profiles");
        assertTrue(result.stream().anyMatch(p -> p.getLibelle().equals("Étudiant")),
                "The result should contain a profile with libelle 'Étudiant'");
        assertTrue(result.stream().anyMatch(p -> p.getLibelle().equals("Professionnel")),
                "The result should contain a profile with libelle 'Professionnel'");
    }


}
