package tn.esprit.spring.kaddem.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtudiantServiceImplTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DepartementRepository departementRepository;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("John Doe");
    }

    @Test
    void retrieveAllEtudiants() {
        // Arrange
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(etudiant);
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        // Act
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Assert
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getNomE());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void addEtudiant() {
        // Arrange
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getNomE());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void updateEtudiant() {
        // Arrange
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.updateEtudiant(etudiant);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getNomE());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void retrieveEtudiant() {
        // Arrange
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        // Act
        Etudiant result = etudiantService.retrieveEtudiant(1);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getNomE());
        verify(etudiantRepository, times(1)).findById(1);
    }

    @Test
    void removeEtudiant() {
        // Arrange
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        // Act
        etudiantService.removeEtudiant(1);

        // Assert
        verify(etudiantRepository, times(1)).delete(etudiant);
    }

    @Test
    void assignEtudiantToDepartement() {
        // Arrange
        Departement departement = new Departement();
        departement.setIdDepart(1);
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        // Act
        etudiantService.assignEtudiantToDepartement(1, 1);

        // Assert
        assertEquals(departement, etudiant.getDepartement());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void addAndAssignEtudiantToEquipeAndContract() {
        // Arrange
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, 1, 1);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getNomE());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void addAndAssignEtudiantToEquipeAndContract_ContratNotFound() {
        // Arrange
        when(contratRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, 1, 1);
        });
    }

    @Test
    void addAndAssignEtudiantToEquipeAndContract_EquipeNotFound() {
        // Arrange
        when(contratRepository.findById(1)).thenReturn(Optional.of(new Contrat()));
        when(equipeRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, 1, 1);
        });
    }

    @Test
    void getEtudiantsByDepartement() {
        // Arrange
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(etudiant);
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(1)).thenReturn(etudiants);

        // Act
        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(1);

        // Assert
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getNomE());
        verify(etudiantRepository, times(1)).findEtudiantsByDepartement_IdDepart(1);
    }
}
