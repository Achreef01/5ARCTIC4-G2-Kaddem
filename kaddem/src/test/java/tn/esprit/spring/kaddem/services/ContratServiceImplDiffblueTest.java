package tn.esprit.spring.kaddem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

@ContextConfiguration(classes = {ContratServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ContratServiceImplDiffblueTest {
    @MockBean
    private ContratRepository contratRepository;

    @Autowired
    private ContratServiceImpl contratServiceImpl;

    @MockBean
    private EtudiantRepository etudiantRepository;

    /**
     * Method under test: {@link ContratServiceImpl#retrieveAllContrats()}
     */
    @Test
    void testRetrieveAllContrats() {
        // Arrange
        ArrayList<Contrat> contratList = new ArrayList<>();
        when(contratRepository.findAll()).thenReturn(contratList);

        // Act
        List<Contrat> actualRetrieveAllContratsResult = contratServiceImpl.retrieveAllContrats();

        // Assert
        verify(contratRepository).findAll();
        assertTrue(actualRetrieveAllContratsResult.isEmpty());
        assertSame(contratList, actualRetrieveAllContratsResult);
    }

    /**
     * Method under test: {@link ContratServiceImpl#updateContrat(Contrat)}
     */
    @Test
    void testUpdateContrat() {
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
        when(contratRepository.save(Mockito.<Contrat>any())).thenReturn(contrat);

        Departement departement2 = new Departement();
        departement2.setEtudiants(new HashSet<>());
        departement2.setIdDepart(1);
        departement2.setNomDepart("Nom Depart");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setContrats(new HashSet<>());
        etudiant2.setDepartement(departement2);
        etudiant2.setEquipes(new ArrayList<>());
        etudiant2.setIdEtudiant(1);
        etudiant2.setNomE("Nom E");
        etudiant2.setOp(Option.GAMIX);
        etudiant2.setPrenomE("Prenom E");

        Contrat ce = new Contrat();
        ce.setArchive(true);
        ce.setDateDebutContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ce.setDateFinContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ce.setEtudiant(etudiant2);
        ce.setIdContrat(1);
        ce.setMontantContrat(2);
        ce.setSpecialite(Specialite.IA);

        // Act
        Contrat actualUpdateContratResult = contratServiceImpl.updateContrat(ce);

        // Assert
        verify(contratRepository).save(isA(Contrat.class));
        assertSame(contrat, actualUpdateContratResult);
    }

    /**
     * Method under test: {@link ContratServiceImpl#addContrat(Contrat)}
     */
    @Test
    void testAddContrat() {
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
        when(contratRepository.save(Mockito.<Contrat>any())).thenReturn(contrat);

        Departement departement2 = new Departement();
        departement2.setEtudiants(new HashSet<>());
        departement2.setIdDepart(1);
        departement2.setNomDepart("Nom Depart");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setContrats(new HashSet<>());
        etudiant2.setDepartement(departement2);
        etudiant2.setEquipes(new ArrayList<>());
        etudiant2.setIdEtudiant(1);
        etudiant2.setNomE("Nom E");
        etudiant2.setOp(Option.GAMIX);
        etudiant2.setPrenomE("Prenom E");

        Contrat ce = new Contrat();
        ce.setArchive(true);
        ce.setDateDebutContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ce.setDateFinContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ce.setEtudiant(etudiant2);
        ce.setIdContrat(1);
        ce.setMontantContrat(2);
        ce.setSpecialite(Specialite.IA);

        // Act
        Contrat actualAddContratResult = contratServiceImpl.addContrat(ce);

        // Assert
        verify(contratRepository).save(isA(Contrat.class));
        assertSame(contrat, actualAddContratResult);
    }

    /**
     * Method under test: {@link ContratServiceImpl#retrieveContrat(Integer)}
     */
    @Test
    void testRetrieveContrat() {
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

        // Act
        Contrat actualRetrieveContratResult = contratServiceImpl.retrieveContrat(1);

        // Assert
        verify(contratRepository).findById(eq(1));
        assertSame(contrat, actualRetrieveContratResult);
    }

    /**
     * Method under test: {@link ContratServiceImpl#removeContrat(Integer)}
     */
    @Test
    void testRemoveContrat() {
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
        doNothing().when(contratRepository).delete(Mockito.<Contrat>any());

        // Act
        contratServiceImpl.removeContrat(1);

        // Assert that nothing has changed
        verify(contratRepository).delete(isA(Contrat.class));
        verify(contratRepository).findById(eq(1));
    }

    /**
     * Method under test:
     * {@link ContratServiceImpl#affectContratToEtudiant(Integer, String, String)}
     */
    @Test
    void testAffectContratToEtudiant() {
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

        Departement departement2 = new Departement();
        departement2.setEtudiants(new HashSet<>());
        departement2.setIdDepart(1);
        departement2.setNomDepart("Nom Depart");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setContrats(new HashSet<>());
        etudiant2.setDepartement(departement2);
        etudiant2.setEquipes(new ArrayList<>());
        etudiant2.setIdEtudiant(1);
        etudiant2.setNomE("Nom E");
        etudiant2.setOp(Option.GAMIX);
        etudiant2.setPrenomE("Prenom E");

        Contrat contrat2 = new Contrat();
        contrat2.setArchive(true);
        contrat2.setDateDebutContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat2.setDateFinContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat2.setEtudiant(etudiant2);
        contrat2.setIdContrat(1);
        contrat2.setMontantContrat(2);
        contrat2.setSpecialite(Specialite.IA);
        when(contratRepository.save(Mockito.<Contrat>any())).thenReturn(contrat2);
        when(contratRepository.findByIdContrat(Mockito.<Integer>any())).thenReturn(contrat);

        Departement departement3 = new Departement();
        departement3.setEtudiants(new HashSet<>());
        departement3.setIdDepart(1);
        departement3.setNomDepart("Nom Depart");

        Etudiant etudiant3 = new Etudiant();
        etudiant3.setContrats(new HashSet<>());
        etudiant3.setDepartement(departement3);
        etudiant3.setEquipes(new ArrayList<>());
        etudiant3.setIdEtudiant(1);
        etudiant3.setNomE("Nom E");
        etudiant3.setOp(Option.GAMIX);
        etudiant3.setPrenomE("Prenom E");
        when(etudiantRepository.findByNomEAndPrenomE(Mockito.<String>any(), Mockito.<String>any())).thenReturn(etudiant3);

        // Act
        Contrat actualAffectContratToEtudiantResult = contratServiceImpl.affectContratToEtudiant(1, "Nom E", "Prenom E");

        // Assert
        verify(contratRepository).save(isA(Contrat.class));
        verify(contratRepository).findByIdContrat(eq(1));
        verify(etudiantRepository).findByNomEAndPrenomE(eq("Nom E"), eq("Prenom E"));
        assertSame(contrat, actualAffectContratToEtudiantResult);
    }

    /**
     * Method under test:
     * {@link ContratServiceImpl#affectContratToEtudiant(Integer, String, String)}
     */
    @Test
    void testAffectContratToEtudiant2() {
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

        Departement departement2 = new Departement();
        departement2.setEtudiants(new HashSet<>());
        departement2.setIdDepart(1);
        departement2.setNomDepart("Nom Depart");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setContrats(new HashSet<>());
        etudiant2.setDepartement(departement2);
        etudiant2.setEquipes(new ArrayList<>());
        etudiant2.setIdEtudiant(1);
        etudiant2.setNomE("Nom E");
        etudiant2.setOp(Option.GAMIX);
        etudiant2.setPrenomE("Prenom E");

        Contrat contrat2 = new Contrat();
        contrat2.setArchive(true);
        contrat2.setDateDebutContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat2.setDateFinContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat2.setEtudiant(etudiant2);
        contrat2.setIdContrat(1);
        contrat2.setMontantContrat(2);
        contrat2.setSpecialite(Specialite.IA);
        when(contratRepository.save(Mockito.<Contrat>any())).thenReturn(contrat2);
        when(contratRepository.findByIdContrat(Mockito.<Integer>any())).thenReturn(contrat);

        Departement departement3 = new Departement();
        departement3.setEtudiants(new HashSet<>());
        departement3.setIdDepart(1);
        departement3.setNomDepart("Nom Depart");

        Etudiant etudiant3 = new Etudiant();
        etudiant3.setContrats(new HashSet<>());
        etudiant3.setDepartement(departement3);
        etudiant3.setEquipes(new ArrayList<>());
        etudiant3.setIdEtudiant(1);
        etudiant3.setNomE("Nom E");
        etudiant3.setOp(Option.GAMIX);
        etudiant3.setPrenomE("Prenom E");

        Contrat contrat3 = new Contrat();
        contrat3.setArchive(true);
        contrat3.setDateDebutContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat3.setDateFinContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat3.setEtudiant(etudiant3);
        contrat3.setIdContrat(1);
        contrat3.setMontantContrat(2);
        contrat3.setSpecialite(Specialite.IA);

        HashSet<Contrat> contrats = new HashSet<>();
        contrats.add(contrat3);

        Departement departement4 = new Departement();
        departement4.setEtudiants(new HashSet<>());
        departement4.setIdDepart(1);
        departement4.setNomDepart("Nom Depart");

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setContrats(contrats);
        etudiant4.setDepartement(departement4);
        etudiant4.setEquipes(new ArrayList<>());
        etudiant4.setIdEtudiant(1);
        etudiant4.setNomE("Nom E");
        etudiant4.setOp(Option.GAMIX);
        etudiant4.setPrenomE("Prenom E");
        when(etudiantRepository.findByNomEAndPrenomE(Mockito.<String>any(), Mockito.<String>any())).thenReturn(etudiant4);

        // Act
        Contrat actualAffectContratToEtudiantResult = contratServiceImpl.affectContratToEtudiant(1, "Nom E", "Prenom E");

        // Assert
        verify(contratRepository).save(isA(Contrat.class));
        verify(contratRepository).findByIdContrat(eq(1));
        verify(etudiantRepository).findByNomEAndPrenomE(eq("Nom E"), eq("Prenom E"));
        assertSame(contrat, actualAffectContratToEtudiantResult);
    }

    /**
     * Method under test: {@link ContratServiceImpl#nbContratsValides(Date, Date)}
     */
    @Test
    void testNbContratsValides() {
        // Arrange
        when(contratRepository.getnbContratsValides(Mockito.<Date>any(), Mockito.<Date>any())).thenReturn(1);
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        Integer actualNbContratsValidesResult = contratServiceImpl.nbContratsValides(startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Assert
        verify(contratRepository).getnbContratsValides(isA(Date.class), isA(Date.class));
        assertEquals(1, actualNbContratsValidesResult.intValue());
    }

    /**
     * Method under test:
     * {@link ContratServiceImpl#retrieveAndUpdateStatusContrat()}
     */
    @Test
    void testRetrieveAndUpdateStatusContrat() {
        // Arrange
        when(contratRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        contratServiceImpl.retrieveAndUpdateStatusContrat();

        // Assert that nothing has changed
        verify(contratRepository).findAll();
    }

    /**
     * Method under test:
     * {@link ContratServiceImpl#retrieveAndUpdateStatusContrat()}
     */
    @Test
    void testRetrieveAndUpdateStatusContrat2() {
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

        ArrayList<Contrat> contratList = new ArrayList<>();
        contratList.add(contrat);
        when(contratRepository.findAll()).thenReturn(contratList);

        // Act
        contratServiceImpl.retrieveAndUpdateStatusContrat();

        // Assert that nothing has changed
        verify(contratRepository).findAll();
    }

    /**
     * Method under test:
     * {@link ContratServiceImpl#retrieveAndUpdateStatusContrat()}
     */
    @Test
    void testRetrieveAndUpdateStatusContrat3() {
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

        Departement departement2 = new Departement();
        departement2.setEtudiants(new HashSet<>());
        departement2.setIdDepart(2);
        departement2.setNomDepart("42");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setContrats(new HashSet<>());
        etudiant2.setDepartement(departement2);
        etudiant2.setEquipes(new ArrayList<>());
        etudiant2.setIdEtudiant(2);
        etudiant2.setNomE("42");
        etudiant2.setOp(Option.SE);
        etudiant2.setPrenomE("42");

        Contrat contrat2 = new Contrat();
        contrat2.setArchive(false);
        contrat2.setDateDebutContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat2.setDateFinContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat2.setEtudiant(etudiant2);
        contrat2.setIdContrat(2);
        contrat2.setMontantContrat(1);
        contrat2.setSpecialite(Specialite.RESEAUX);

        ArrayList<Contrat> contratList = new ArrayList<>();
        contratList.add(contrat2);
        contratList.add(contrat);
        when(contratRepository.findAll()).thenReturn(contratList);

        // Act
        contratServiceImpl.retrieveAndUpdateStatusContrat();

        // Assert
        verify(contratRepository).findAll();
    }

    /**
     * Method under test:
     * {@link ContratServiceImpl#getChiffreAffaireEntreDeuxDates(Date, Date)}
     */
    @Test
    void testGetChiffreAffaireEntreDeuxDates() {
        // Arrange
        when(contratRepository.findAll()).thenReturn(new ArrayList<>());
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        float actualChiffreAffaireEntreDeuxDates = contratServiceImpl.getChiffreAffaireEntreDeuxDates(startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Assert
        verify(contratRepository).findAll();
        assertEquals(0.0f, actualChiffreAffaireEntreDeuxDates);
    }

    /**
     * Method under test:
     * {@link ContratServiceImpl#getChiffreAffaireEntreDeuxDates(Date, Date)}
     */
    @Test
    void testGetChiffreAffaireEntreDeuxDates2() {
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

        ArrayList<Contrat> contratList = new ArrayList<>();
        contratList.add(contrat);
        when(contratRepository.findAll()).thenReturn(contratList);
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        float actualChiffreAffaireEntreDeuxDates = contratServiceImpl.getChiffreAffaireEntreDeuxDates(startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Assert
        verify(contratRepository).findAll();
        assertEquals(0.0f, actualChiffreAffaireEntreDeuxDates);
    }

    /**
     * Method under test:
     * {@link ContratServiceImpl#getChiffreAffaireEntreDeuxDates(Date, Date)}
     */
    @Test
    void testGetChiffreAffaireEntreDeuxDates3() {
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

        Departement departement2 = new Departement();
        departement2.setEtudiants(new HashSet<>());
        departement2.setIdDepart(2);
        departement2.setNomDepart("42");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setContrats(new HashSet<>());
        etudiant2.setDepartement(departement2);
        etudiant2.setEquipes(new ArrayList<>());
        etudiant2.setIdEtudiant(2);
        etudiant2.setNomE("42");
        etudiant2.setOp(Option.SE);
        etudiant2.setPrenomE("42");

        Contrat contrat2 = new Contrat();
        contrat2.setArchive(false);
        contrat2.setDateDebutContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat2.setDateFinContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat2.setEtudiant(etudiant2);
        contrat2.setIdContrat(2);
        contrat2.setMontantContrat(1);
        contrat2.setSpecialite(Specialite.RESEAUX);

        ArrayList<Contrat> contratList = new ArrayList<>();
        contratList.add(contrat2);
        contratList.add(contrat);
        when(contratRepository.findAll()).thenReturn(contratList);
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        float actualChiffreAffaireEntreDeuxDates = contratServiceImpl.getChiffreAffaireEntreDeuxDates(startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Assert
        verify(contratRepository).findAll();
        assertEquals(0.0f, actualChiffreAffaireEntreDeuxDates);
    }

    /**
     * Method under test:
     * {@link ContratServiceImpl#getChiffreAffaireEntreDeuxDates(Date, Date)}
     */
    @Test
    void testGetChiffreAffaireEntreDeuxDates4() {
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

        Departement departement2 = new Departement();
        departement2.setEtudiants(new HashSet<>());
        departement2.setIdDepart(2);
        departement2.setNomDepart("42");

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setContrats(new HashSet<>());
        etudiant2.setDepartement(departement2);
        etudiant2.setEquipes(new ArrayList<>());
        etudiant2.setIdEtudiant(2);
        etudiant2.setNomE("42");
        etudiant2.setOp(Option.SE);
        etudiant2.setPrenomE("42");

        Contrat contrat2 = new Contrat();
        contrat2.setArchive(false);
        contrat2.setDateDebutContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat2.setDateFinContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat2.setEtudiant(etudiant2);
        contrat2.setIdContrat(2);
        contrat2.setMontantContrat(1);
        contrat2.setSpecialite(Specialite.RESEAUX);

        Departement departement3 = new Departement();
        departement3.setEtudiants(new HashSet<>());
        departement3.setIdDepart(3);
        departement3.setNomDepart("");

        Etudiant etudiant3 = new Etudiant();
        etudiant3.setContrats(new HashSet<>());
        etudiant3.setDepartement(departement3);
        etudiant3.setEquipes(new ArrayList<>());
        etudiant3.setIdEtudiant(3);
        etudiant3.setNomE("");
        etudiant3.setOp(Option.SIM);
        etudiant3.setPrenomE("");

        Contrat contrat3 = new Contrat();
        contrat3.setArchive(true);
        contrat3.setDateDebutContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat3.setDateFinContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat3.setEtudiant(etudiant3);
        contrat3.setIdContrat(3);
        contrat3.setMontantContrat(0);
        contrat3.setSpecialite(Specialite.CLOUD);

        ArrayList<Contrat> contratList = new ArrayList<>();
        contratList.add(contrat3);
        contratList.add(contrat2);
        contratList.add(contrat);
        when(contratRepository.findAll()).thenReturn(contratList);
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        float actualChiffreAffaireEntreDeuxDates = contratServiceImpl.getChiffreAffaireEntreDeuxDates(startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Assert
        verify(contratRepository).findAll();
        assertEquals(0.0f, actualChiffreAffaireEntreDeuxDates);
    }

    /**
     * Method under test:
     * {@link ContratServiceImpl#getChiffreAffaireEntreDeuxDates(Date, Date)}
     */
    @Test
    void testGetChiffreAffaireEntreDeuxDates5() {
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
        Contrat contrat = mock(Contrat.class);
        when(contrat.getSpecialite()).thenReturn(Specialite.SECURITE);
        doNothing().when(contrat).setArchive(Mockito.<Boolean>any());
        doNothing().when(contrat).setDateDebutContrat(Mockito.<Date>any());
        doNothing().when(contrat).setDateFinContrat(Mockito.<Date>any());
        doNothing().when(contrat).setEtudiant(Mockito.<Etudiant>any());
        doNothing().when(contrat).setIdContrat(Mockito.<Integer>any());
        doNothing().when(contrat).setMontantContrat(Mockito.<Integer>any());
        doNothing().when(contrat).setSpecialite(Mockito.<Specialite>any());
        contrat.setArchive(true);
        contrat.setDateDebutContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat.setDateFinContrat(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        contrat.setEtudiant(etudiant);
        contrat.setIdContrat(1);
        contrat.setMontantContrat(2);
        contrat.setSpecialite(Specialite.IA);

        ArrayList<Contrat> contratList = new ArrayList<>();
        contratList.add(contrat);
        when(contratRepository.findAll()).thenReturn(contratList);
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        float actualChiffreAffaireEntreDeuxDates = contratServiceImpl.getChiffreAffaireEntreDeuxDates(startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Assert
        verify(contrat, atLeast(1)).getSpecialite();
        verify(contrat).setArchive(eq(true));
        verify(contrat).setDateDebutContrat(isA(Date.class));
        verify(contrat).setDateFinContrat(isA(Date.class));
        verify(contrat).setEtudiant(isA(Etudiant.class));
        verify(contrat).setIdContrat(eq(1));
        verify(contrat).setMontantContrat(eq(2));
        verify(contrat).setSpecialite(eq(Specialite.IA));
        verify(contratRepository).findAll();
        assertEquals(0.0f, actualChiffreAffaireEntreDeuxDates);
    }
}
