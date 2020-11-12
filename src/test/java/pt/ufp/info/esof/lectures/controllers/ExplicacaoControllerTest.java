package pt.ufp.info.esof.lectures.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.ufp.info.esof.lectures.models.Aluno;
import pt.ufp.info.esof.lectures.models.Disponibilidade;
import pt.ufp.info.esof.lectures.models.Explicacao;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.AlunoRepository;
import pt.ufp.info.esof.lectures.repositories.ExplicacaoRepository;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExplicacaoController.class)
class ExplicacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExplicacaoRepository explicacaoRepository;
    @MockBean
    private ExplicadorRepository explicadorRepository;
    @MockBean
    private AlunoRepository alunoRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void marcaAtendimento() throws Exception {

        //https://www.baeldung.com/jackson-serialize-dates
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Aluno aluno=new Aluno();
        aluno.setId(1L);

        Disponibilidade disponibilidade=new Disponibilidade();
        disponibilidade.setHoraInicio(LocalTime.of(8,0));
        disponibilidade.setHoraFim(LocalTime.of(12,0));
        disponibilidade.setDiaDaSemana(LocalDate.now().getDayOfWeek());

        Explicador explicador=new Explicador();
        explicador.adicionaDisponibilidade(disponibilidade);
        explicador.setId(1L);

        Explicacao explicacao=new Explicacao();
        explicacao.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(8,0)
        ));

        explicacao.setExplicador(explicador);
        explicacao.setAluno(aluno);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(explicadorRepository.findById(1L)).thenReturn(Optional.of(explicador));
        when(explicacaoRepository.save(explicacao)).thenReturn(explicacao);

        String explicacaoJson=objectMapper.writeValueAsString(explicacao);

        mockMvc.perform(post("/explicacao").contentType(MediaType.APPLICATION_JSON).content(explicacaoJson))
                .andExpect(status().isOk());

        mockMvc.perform(post("/explicacao").contentType(MediaType.APPLICATION_JSON).content(explicacaoJson))
                .andExpect(status().isBadRequest());



    }
}