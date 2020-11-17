package pt.ufp.info.esof.lectures.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.util.ArrayList;
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

    @Test
    void getAllExplicador() throws Exception {

        List<Explicador> explicadores=new ArrayList<>();
        explicadores.add(new Explicador());

        when(explicadorRepository.findAll())
                .thenReturn(explicadores);


        String responseJson=mockMvc.perform(
                get("/explicador")
        ).andExpect(
                status().isOk()
        ).andDo(
                print()
        ).andReturn().getResponse().getContentAsString();

        System.out.println(responseJson);

    }

    @Test
    void getExplicadorById() throws Exception {


        when(explicadorRepository.findById(1L)).thenReturn(Optional.of(new Explicador()));

        mockMvc.perform(get("/explicador/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/explicador/10"))
                .andExpect(status().isNotFound());
    }

    @Test
    void criarExplicador() throws Exception {

        Explicador explicador=new Explicador();
        explicador.setEmail("mail");

        ObjectMapper objectMapper=new ObjectMapper();
        String explicadorJson=objectMapper.writeValueAsString(explicador);

        when(explicadorRepository.findByEmail("mail")).thenReturn(Optional.of(new Explicador()));

        mockMvc.perform(
                post("/explicador").content(explicadorJson).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isBadRequest()
        );

        Explicador nonExistentExplicador=new Explicador();
        nonExistentExplicador.setEmail("newMail");

        String nonExistentExplicadorJson=objectMapper.writeValueAsString(nonExistentExplicador);

        mockMvc.perform(
                post("/explicador").content(nonExistentExplicadorJson).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        );

        mockMvc.perform(
                post("/explicador").content(objectMapper.writeValueAsString(new Explicador())).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isBadRequest()
        );



    }
}