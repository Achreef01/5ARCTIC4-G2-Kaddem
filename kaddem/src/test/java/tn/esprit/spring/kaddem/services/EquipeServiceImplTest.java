package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    private Equipe equipe;

    @BeforeEach
    void setUp() {
        equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNiveau(Niveau.JUNIOR);
        equipe.setEtudiants(new HashSet<>());
    }

    @Test
    void testRetrieveAllEquipes() {
        // Arrange
        List<Equipe> equipes = new ArrayList<>();
        equipes.add(equipe);
        Mockito.when(equipeRepository.findAll()).thenReturn(equipes);

        // Act
        List<Equipe> actualEquipes = equipeService.retrieveAllEquipes();

        // Assert
        assertEquals(1, actualEquipes.size());
        assertEquals(equipe, actualEquipes.get(0));
    }

    @Test
    void testAddEquipe() {
        // Arrange
        Mockito.when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(equipe);

        // Act
        Equipe actualEquipe = equipeService.addEquipe(equipe);

        // Assert
        assertEquals(equipe, actualEquipe);
        Mockito.verify(equipeRepository).save(equipe);
    }

    @Test
    void testDeleteEquipe() {
        // Arrange
        Mockito.when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        // Act
        equipeService.deleteEquipe(1);

        // Assert
        Mockito.verify(equipeRepository).delete(equipe);
    }

    @Test
    void testRetrieveEquipe() {
        // Arrange
        Mockito.when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        // Act
        Equipe actualEquipe = equipeService.retrieveEquipe(1);

        // Assert
        assertEquals(equipe, actualEquipe);
    }

    @Test
    void testUpdateEquipe() {
        // Arrange
        equipe.setNiveau(Niveau.SENIOR);
        Mockito.when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(equipe);

        // Act
        Equipe updatedEquipe = equipeService.updateEquipe(equipe);

        // Assert
        assertEquals(equipe, updatedEquipe);
        Mockito.verify(equipeRepository).save(equipe);
    }


}
