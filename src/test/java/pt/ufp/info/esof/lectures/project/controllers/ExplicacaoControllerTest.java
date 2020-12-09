package pt.ufp.info.esof.lectures.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.lectures.project.dtos.MarcarAtendimentoDTO;
import pt.ufp.info.esof.lectures.project.models.Aluno;
import pt.ufp.info.esof.lectures.project.models.Explicacao;
import pt.ufp.info.esof.lectures.project.models.Explicador;
import pt.ufp.info.esof.lectures.project.services.ExplicacaoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExplicacaoController.class)
class ExplicacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicacaoService explicacaoService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void marcaAtendimento() throws Exception {


       Aluno aluno=new Aluno();
       aluno.setId(1L);

       Explicador explicador=new Explicador();
       explicador.setId(1L);

       Explicacao explicacao=new Explicacao();
       explicacao.setExplicador(explicador);
       explicacao.setAluno(aluno);

        MarcarAtendimentoDTO atendimentoDTO=new MarcarAtendimentoDTO();
        atendimentoDTO.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(8,0)
        ));

        atendimentoDTO.setExplicadorId(1L);
        atendimentoDTO.setAlunoId(1L);

        when(explicacaoService.marcarAtendimento(atendimentoDTO.converter())).thenReturn(Optional.of(explicacao));

        String explicacaoJson=objectMapper.writeValueAsString(atendimentoDTO);

        mockMvc.perform(post("/explicacao").contentType(MediaType.APPLICATION_JSON).content(explicacaoJson))
                .andExpect(status().isOk());

    }
}