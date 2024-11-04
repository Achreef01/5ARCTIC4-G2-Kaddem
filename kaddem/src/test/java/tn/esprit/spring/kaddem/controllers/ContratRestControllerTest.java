package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.services.IContratService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class ContratRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IContratService contratService;

    @InjectMocks
    private ContratRestController contratRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contratRestController).build();
    }

    @Test
    void testGetContrats() throws Exception {
        // Arrange
        List<Contrat> contrats = new ArrayList<>(Arrays.asList(new Contrat(), new Contrat()));
        when(contratService.retrieveAllContrats()).thenReturn(contrats);

        // Act & Assert
        mockMvc.perform(get("/contrat/retrieve-all-contrats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andDo(print());

        verify(contratService, times(1)).retrieveAllContrats();
    }

    @Test
    void testRetrieveContrat() throws Exception {
        // Arrange
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        when(contratService.retrieveContrat(1)).thenReturn(contrat);

        // Act & Assert
        mockMvc.perform(get("/contrat/retrieve-contrat/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idContrat").value(1))
                .andDo(print());

        verify(contratService, times(1)).retrieveContrat(1);
    }

    @Test
    void testAddContrat() throws Exception {
        // Arrange
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        when(contratService.addContrat(any(Contrat.class))).thenReturn(contrat);

        // Act & Assert
        mockMvc.perform(post("/contrat/add-contrat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idContrat\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idContrat").value(1))
                .andDo(print());

        verify(contratService, times(1)).addContrat(any(Contrat.class));
    }

    @Test
    void testRemoveContrat() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/contrat/remove-contrat/1"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(contratService, times(1)).removeContrat(1);
    }

    @Test
    void testUpdateContrat() throws Exception {
        // Arrange
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        when(contratService.updateContrat(any(Contrat.class))).thenReturn(contrat);

        // Act & Assert
        mockMvc.perform(put("/contrat/update-contrat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idContrat\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idContrat").value(1))
                .andDo(print());

        verify(contratService, times(1)).updateContrat(any(Contrat.class));
    }

    @Test
    void testAssignContratToEtudiant() throws Exception {
        // Arrange
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        when(contratService.affectContratToEtudiant(1, "John", "Doe")).thenReturn(contrat);

        // Act & Assert
        mockMvc.perform(put("/contrat/assignContratToEtudiant/1/John/Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idContrat").value(1))
                .andDo(print());

        verify(contratService, times(1)).affectContratToEtudiant(1, "John", "Doe");
    }

    @Test
    void testGetNbContratsValides() throws Exception {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        when(contratService.nbContratsValides(any(Date.class), any(Date.class))).thenReturn(5);

        // Act & Assert
        mockMvc.perform(get("/contrat/getnbContratsValides/{startDate}/{endDate}", "2023-01-01", "2023-12-31"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"))
                .andDo(print());

        verify(contratService, times(1)).nbContratsValides(any(Date.class), any(Date.class));
    }

    @Test
    void testCalculChiffreAffaireEntreDeuxDates() throws Exception {
        // Arrange
        Date specificStartDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01");
        Date specificEndDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-31");

        // Mock the method call
        when(contratService.nbContratsValides(specificStartDate, specificEndDate)).thenReturn(5);
        when(contratService.getChiffreAffaireEntreDeuxDates(specificStartDate, specificEndDate)).thenReturn(any());  // Adjust as needed

        // Act & Assert
        mockMvc.perform(get("/contrat/calculChiffreAffaireEntreDeuxDate/{startDate}/{endDate}", "2023-01-01", "2023-12-31"))
                .andExpect(status().isOk())
                .andExpect(content().string("1000.0"))
                .andDo(print());

        // Verify the calls
        verify(contratService, times(1)).nbContratsValides(specificStartDate, specificEndDate);
        verify(contratService, times(1)).getChiffreAffaireEntreDeuxDates(specificStartDate, specificEndDate);
    }


}
