package pt.ufp.info.esof.lectures.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DisponibilidadeTest {

    @Test
    void estaDisponivel() {

        Disponibilidade disponibilidade=new Disponibilidade();
        disponibilidade.setDiaDaSemana(LocalDate.now().getDayOfWeek());
        disponibilidade.setHoraInicio(LocalTime.of(8,0));
        disponibilidade.setHoraFim(LocalTime.of(9,0));

        Explicacao explicacaoAntes=new Explicacao();
        explicacaoAntes.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(7,0)
        ));

        assertFalse(disponibilidade.estaDisponivel(explicacaoAntes));

        Explicacao explicacaoDepois=new Explicacao();
        explicacaoDepois.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(9,0)
        ));

        assertFalse(disponibilidade.estaDisponivel(explicacaoDepois));

        Explicacao explicacaoOk=new Explicacao();
        explicacaoOk.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(8,0)
        ));

        assertTrue(disponibilidade.estaDisponivel(explicacaoOk));

        Explicacao explicacaoDiaSeguinte=new Explicacao();
        explicacaoDiaSeguinte.setHora(LocalDateTime.of(
                LocalDate.now().plusDays(1),
                LocalTime.of(8,0)
        ));

        assertFalse(disponibilidade.estaDisponivel(explicacaoDiaSeguinte));

    }
}