package pt.ufp.info.esof.lectures.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.lectures.models.Aluno;
import pt.ufp.info.esof.lectures.models.Disponibilidade;
import pt.ufp.info.esof.lectures.models.Explicacao;
import pt.ufp.info.esof.lectures.models.Explicador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExplicadorRepositoryTest {
    @Autowired
    private ExplicadorRepository explicadorRepository;
    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;
    @Autowired
    private ExplicacaoRepository explicacaoRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    public void testaCriacaoExplicador(){
        Explicador explicador=new Explicador();
        explicador.setEmail("explicador@gmail.com");

        Disponibilidade disponibilidade=new Disponibilidade();

        disponibilidade.setDiaDaSemana(LocalDate.now().getDayOfWeek());
        disponibilidade.setHoraInicio(LocalTime.of(8,0));
        disponibilidade.setHoraFim(disponibilidade.getHoraInicio().plusHours(3));

        explicador.setDisponibilidades(Collections.singletonList(disponibilidade));

        Explicacao explicacao=new Explicacao();
        explicacao.setExplicador(explicador);
        explicacao.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(8,0)
        ));

        Aluno aluno=new Aluno();

        assertNull(aluno.getId());
        alunoRepository.save(aluno);
        assertNotNull(aluno.getId());

        aluno.addExplicacao(explicacao);
        explicador.adicionarExplicacao(explicacao);

        assertEquals(0,explicadorRepository.count());
        assertEquals(0,disponibilidadeRepository.count());

        assertNull(disponibilidade.getId());
        assertNull(explicador.getId());
        explicadorRepository.save(explicador);
        assertNotNull(explicador.getId());
        assertNotNull(disponibilidade.getId());

        assertEquals(1,explicadorRepository.count());
        assertEquals(1,disponibilidadeRepository.count());
        assertNotNull(explicadorRepository.findByEmail("explicador@gmail.com"));
        assertTrue(explicadorRepository.findByEmail("invalid email").isEmpty());

        assertEquals(1,explicacaoRepository.count());

    }
}