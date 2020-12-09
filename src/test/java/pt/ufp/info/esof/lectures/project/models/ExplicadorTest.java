package pt.ufp.info.esof.lectures.project.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ExplicadorTest {

    @Test
    void adicionarExplicacao() {
        Explicador explicador=new Explicador();

        Disponibilidade disponibilidade=new Disponibilidade();
        disponibilidade.setDiaDaSemana(LocalDate.now().getDayOfWeek());
        disponibilidade.setHoraInicio(LocalTime.of(8,0));
        disponibilidade.setHoraFim(LocalTime.of(9,0));

        explicador.adicionaDisponibilidade(disponibilidade);

        assertEquals(0,explicador.getExplicacoes().size());

        Explicacao explicacaoComecaAntesTerminaDurante=new Explicacao();
        explicacaoComecaAntesTerminaDurante.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(7,30)
        ));
        explicador.adicionarExplicacao(explicacaoComecaAntesTerminaDurante);
        assertEquals(0,explicador.getExplicacoes().size());

        Explicacao explicacaoDiaSeguinte=new Explicacao();
        explicacaoDiaSeguinte.setHora(LocalDateTime.of(
                LocalDate.now().plusDays(1),
                LocalTime.of(8,0)
        ));

        explicador.adicionarExplicacao(explicacaoDiaSeguinte);
        assertEquals(0,explicador.getExplicacoes().size());

        Explicacao explicacaoOk=new Explicacao();
        explicacaoOk.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(8,0)
        ));

        explicador.adicionarExplicacao(explicacaoOk);
        assertEquals(1,explicador.getExplicacoes().size());

        explicador.adicionarExplicacao(explicacaoOk);
        assertEquals(1,explicador.getExplicacoes().size());

    }
}