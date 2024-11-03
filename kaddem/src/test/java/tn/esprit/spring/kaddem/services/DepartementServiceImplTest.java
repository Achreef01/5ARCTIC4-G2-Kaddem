package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartementServiceImplTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    private Departement departement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("Informatique");
    }

    @Test
    void retrieveAllDepartements() {
        // Arrange
        List<Departement> departements = new ArrayList<>();
        departements.add(departement);
        when(departementRepository.findAll()).thenReturn(departements);

        // Act
        List<Departement> result = departementService.retrieveAllDepartements();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Informatique", result.get(0).getNomDepart());
        verify(departementRepository, times(1)).findAll();
    }

    @Test
    void addDepartement() {
        // Arrange
        when(departementRepository.save(departement)).thenReturn(departement);

        // Act
        Departement result = departementService.addDepartement(departement);

        // Assert
        assertNotNull(result);
        assertEquals("Informatique", result.getNomDepart());
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    void updateDepartement() {
        // Arrange
        when(departementRepository.save(departement)).thenReturn(departement);

        // Act
        Departement result = departementService.updateDepartement(departement);

        // Assert
        assertNotNull(result);
        assertEquals("Informatique", result.getNomDepart());
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    void retrieveDepartement() {
        // Arrange
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        // Act
        Departement result = departementService.retrieveDepartement(1);

        // Assert
        assertNotNull(result);
        assertEquals("Informatique", result.getNomDepart());
        verify(departementRepository, times(1)).findById(1);
    }

    @Test
    void deleteDepartement() {
        // Arrange
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        // Act
        departementService.deleteDepartement(1);

        // Assert
        verify(departementRepository, times(1)).delete(departement);
    }

    @Test
    void retrieveDepartement_NotFound() {
        // Arrange
        when(departementRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            departementService.retrieveDepartement(1);
        });
        verify(departementRepository, times(1)).findById(1);
    }
}
