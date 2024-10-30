package tn.esprit.spring.kaddem.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

@ContextConfiguration(classes = {EquipeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EquipeServiceImplTest {
    @MockBean
    private EquipeRepository equipeRepository;

    @Autowired
    private EquipeServiceImpl equipeServiceImpl;

    /**
     * Method under test: {@link EquipeServiceImpl#retrieveAllEquipes()}
     */
    @Test
    void testRetrieveAllEquipes() {
        // Arrange
        ArrayList<Equipe> equipeList = new ArrayList<>();
        when(equipeRepository.findAll()).thenReturn(equipeList);

        // Act
        List<Equipe> actualRetrieveAllEquipesResult = equipeServiceImpl.retrieveAllEquipes();

        // Assert
        verify(equipeRepository).findAll();
        assertTrue(actualRetrieveAllEquipesResult.isEmpty());
        assertSame(equipeList, actualRetrieveAllEquipesResult);
    }

    /**
     * Method under test: {@link EquipeServiceImpl#addEquipe(Equipe)}
     */
    @Test
    void testAddEquipe() {
        // Arrange
        Equipe equipe = new Equipe();
        equipe.setDetailEquipe(new DetailEquipe());
        equipe.setEtudiants(new HashSet<>());
        equipe.setIdEquipe(1);
        equipe.setNiveau(Niveau.JUNIOR);
        equipe.setNomEquipe("Nom Equipe");

        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setEquipe(equipe);
        detailEquipe.setIdDetailEquipe(1);
        detailEquipe.setSalle(2);
        detailEquipe.setThematique("Thematique");

        Equipe equipe2 = new Equipe();
        equipe2.setDetailEquipe(detailEquipe);
        equipe2.setEtudiants(new HashSet<>());
        equipe2.setIdEquipe(1);
        equipe2.setNiveau(Niveau.JUNIOR);
        equipe2.setNomEquipe("Nom Equipe");

        DetailEquipe detailEquipe2 = new DetailEquipe();
        detailEquipe2.setEquipe(equipe2);
        detailEquipe2.setIdDetailEquipe(1);
        detailEquipe2.setSalle(2);
        detailEquipe2.setThematique("Thematique");

        Equipe equipe3 = new Equipe();
        equipe3.setDetailEquipe(detailEquipe2);
        equipe3.setEtudiants(new HashSet<>());
        equipe3.setIdEquipe(1);
        equipe3.setNiveau(Niveau.JUNIOR);
        equipe3.setNomEquipe("Nom Equipe");
        when(equipeRepository.save(Mockito.<Equipe>any())).thenReturn(equipe3);

        DetailEquipe detailEquipe3 = new DetailEquipe();
        detailEquipe3.setEquipe(new Equipe());
        detailEquipe3.setIdDetailEquipe(1);
        detailEquipe3.setSalle(2);
        detailEquipe3.setThematique("Thematique");

        Equipe equipe4 = new Equipe();
        equipe4.setDetailEquipe(detailEquipe3);
        equipe4.setEtudiants(new HashSet<>());
        equipe4.setIdEquipe(1);
        equipe4.setNiveau(Niveau.JUNIOR);
        equipe4.setNomEquipe("Nom Equipe");

        DetailEquipe detailEquipe4 = new DetailEquipe();
        detailEquipe4.setEquipe(equipe4);
        detailEquipe4.setIdDetailEquipe(1);
        detailEquipe4.setSalle(2);
        detailEquipe4.setThematique("Thematique");

        Equipe e = new Equipe();
        e.setDetailEquipe(detailEquipe4);
        e.setEtudiants(new HashSet<>());
        e.setIdEquipe(1);
        e.setNiveau(Niveau.JUNIOR);
        e.setNomEquipe("Nom Equipe");

        // Act
        Equipe actualAddEquipeResult = equipeServiceImpl.addEquipe(e);

        // Assert
        verify(equipeRepository).save(isA(Equipe.class));
        assertSame(equipe3, actualAddEquipeResult);
    }

    /**
     * Method under test: {@link EquipeServiceImpl#deleteEquipe(Integer)}
     */
    @Test
    void testDeleteEquipe() {
        // Arrange
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setEquipe(new Equipe());
        detailEquipe.setIdDetailEquipe(1);
        detailEquipe.setSalle(2);
        detailEquipe.setThematique("Thematique");

        Equipe equipe = new Equipe();
        equipe.setDetailEquipe(detailEquipe);
        equipe.setEtudiants(new HashSet<>());
        equipe.setIdEquipe(1);
        equipe.setNiveau(Niveau.JUNIOR);
        equipe.setNomEquipe("Nom Equipe");

        DetailEquipe detailEquipe2 = new DetailEquipe();
        detailEquipe2.setEquipe(equipe);
        detailEquipe2.setIdDetailEquipe(1);
        detailEquipe2.setSalle(2);
        detailEquipe2.setThematique("Thematique");

        Equipe equipe2 = new Equipe();
        equipe2.setDetailEquipe(detailEquipe2);
        equipe2.setEtudiants(new HashSet<>());
        equipe2.setIdEquipe(1);
        equipe2.setNiveau(Niveau.JUNIOR);
        equipe2.setNomEquipe("Nom Equipe");
        Optional<Equipe> ofResult = Optional.of(equipe2);
        doNothing().when(equipeRepository).delete(Mockito.<Equipe>any());
        when(equipeRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        equipeServiceImpl.deleteEquipe(1);

        // Assert that nothing has changed
        verify(equipeRepository).delete(isA(Equipe.class));
        verify(equipeRepository).findById(eq(1));
    }

    /**
     * Method under test: {@link EquipeServiceImpl#retrieveEquipe(Integer)}
     */
    @Test
    void testRetrieveEquipe() {
        // Arrange
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setEquipe(new Equipe());
        detailEquipe.setIdDetailEquipe(1);
        detailEquipe.setSalle(2);
        detailEquipe.setThematique("Thematique");

        Equipe equipe = new Equipe();
        equipe.setDetailEquipe(detailEquipe);
        equipe.setEtudiants(new HashSet<>());
        equipe.setIdEquipe(1);
        equipe.setNiveau(Niveau.JUNIOR);
        equipe.setNomEquipe("Nom Equipe");

        DetailEquipe detailEquipe2 = new DetailEquipe();
        detailEquipe2.setEquipe(equipe);
        detailEquipe2.setIdDetailEquipe(1);
        detailEquipe2.setSalle(2);
        detailEquipe2.setThematique("Thematique");

        Equipe equipe2 = new Equipe();
        equipe2.setDetailEquipe(detailEquipe2);
        equipe2.setEtudiants(new HashSet<>());
        equipe2.setIdEquipe(1);
        equipe2.setNiveau(Niveau.JUNIOR);
        equipe2.setNomEquipe("Nom Equipe");
        Optional<Equipe> ofResult = Optional.of(equipe2);
        when(equipeRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Equipe actualRetrieveEquipeResult = equipeServiceImpl.retrieveEquipe(1);

        // Assert
        verify(equipeRepository).findById(eq(1));
        assertSame(equipe2, actualRetrieveEquipeResult);
    }

    /**
     * Method under test: {@link EquipeServiceImpl#updateEquipe(Equipe)}
     */
    @Test
    void testUpdateEquipe() {
        // Arrange
        Equipe equipe = new Equipe();
        equipe.setDetailEquipe(new DetailEquipe());
        equipe.setEtudiants(new HashSet<>());
        equipe.setIdEquipe(1);
        equipe.setNiveau(Niveau.JUNIOR);
        equipe.setNomEquipe("Nom Equipe");

        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setEquipe(equipe);
        detailEquipe.setIdDetailEquipe(1);
        detailEquipe.setSalle(2);
        detailEquipe.setThematique("Thematique");

        Equipe equipe2 = new Equipe();
        equipe2.setDetailEquipe(detailEquipe);
        equipe2.setEtudiants(new HashSet<>());
        equipe2.setIdEquipe(1);
        equipe2.setNiveau(Niveau.JUNIOR);
        equipe2.setNomEquipe("Nom Equipe");

        DetailEquipe detailEquipe2 = new DetailEquipe();
        detailEquipe2.setEquipe(equipe2);
        detailEquipe2.setIdDetailEquipe(1);
        detailEquipe2.setSalle(2);
        detailEquipe2.setThematique("Thematique");

        Equipe equipe3 = new Equipe();
        equipe3.setDetailEquipe(detailEquipe2);
        equipe3.setEtudiants(new HashSet<>());
        equipe3.setIdEquipe(1);
        equipe3.setNiveau(Niveau.JUNIOR);
        equipe3.setNomEquipe("Nom Equipe");
        when(equipeRepository.save(Mockito.<Equipe>any())).thenReturn(equipe3);

        DetailEquipe detailEquipe3 = new DetailEquipe();
        detailEquipe3.setEquipe(new Equipe());
        detailEquipe3.setIdDetailEquipe(1);
        detailEquipe3.setSalle(2);
        detailEquipe3.setThematique("Thematique");

        Equipe equipe4 = new Equipe();
        equipe4.setDetailEquipe(detailEquipe3);
        equipe4.setEtudiants(new HashSet<>());
        equipe4.setIdEquipe(1);
        equipe4.setNiveau(Niveau.JUNIOR);
        equipe4.setNomEquipe("Nom Equipe");

        DetailEquipe detailEquipe4 = new DetailEquipe();
        detailEquipe4.setEquipe(equipe4);
        detailEquipe4.setIdDetailEquipe(1);
        detailEquipe4.setSalle(2);
        detailEquipe4.setThematique("Thematique");

        Equipe e = new Equipe();
        e.setDetailEquipe(detailEquipe4);
        e.setEtudiants(new HashSet<>());
        e.setIdEquipe(1);
        e.setNiveau(Niveau.JUNIOR);
        e.setNomEquipe("Nom Equipe");

        // Act
        Equipe actualUpdateEquipeResult = equipeServiceImpl.updateEquipe(e);

        // Assert
        verify(equipeRepository).save(isA(Equipe.class));
        assertSame(equipe3, actualUpdateEquipeResult);
    }

    /**
     * Method under test: {@link EquipeServiceImpl#evoluerEquipes()}
     */
    @Test
    void testEvoluerEquipes() {
        // Arrange
        when(equipeRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        equipeServiceImpl.evoluerEquipes();

        // Assert that nothing has changed
        verify(equipeRepository).findAll();
    }

    /**
     * Method under test: {@link EquipeServiceImpl#evoluerEquipes()}
     */
    @Test
    void testEvoluerEquipes2() {
        // Arrange
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setEquipe(new Equipe());
        detailEquipe.setIdDetailEquipe(1);
        detailEquipe.setSalle(2);
        detailEquipe.setThematique("Thematique");

        Equipe equipe = new Equipe();
        equipe.setDetailEquipe(detailEquipe);
        equipe.setEtudiants(new HashSet<>());
        equipe.setIdEquipe(1);
        equipe.setNiveau(Niveau.JUNIOR);
        equipe.setNomEquipe("Nom Equipe");

        DetailEquipe detailEquipe2 = new DetailEquipe();
        detailEquipe2.setEquipe(equipe);
        detailEquipe2.setIdDetailEquipe(1);
        detailEquipe2.setSalle(2);
        detailEquipe2.setThematique("Thematique");
        Equipe equipe2 = mock(Equipe.class);
        when(equipe2.getNiveau()).thenReturn(Niveau.EXPERT);
        doNothing().when(equipe2).setDetailEquipe(Mockito.<DetailEquipe>any());
        doNothing().when(equipe2).setEtudiants(Mockito.<Set<Etudiant>>any());
        doNothing().when(equipe2).setIdEquipe(Mockito.<Integer>any());
        doNothing().when(equipe2).setNiveau(Mockito.<Niveau>any());
        doNothing().when(equipe2).setNomEquipe(Mockito.<String>any());
        equipe2.setDetailEquipe(detailEquipe2);
        equipe2.setEtudiants(new HashSet<>());
        equipe2.setIdEquipe(1);
        equipe2.setNiveau(Niveau.JUNIOR);
        equipe2.setNomEquipe("Nom Equipe");

        ArrayList<Equipe> equipeList = new ArrayList<>();
        equipeList.add(equipe2);
        when(equipeRepository.findAll()).thenReturn(equipeList);

        // Act
        equipeServiceImpl.evoluerEquipes();

        // Assert
        verify(equipeRepository).findAll();
        verify(equipe2, atLeast(1)).getNiveau();
        verify(equipe2).setDetailEquipe(isA(DetailEquipe.class));
        verify(equipe2).setEtudiants(isA(Set.class));
        verify(equipe2).setIdEquipe(eq(1));
        verify(equipe2).setNiveau(eq(Niveau.JUNIOR));
        verify(equipe2).setNomEquipe(eq("Nom Equipe"));
    }
}
