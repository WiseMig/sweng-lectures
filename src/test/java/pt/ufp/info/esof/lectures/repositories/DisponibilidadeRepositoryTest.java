package pt.ufp.info.esof.lectures.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.lectures.models.Disponibilidade;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DisponibilidadeRepositoryTest {
    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;

    @Test
    public void testeCriacaoDisponibilidade(){
        Disponibilidade disponibilidade=new Disponibilidade();

        disponibilidade.setHoraInicio(LocalTime.now());
        disponibilidade.setHoraFim(LocalTime.now().plusHours(3));

        assertEquals(0,disponibilidadeRepository.count());

        disponibilidadeRepository.save(disponibilidade);

        assertEquals(1,disponibilidadeRepository.count());
    }
}