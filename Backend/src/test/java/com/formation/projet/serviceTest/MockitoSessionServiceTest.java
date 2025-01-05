package com.formation.projet.serviceTest;
import com.formation.projet.entities.Session;
import com.formation.projet.repository.SessionRepository;
import com.formation.projet.response.MessageResponse;
import com.formation.projet.service.SessionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class MockitoSessionServiceTest {
    @InjectMocks
    private SessionServiceImpl sessionService;

    @Mock
    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_Success() {
        // Arrange
        Session session = new Session();
        session.setId(1L);

        when(sessionRepository.existsById(session.getId())).thenReturn(false);
        when(sessionRepository.save(session)).thenReturn(session);

        // Act
        MessageResponse response = sessionService.save(session);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Succès", response.getMessage()); // Updated to match the actual value
        verify(sessionRepository, times(1)).save(session);
    }

    @Test
    void testSave_Failure_SessionExists() {
        // Arrange
        Session session = new Session();
        session.setId(1L);

        when(sessionRepository.existsById(session.getId())).thenReturn(true);

        // Act
        MessageResponse response = sessionService.save(session);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Echec !", response.getMessage());
        assertEquals("Ce session existe déja !", response.getDetail());
        verify(sessionRepository, never()).save(any());
    }

    @Test
    void testUpdate_Success() {
        // Arrange
        long sessionId = 1L;
        Session existingSession = new Session();
        existingSession.setId(sessionId);

        Session updatedSession = new Session();
        updatedSession.setId(sessionId);
        updatedSession.setLieu("Updated Location");

        when(sessionRepository.findById(sessionId)).thenReturn(existingSession);
        when(sessionRepository.save(any(Session.class))).thenReturn(updatedSession);

        // Act
        MessageResponse response = sessionService.update(sessionId, updatedSession);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Succès", response.getMessage());
        verify(sessionRepository, times(1)).save(updatedSession);
    }

    @Test
    void testUpdate_Failure_SessionNotFound() {
        // Arrange
        long sessionId = 1L;
        Session updatedSession = new Session();

        when(sessionRepository.findById(sessionId)).thenReturn(null);

        // Act
        MessageResponse response = sessionService.update(sessionId, updatedSession);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Echec !", response.getMessage());
        assertEquals("Ce profil n'existe pas !", response.getDetail());
        verify(sessionRepository, never()).save(any());
    }

    @Test
    void testDelete_Success() {
        // Arrange
        long sessionId = 1L;
        Session session = new Session();
        session.setId(sessionId);

        when(sessionRepository.findById(sessionId)).thenReturn(session);

        // Act
        MessageResponse response = sessionService.delete(sessionId);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Succès", response.getMessage()); // Updated to match the actual value
        verify(sessionRepository, times(1)).delete(session);
    }

    @Test
    void testDelete_Failure_SessionNotFound() {
        // Arrange
        long sessionId = 1L;

        when(sessionRepository.findById(sessionId)).thenReturn(null);

        // Act
        MessageResponse response = sessionService.delete(sessionId);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Echec !", response.getMessage());
        assertEquals("Ce profil n'existe pas !", response.getDetail());
        verify(sessionRepository, never()).delete(any());
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Session> sessions = new ArrayList<>();
        sessions.add(new Session());
        sessions.add(new Session());

        when(sessionRepository.findAll()).thenReturn(sessions);

        // Act
        List<Session> result = sessionService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(sessionRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        // Arrange
        long sessionId = 1L;
        Session session = new Session();
        session.setId(sessionId);

        when(sessionRepository.findById(sessionId)).thenReturn(session);

        // Act
        Session result = sessionService.findById(sessionId);

        // Assert
        assertNotNull(result);
        assertEquals(sessionId, result.getId());
        verify(sessionRepository, times(1)).findById(sessionId);
    }

    @Test
    void testFindById_Failure() {
        // Arrange
        long sessionId = 1L;

        when(sessionRepository.findById(sessionId)).thenReturn(null);

        // Act
        Session result = sessionService.findById(sessionId);

        // Assert
        assertNull(result);
        verify(sessionRepository, times(1)).findById(sessionId);
    }
}
