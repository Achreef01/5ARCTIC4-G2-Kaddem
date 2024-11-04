package tn.esprit.spring.kaddem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEquipes() {
        // Préparation des données de test
        List<Equipe> mockEquipes = new ArrayList<>();
        mockEquipes.add(new Equipe(1, "Equipe A", Niveau.JUNIOR));
        mockEquipes.add(new Equipe(2, "Equipe B", Niveau.SENIOR));

        // Simulation de la réponse du repository
        when(equipeRepository.findAll()).thenReturn(mockEquipes);

        // Appel de la méthode à tester
        List<Equipe> equipes = equipeService.retrieveAllEquipes();

        // Vérifications
        assertNotNull(equipes);
        assertEquals(2, equipes.size());
        verify(equipeRepository).findAll();
    }

    @Test
    void testAddEquipe() {
        // Préparation des données de test
        Equipe newEquipe = new Equipe(null, "Equipe C", Niveau.JUNIOR);
        Equipe savedEquipe = new Equipe(1, "Equipe C", Niveau.JUNIOR);

        // Simulation de la sauvegarde dans le repository
        when(equipeRepository.save(newEquipe)).thenReturn(savedEquipe);

        // Appel de la méthode à tester
        Equipe result = equipeService.addEquipe(newEquipe);

        // Vérifications
        assertNotNull(result);
        assertEquals("Equipe C", result.getNomEquipe());
        verify(equipeRepository).save(newEquipe);
    }

    @Test
    void testRetrieveEquipe() {
        // Préparation des données de test
        Equipe mockEquipe = new Equipe(1, "Equipe D", Niveau.EXPERT);

        // Simulation de la recherche par ID
        when(equipeRepository.findById(1)).thenReturn(Optional.of(mockEquipe));

        // Appel de la méthode à tester
        Equipe equipe = equipeService.retrieveEquipe(1);

        // Vérifications
        assertNotNull(equipe);
        assertEquals("Equipe D", equipe.getNomEquipe());
        verify(equipeRepository).findById(1);
    }

    @Test
    void testDeleteEquipe() {
        // Simulation de la recherche par ID
        Equipe equipeToDelete = new Equipe(1, "Equipe E", Niveau.JUNIOR);
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipeToDelete));

        // Appel de la méthode de suppression
        equipeService.deleteEquipe(1);

        // Vérification de la suppression
        verify(equipeRepository).delete(equipeToDelete);
    }

    @Test
    void testUpdateEquipe() {
        // Préparation des données de test
        Equipe existingEquipe = new Equipe(1, "Equipe F", Niveau.JUNIOR);
        Equipe updatedEquipe = new Equipe(1, "Equipe F Updated", Niveau.SENIOR);

        // Simulation de la mise à jour dans le repository
        when(equipeRepository.save(existingEquipe)).thenReturn(updatedEquipe);

        // Appel de la méthode de mise à jour
        Equipe result = equipeService.updateEquipe(existingEquipe);

        // Vérifications
        assertNotNull(result);
        assertEquals("Equipe F Updated", result.getNomEquipe());
        verify(equipeRepository).save(existingEquipe);
    }
}
