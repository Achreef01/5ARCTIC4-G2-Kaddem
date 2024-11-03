package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContratServiceImplTest {

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    private Contrat contrat;
    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        contrat = new Contrat();
        contrat.setIdContrat(1);
        contrat.setSpecialite(Specialite.IA);
        contrat.setArchive(false);
        contrat.setDateFinContrat(new Date(System.currentTimeMillis() + 86400000L)); // 1 day in future

        etudiant = new Etudiant();
        etudiant.setNomE("John");
        etudiant.setPrenomE("Doe");
        etudiant.setContrats(Set.of(contrat));
    }

    @Test
    void testRetrieveAllContrats() {
        // Arrange
        List<Contrat> contrats = new ArrayList<>();
        contrats.add(contrat);
        when(contratRepository.findAll()).thenReturn(contrats);

        // Act
        List<Contrat> actualContrats = contratService.retrieveAllContrats();

        // Assert
        assertEquals(1, actualContrats.size());
        assertEquals(contrat, actualContrats.get(0));
    }

    @Test
    void testAddContrat() {
        // Arrange
        when(contratRepository.save(contrat)).thenReturn(contrat);

        // Act
        Contrat actualContrat = contratService.addContrat(contrat);

        // Assert
        assertEquals(contrat, actualContrat);
        verify(contratRepository).save(contrat);
    }

    @Test
    void testUpdateContrat() {
        // Arrange
        when(contratRepository.save(contrat)).thenReturn(contrat);

        // Act
        Contrat updatedContrat = contratService.updateContrat(contrat);

        // Assert
        assertEquals(contrat, updatedContrat);
        verify(contratRepository).save(contrat);
    }

    @Test
    void testRetrieveContrat() {
        // Arrange
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        // Act
        Contrat actualContrat = contratService.retrieveContrat(1);

        // Assert
        assertEquals(contrat, actualContrat);
    }

    @Test
    void testRemoveContrat() {
        // Arrange
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        // Act
        contratService.removeContrat(1);

        // Assert
        verify(contratRepository).delete(contrat);
    }

    @Test
    void testAffectContratToEtudiant() {
        // Arrange
        when(etudiantRepository.findByNomEAndPrenomE("John", "Doe")).thenReturn(etudiant);
        when(contratRepository.findByIdContrat(1)).thenReturn(contrat);

        // Act
        Contrat affectedContrat = contratService.affectContratToEtudiant(1, "John", "Doe");

        // Assert
        assertEquals(etudiant, affectedContrat.getEtudiant());
        verify(contratRepository).save(contrat);
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDates() {
        // Arrange
        Date startDate = new Date(System.currentTimeMillis() - 86400000L * 30); // 30 days ago
        Date endDate = new Date();
        List<Contrat> contrats = new ArrayList<>();
        contrats.add(contrat);
        when(contratRepository.findAll()).thenReturn(contrats);

        // Act
        float chiffreAffaire = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);

        // Assert
        assertEquals(300 * (30 / 30.0), chiffreAffaire); // Assuming 30 days for simplicity
    }

    // Additional tests for methods like nbContratsValides and retrieveAndUpdateStatusContrat can be added similarly
}
