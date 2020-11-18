package pt.ufp.info.esof.lectures.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.lectures.models.Aluno;
import pt.ufp.info.esof.lectures.models.Disponibilidade;
import pt.ufp.info.esof.lectures.models.Explicacao;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.AlunoRepository;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ExplicacaoServiceImpl.class)
class ExplicacaoServiceImplTest {

    @Autowired
    private ExplicacaoService explicacaoService;

    @MockBean
    private ExplicadorRepository explicadorRepository;
    @MockBean
    private AlunoRepository alunoRepository;

    @Test
    void marcarAtendimento() {
        Explicacao explicacao=new Explicacao();
        explicacao.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(9,0)
        ));

        Disponibilidade disponibilidade=new Disponibilidade();
        disponibilidade.setDiaDaSemana(LocalDate.now().getDayOfWeek());
        disponibilidade.setHoraInicio(LocalTime.of(8,0));
        disponibilidade.setHoraFim(LocalTime.of(10,0));

        Aluno aluno=new Aluno();
        aluno.setId(1L);

        Explicador explicador=new Explicador();
        explicador.setId(1L);
        explicador.adicionaDisponibilidade(disponibilidade);

        explicacao.setAluno(aluno);
        explicacao.setExplicador(explicador);

        when(explicadorRepository.findById(1L)).thenReturn(Optional.of(explicador));
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        Optional<Explicacao> optionalExplicacao=explicacaoService.marcarAtendimento(explicacao);
        assertTrue(optionalExplicacao.isPresent());
        Explicacao explicacaoMarcada=optionalExplicacao.get();

        assertEquals(1,explicacaoMarcada.getExplicador().getExplicacoes().size());

        optionalExplicacao=explicacaoService.marcarAtendimento(explicacao);
        assertTrue(optionalExplicacao.isEmpty());
    }
}