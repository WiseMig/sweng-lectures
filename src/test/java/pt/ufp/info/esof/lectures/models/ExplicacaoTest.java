package pt.ufp.info.esof.lectures.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ExplicacaoTest {

    @Test
    void temMarcacaoPrevia() {

        Explicacao explicacaoBase=new Explicacao();
        explicacaoBase.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(8,0)
        ));

        Explicacao explicacaoAntes=new Explicacao();
        explicacaoAntes.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(7,0)
        ));

        assertFalse(explicacaoBase.temMarcacaoPrevia(explicacaoAntes));

        Explicacao explicacaoDepois=new Explicacao();
        explicacaoDepois.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(9,0)
        ));

        assertFalse(explicacaoBase.temMarcacaoPrevia(explicacaoDepois));

        Explicacao explicacaoComecaAntesTerminaDurante=new Explicacao();
        explicacaoComecaAntesTerminaDurante.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(7,30)
        ));

        assertTrue(explicacaoBase.temMarcacaoPrevia(explicacaoComecaAntesTerminaDurante));

        Explicacao explicacaoComecaDuranteTerminaDepois=new Explicacao();
        explicacaoComecaDuranteTerminaDepois.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(8,30)
        ));

        assertTrue(explicacaoBase.temMarcacaoPrevia(explicacaoComecaDuranteTerminaDepois));

        Explicacao explicacaoIgual=new Explicacao();
        explicacaoIgual.setHora(LocalDateTime.of(
                LocalDate.now(),
                LocalTime.of(8,0)
        ));

        assertTrue(explicacaoBase.temMarcacaoPrevia(explicacaoIgual));

        Explicacao explicacaoDiaSeguinte=new Explicacao();
        explicacaoDiaSeguinte.setHora(LocalDateTime.of(
                LocalDate.now().plusDays(1),
                LocalTime.of(8,0)
        ));

        assertFalse(explicacaoBase.temMarcacaoPrevia(explicacaoDiaSeguinte));
    }
}