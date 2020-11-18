package pt.ufp.info.esof.lectures.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pt.ufp.info.esof.lectures.models.Disponibilidade;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExplicadorController.class)
class ExplicadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicadorRepository explicadorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllExplicador() throws Exception {
        Explicador explicador1=new Explicador();
        Explicador explicador2=new Explicador();
        Explicador explicador3=new Explicador();

        List<Explicador> explicadores= Arrays.asList(explicador1,explicador2,explicador3);

        String listExplicadoresAsJsonString=new ObjectMapper().writeValueAsString(explicadores);

        when(explicadorRepository.findAll()).thenReturn(explicadores);

        String httpResponseAsString=mockMvc.perform(get("/explicador")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        assertEquals(listExplicadoresAsJsonString,httpResponseAsString);

    }

    @Test
    void getExplicadorById() throws Exception {
        Explicador explicador=new Explicador();
        String explicadorAsJsonString=new ObjectMapper().writeValueAsString(explicador);

        when(explicadorRepository.findById(1L)).thenReturn(Optional.of(explicador));

        String httpResponseAsString=mockMvc.perform(get("/explicador/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
        assertEquals(explicadorAsJsonString,httpResponseAsString);

        mockMvc.perform(get("/explicador/2")).andExpect(status().isNotFound());
    }

    @Test
    void createExplicador() throws Exception {
        Explicador explicador=new Explicador();
        explicador.setEmail("novoexplicador@mail.com");

        when(this.explicadorRepository.save(explicador)).thenReturn(explicador);

        String explicadorAsJsonString=new ObjectMapper().writeValueAsString(explicador);

        mockMvc.perform(post("/explicador").content(explicadorAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Explicador explicadorExistente=new Explicador();
        explicadorExistente.setEmail("explicador@mail.com");
        String explicadorExistenteAsJsonString=new ObjectMapper().writeValueAsString(explicadorExistente);
        when(this.explicadorRepository.findByEmail("explicador@mail.com")).thenReturn(Optional.of(explicadorExistente));

        mockMvc.perform(post("/explicador").content(explicadorExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

    }

    @Test
    public void adicionaDisponibilidade() throws Exception {

        Explicador explicador=new Explicador();
        explicador.setEmail("novoexplicador@mail.com");

        Disponibilidade disponibilidade=new Disponibilidade();

        disponibilidade.setDiaDaSemana(LocalDate.now().getDayOfWeek());
        disponibilidade.setHoraInicio(LocalTime.of(8,0));
        disponibilidade.setHoraFim(disponibilidade.getHoraInicio().plusHours(3));

        String disponibilidadeJson=objectMapper.writeValueAsString(disponibilidade);

        when(explicadorRepository.findById(1L)).thenReturn(Optional.of(explicador));

        mockMvc.perform(
                patch("/explicador/1")
                        .content(disponibilidadeJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mockMvc.perform(
                patch("/explicador/1")
                        .content(disponibilidadeJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

        mockMvc.perform(
                patch("/explicador/2")
                        .content(disponibilidadeJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

    }
}