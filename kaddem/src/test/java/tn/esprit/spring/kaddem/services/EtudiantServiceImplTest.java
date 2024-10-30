package tn.esprit.spring.kaddem.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

@ContextConfiguration(classes = {EtudiantServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EtudiantServiceImplTest {
    @MockBean
    private ContratRepository contratRepository;

    @MockBean
    private DepartementRepository departementRepository;

    @MockBean
    private EquipeRepository equipeRepository;

    @MockBean
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EtudiantServiceImpl etudiantServiceImpl;

    /**
     * Method under test: {@link EtudiantServiceImpl#retrieveAllEtudiants()}
     */
    @Test
    void testRetrieveAllEtudiants() {
        // Arrange
        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        when(etudiantRepository.findAll()).thenReturn(etudiantList);

        // Act
        List<Etudiant> actualRetrieveAllEtudiantsResult = etudiantServiceImpl.retrieveAllEtudiants();

        // Assert
        verify(etudiantRepository).findAll();
        assertTrue(actualRetrieveAllEtudiantsResult.isEmpty());
        assertSame(etudiantList, actualRetrieveAllEtudiantsResult);
    }

    /**
     * Method under test: {@link EtudiantServiceImpl#addEtudiant(Etudiant)}
     */
    @Test
    void testAddEtudiant() {
        // Arrange
        Departement departement = new Departement();
        departement.setEtudiants(new HashSet<>());
        departement.setIdDepart(1);
        departement.setNomDepart("Nom Depart");

        Etudiant etudiant = new Etudiant();
        etudiant.setContrats(new HashSet<>());
        etudiant.setDepartement(departement);
        etudiant.setEquipes(new ArrayList<>());
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Nom E");
        etudiant.setOp(Option.GAMIX);
        etudiant.setPrenomE("Prenom E");
        when(etudiantRepository.save(Mockito.<Etudiant>any())).thenReturn(etudiant);

        Departement departement2 = new Departement();
        departement2.setEtudiants(new HashSet<>());
        departement2.setIdDepart(1);
        departement2.setNomDepart("Nom Depart");

        Etudiant e = new Etudiant();
        e.setContrats(new HashSet<>());
        e.setDepartement(departement2);
        e.setEquipes(new ArrayList<>());
        e.setIdEtudiant(1);
        e.setNomE("Nom E");
        e.setOp(Option.GAMIX);
        e.setPrenomE("Prenom E");

        // Act
        Etudiant actualAddEtudiantResult = etudiantServiceImpl.addEtudiant(e);

        // Assert
        verify(etudiantRepository).save(isA(Etudiant.class));
        assertSame(etudiant, actualAddEtudiantResult);
    }

    /**
     * Method under test: {@link EtudiantServiceImpl#updateEtudiant(Etudiant)}
     */
    @Test
    void testUpdateEtudiant() {
        // Arrange
        Departement departement = new Departement();
        departement.setEtudiants(new HashSet<>());
        departement.setIdDepart(1);
        departement.setNomDepart("Nom Depart");

        Etudiant etudiant = new Etudiant();
        etudiant.setContrats(new HashSet<>());
        etudiant.setDepartement(departement);
        etudiant.setEquipes(new ArrayList<>());
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Nom E");
        etudiant.setOp(Option.GAMIX);
        etudiant.setPrenomE("Prenom E");
        when(etudiantRepository.save(Mockito.<Etudiant>any())).thenReturn(etudiant);

        Departement departement2 = new Departement();
        departement2.setEtudiants(new HashSet<>());
        departement2.setIdDepart(1);
        departement2.setNomDepart("Nom Depart");

        Etudiant e = new Etudiant();
        e.setContrats(new HashSet<>());
        e.setDepartement(departement2);
        e.setEquipes(new ArrayList<>());
        e.setIdEtudiant(1);
        e.setNomE("Nom E");
        e.setOp(Option.GAMIX);
        e.setPrenomE("Prenom E");

        // Act
        Etudiant actualUpdateEtudiantResult = etudiantServiceImpl.updateEtudiant(e);

        // Assert
        verify(etudiantRepository).save(isA(Etudiant.class));
        assertSame(etudiant, actualUpdateEtudiantResult);
    }

    /**
     * Method under test: {@link EtudiantServiceImpl#retrieveEtudiant(Integer)}
     */
    @Test
    void testRetrieveEtudiant() {
        // Arrange
        Departement departement = new Departement();
        departement.setEtudiants(new HashSet<>());
        departement.setIdDepart(1);
        departement.setNomDepart("Nom Depart");

        Etudiant etudiant = new Etudiant();
        etudiant.setContrats(new HashSet<>());
        etudiant.setDepartement(departement);
        etudiant.setEquipes(new ArrayList<>());
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Nom E");
        etudiant.setOp(Option.GAMIX);
        etudiant.setPrenomE("Prenom E");
        Optional<Etudiant> ofResult = Optional.of(etudiant);
        when(etudiantRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Etudiant actualRetrieveEtudiantResult = etudiantServiceImpl.retrieveEtudiant(1);

        // Assert
        verify(etudiantRepository).findById(eq(1));
        assertSame(etudiant, actualRetrieveEtudiantResult);
    }

    /**
     * Method under test: {@link EtudiantServiceImpl#removeEtudiant(Integer)}
     */
    @Test
    void testRemoveEtudiant() {
        // Arrange
        Departement departement = new Departement();
        departement.setEtudiants(new HashSet<>());
        departement.setIdDepart(1);
        departement.setNomDepart("Nom Depart");

        Etudiant etudiant = new Etudiant();
        etudiant.setContrats(new HashSet<>());
        etudiant.setDepartement(departement);
        etudiant.setEquipes(new ArrayList<>());
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Nom E");
        etudiant.setOp(Option.GAMIX);
        etudiant.setPrenomE("Prenom E");
        Optional<Etudiant> ofResult = Optional.of(etudiant);
        doNothing().when(etudiantRepository).delete(Mockito.<Etudiant>any());
        when(etudiantRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        etudiantServiceImpl.removeEtudiant(1);

        // Assert that nothing has changed
        verify(etudiantRepository).delete(isA(Etudiant.class));
        verify(etudiantRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link EtudiantServiceImpl#assignEtudiantToDepartement(Integer, Integer)}
     */
    @Test
    void testAssignEtudiantToDepartement() {
        // Arrange
        Departement departement = new Departement();
        departement.setEtudiants(new HashSet<>());
        departement.setIdDepart(1);
        departement.setNomDepart("Nom Depart");
        Optional<Departement> ofResult = Optional.of(departement);
        when(departementRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Departement departement2 = new Departement();
        departement2.setEtudiants(new HashSet<>());
        departement2.setIdDepart(1);
        departement2.setNomDepart("Nom Depart");

        Etudiant etudiant = new Etudiant();
        etudiant.setContrats(new HashSet<>());
        etudiant.setDepartement(departement2);
        etudiant.setEquipes(new ArrayList<>());
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Nom E");
        etudiant.setOp(Option.GAMIX);
        etudiant.setPrenomE("Prenom E");
        Optional<Etudiant> ofResult2 = Optional.of(etudiant);

        Departement departement3 = new Departement();
        departement3.setEtudiants(new HashSet<>());
        departement3.setIdDepart(1);
        departement3.setNomDepart("Nom Depart");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setContrats(new HashSet<>());
        etudiant2.setDepartement(departement3);
        etudiant2.setEquipes(new ArrayList<>());
        etudiant2.setIdEtudiant(1);
        etudiant2.setNomE("Nom E");
        etudiant2.setOp(Option.GAMIX);
        etudiant2.setPrenomE("Prenom E");
        when(etudiantRepository.save(Mockito.<Etudiant>any())).thenReturn(etudiant2);
        when(etudiantRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);

        // Act
        etudiantServiceImpl.assignEtudiantToDepartement(1, 1);

        // Assert
        verify(departementRepository).findById(eq(1));
        verify(etudiantRepository).findById(eq(1));
        verify(etudiantRepository).save(isA(Etudiant.class));
    }

    /**
     * Method under test:
     * {@link EtudiantServiceImpl#addAndAssignEtudiantToEquipeAndContract(Etudiant, Integer, Integer)}
     */
    @Test
    void testAddAndAssignEtudiantToEquipeAndContract() {
        // Arrange
        Departement departement = new Departement();
        departement.setEtudiants(new HashSet<>());
        departement.setIdDepart(1);
        departement.setNomDepart("Nom Depart");

        Etudiant etudiant = new Etudiant();
        etudiant.setContrats(new HashSet<>());
        etudiant.setDepartement(departement);
        etudiant.setEquipes(new ArrayList<>());
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Nom E");
        etudiant.setOp(Option.GAMIX);
        etudiant.setPrenomE("Prenom E");

        Contrat contrat = new Contrat();
        contrat.setArchive(true);
        contrat.setDateDebutContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat.setDateFinContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat.setEtudiant(etudiant);
        contrat.setIdContrat(1);
        contrat.setMontantContrat(2);
        contrat.setSpecialite(Specialite.IA);
        Optional<Contrat> ofResult = Optional.of(contrat);
        when(contratRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

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
        Optional<Equipe> ofResult2 = Optional.of(equipe2);
        when(equipeRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);

        Departement departement2 = new Departement();
        departement2.setEtudiants(new HashSet<>());
        departement2.setIdDepart(1);
        departement2.setNomDepart("Nom Depart");

        Etudiant e = new Etudiant();
        e.setContrats(new HashSet<>());
        e.setDepartement(departement2);
        e.setEquipes(new ArrayList<>());
        e.setIdEtudiant(1);
        e.setNomE("Nom E");
        e.setOp(Option.GAMIX);
        e.setPrenomE("Prenom E");

        // Act
        Etudiant actualAddAndAssignEtudiantToEquipeAndContractResult = etudiantServiceImpl
                .addAndAssignEtudiantToEquipeAndContract(e, 1, 1);

        // Assert
        verify(contratRepository).findById(eq(1));
        verify(equipeRepository).findById(eq(1));
        assertSame(e, actualAddAndAssignEtudiantToEquipeAndContractResult);
    }

    /**
     * Method under test:
     * {@link EtudiantServiceImpl#getEtudiantsByDepartement(Integer)}
     */
    @Test
    void testGetEtudiantsByDepartement() {
        // Arrange
        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(Mockito.<Integer>any())).thenReturn(etudiantList);

        // Act
        List<Etudiant> actualEtudiantsByDepartement = etudiantServiceImpl.getEtudiantsByDepartement(1);

        // Assert
        verify(etudiantRepository).findEtudiantsByDepartement_IdDepart(eq(1));
        assertTrue(actualEtudiantsByDepartement.isEmpty());
        assertSame(etudiantList, actualEtudiantsByDepartement);
    }
}
