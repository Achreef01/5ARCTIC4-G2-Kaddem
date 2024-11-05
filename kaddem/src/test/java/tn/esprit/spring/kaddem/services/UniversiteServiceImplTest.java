package tn.esprit.spring.kaddem.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
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
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

@ContextConfiguration(classes = {UniversiteServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UniversiteServiceImplTest {
    @MockBean
    private DepartementRepository departementRepository;

    @MockBean
    private UniversiteRepository universiteRepository;

    @Autowired
    private UniversiteServiceImpl universiteServiceImpl;


    @Test
    void testRetrieveAllUniversites() {
        // Arrange
        ArrayList<Universite> universiteList = new ArrayList<>();
        when(universiteRepository.findAll()).thenReturn(universiteList);

        // Act
        List<Universite> actualRetrieveAllUniversitesResult = universiteServiceImpl.retrieveAllUniversites();

        // Assert
        verify(universiteRepository).findAll();
        assertTrue(actualRetrieveAllUniversitesResult.isEmpty());
        assertSame(universiteList, actualRetrieveAllUniversitesResult);
    }


    @Test
    void testAddUniversite() {
        // Arrange
        Universite universite = new Universite();
        universite.setDepartements(new HashSet<>());
        universite.setIdUniv(1);
        universite.setNomUniv("Nom Univ");
        when(universiteRepository.save(Mockito.<Universite>any())).thenReturn(universite);

        Universite u = new Universite();
        u.setDepartements(new HashSet<>());
        u.setIdUniv(1);
        u.setNomUniv("Nom Univ");

        // Act
        Universite actualAddUniversiteResult = universiteServiceImpl.addUniversite(u);

        // Assert
        verify(universiteRepository).save(isA(Universite.class));
        assertSame(universite, actualAddUniversiteResult);
    }


    @Test
    void testUpdateUniversite() {
        // Arrange
        Universite universite = new Universite();
        universite.setDepartements(new HashSet<>());
        universite.setIdUniv(1);
        universite.setNomUniv("Nom Univ");
        when(universiteRepository.save(Mockito.<Universite>any())).thenReturn(universite);

        Universite u = new Universite();
        u.setDepartements(new HashSet<>());
        u.setIdUniv(1);
        u.setNomUniv("Nom Univ");

        // Act
        Universite actualUpdateUniversiteResult = universiteServiceImpl.updateUniversite(u);

        // Assert
        verify(universiteRepository).save(isA(Universite.class));
        assertSame(universite, actualUpdateUniversiteResult);
    }


    @Test
    void testRetrieveUniversite() {
        // Arrange
        Universite universite = new Universite();
        universite.setDepartements(new HashSet<>());
        universite.setIdUniv(1);
        universite.setNomUniv("Nom Univ");
        Optional<Universite> ofResult = Optional.of(universite);
        when(universiteRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Universite actualRetrieveUniversiteResult = universiteServiceImpl.retrieveUniversite(1);

        // Assert
        verify(universiteRepository).findById(eq(1));
        assertSame(universite, actualRetrieveUniversiteResult);
    }


    @Test
    void testDeleteUniversite() {
        // Arrange
        Universite universite = new Universite();
        universite.setDepartements(new HashSet<>());
        universite.setIdUniv(1);
        universite.setNomUniv("Nom Univ");
        Optional<Universite> ofResult = Optional.of(universite);
        doNothing().when(universiteRepository).delete(Mockito.<Universite>any());
        when(universiteRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        universiteServiceImpl.deleteUniversite(1);

        // Assert that nothing has changed
        verify(universiteRepository).delete(isA(Universite.class));
        verify(universiteRepository).findById(eq(1));
    }


    @Test
    void testAssignUniversiteToDepartement() {
        // Arrange
        Departement departement = new Departement();
        departement.setEtudiants(new HashSet<>());
        departement.setIdDepart(1);
        departement.setNomDepart("Nom Depart");
        Optional<Departement> ofResult = Optional.of(departement);
        when(departementRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Universite universite = new Universite();
        universite.setDepartements(new HashSet<>());
        universite.setIdUniv(1);
        universite.setNomUniv("Nom Univ");
        Optional<Universite> ofResult2 = Optional.of(universite);

        Universite universite2 = new Universite();
        universite2.setDepartements(new HashSet<>());
        universite2.setIdUniv(1);
        universite2.setNomUniv("Nom Univ");
        when(universiteRepository.save(Mockito.<Universite>any())).thenReturn(universite2);
        when(universiteRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);

        // Act
        universiteServiceImpl.assignUniversiteToDepartement(1, 1);

        // Assert
        verify(departementRepository).findById(eq(1));
        verify(universiteRepository).findById(eq(1));
        verify(universiteRepository).save(isA(Universite.class));
    }


    @Test
    void testRetrieveDepartementsByUniversite() {
        // Arrange
        Universite universite = new Universite();
        HashSet<Departement> departements = new HashSet<>();
        universite.setDepartements(departements);
        universite.setIdUniv(1);
        universite.setNomUniv("Nom Univ");
        Optional<Universite> ofResult = Optional.of(universite);
        when(universiteRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        Set<Departement> actualRetrieveDepartementsByUniversiteResult = universiteServiceImpl
                .retrieveDepartementsByUniversite(1);

        // Assert
        verify(universiteRepository).findById(eq(1));
        assertTrue(actualRetrieveDepartementsByUniversiteResult.isEmpty());
        assertSame(departements, actualRetrieveDepartementsByUniversiteResult);
    }
}