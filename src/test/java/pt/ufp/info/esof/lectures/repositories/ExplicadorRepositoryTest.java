package pt.ufp.info.esof.lectures.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.lectures.models.Disponibilidade;
import pt.ufp.info.esof.lectures.models.Explicador;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExplicadorRepositoryTest {
    @Autowired
    private ExplicadorRepository explicadorRepository;
    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;

    @Test
    public void testaCriacaoExplicador(){
        Explicador explicador=new Explicador();
        explicador.setEmail("explicador@gmail.com");

        Disponibilidade disponibilidade=new Disponibilidade();

        disponibilidade.setHoraInicio(LocalTime.now());
        disponibilidade.setHoraFim(LocalTime.now().plusHours(3));

        //explicador.adicionaDisponibilidade(disponibilidade);
        explicador.setDisponibilidades(Collections.singletonList(disponibilidade));

        assertEquals(0,explicadorRepository.count());
        assertEquals(0,disponibilidadeRepository.count());
        explicadorRepository.save(explicador);
        assertEquals(1,explicadorRepository.count());
        assertEquals(1,disponibilidadeRepository.count());
        assertNotNull(explicadorRepository.findByEmail("explicador@gmail.com"));
        assertNull(explicadorRepository.findByEmail("invalid email"));

    }
}