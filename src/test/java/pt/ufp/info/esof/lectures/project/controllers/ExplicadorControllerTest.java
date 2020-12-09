package pt.ufp.info.esof.lectures.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.lectures.project.dtos.DisponibilidadeCreateDTO;
import pt.ufp.info.esof.lectures.project.models.Explicador;
import pt.ufp.info.esof.lectures.project.services.ExplicadorService;

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
    private ExplicadorService explicadorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllExplicador() throws Exception {
        Explicador explicador1=new Explicador();
        Explicador explicador2=new Explicador();
        Explicador explicador3=new Explicador();

        List<Explicador> explicadores= Arrays.asList(explicador1,explicador2,explicador3);

        when(explicadorService.findAll()).thenReturn(explicadores);

        String httpResponseAsString=mockMvc.perform(get("/explicador")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);


    }

    @Test
    void getExplicadorById() throws Exception {
        Explicador explicador=new Explicador();
        String explicadorAsJsonString=new ObjectMapper().writeValueAsString(explicador);

        when(explicadorService.findById(1L)).thenReturn(Optional.of(explicador));

        String httpResponseAsString=mockMvc.perform(get("/explicador/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/explicador/2")).andExpect(status().isNotFound());
    }

    @Test
    void createExplicador() throws Exception {
        Explicador explicador=new Explicador();
        explicador.setEmail("novoexplicador@mail.com");

        when(this.explicadorService.createExplicador(explicador)).thenReturn(Optional.of(explicador));

        String explicadorAsJsonString=new ObjectMapper().writeValueAsString(explicador);

        mockMvc.perform(post("/explicador").content(explicadorAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Explicador explicadorExistente=new Explicador();
        explicadorExistente.setEmail("explicador@mail.com");
        String explicadorExistenteAsJsonString=new ObjectMapper().writeValueAsString(explicadorExistente);

        mockMvc.perform(post("/explicador").content(explicadorExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

    }

    @Test
    public void adicionaDisponibilidade() throws Exception {

        Explicador explicador=new Explicador();
        explicador.setEmail("novoexplicador@mail.com");

        DisponibilidadeCreateDTO disponibilidade=new DisponibilidadeCreateDTO();

        disponibilidade.setDia(LocalDate.now().getDayOfWeek());
        disponibilidade.setHoraInicio(LocalTime.of(8,0));
        disponibilidade.setHoraFim(disponibilidade.getHoraInicio().plusHours(3));

        String disponibilidadeJson=objectMapper.writeValueAsString(disponibilidade);

        when(explicadorService.adicionaDisponibilidade(1L,disponibilidade.converter())).thenReturn(Optional.of(explicador));

        mockMvc.perform(
                patch("/explicador/1")
                        .content(disponibilidadeJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mockMvc.perform(
                patch("/explicador/2")
                        .content(disponibilidadeJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

    }
}