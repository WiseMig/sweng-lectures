package pt.ufp.info.esof.lectures.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.lectures.models.Cadeira;
import pt.ufp.info.esof.lectures.models.Disponibilidade;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.CadeiraRepository;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ExplicadorServiceImpl.class)
class ExplicadorServiceImplTest {

    @Autowired
    private ExplicadorService explicadorService;
    @MockBean
    private ExplicadorRepository explicadorRepository;
    @MockBean
    private CadeiraRepository cadeiraRepository;

    @Test
    void findAll() {
        when(explicadorRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(explicadorService.findAll());
    }

    @Test
    void findById() {
        when(explicadorRepository.findById(1L)).thenReturn(Optional.of(new Explicador()));
        assertTrue(explicadorService.findById(1L).isPresent());
        assertTrue(explicadorService.findById(2L).isEmpty());
    }

    @Test
    void createExplicador() {
        String mail="existingMail";
        String cadeiraNome="cadeira";

        Explicador explicador=new Explicador();
        explicador.setEmail("mail");

        Cadeira cadeira=new Cadeira();
        cadeira.setNome(cadeiraNome);

        explicador.adicionaCadeira(cadeira);

        when(cadeiraRepository.findByNome(cadeiraNome)).thenReturn(Optional.of(cadeira));
        when(explicadorRepository.save(explicador)).thenReturn(explicador);

        assertTrue(explicadorService.createExplicador(explicador).isPresent());

        explicador.setEmail(mail);
        when(explicadorRepository.findByEmail(mail)).thenReturn(Optional.of(explicador));
        assertTrue(explicadorService.createExplicador(explicador).isEmpty());

    }

    @Test
    void adicionaDisponibilidade() {

        Disponibilidade disponibilidade=new Disponibilidade();
        disponibilidade.setDiaDaSemana(LocalDate.now().getDayOfWeek());
        disponibilidade.setHoraInicio(LocalTime.of(8,0));
        disponibilidade.setHoraFim(LocalTime.of(10,0));

        Explicador explicador=new Explicador();

        when(explicadorRepository.findById(1L)).thenReturn(Optional.of(explicador));

        assertTrue(explicadorService.adicionaDisponibilidade(1L,disponibilidade).isPresent());
        assertTrue(explicadorService.adicionaDisponibilidade(2L,disponibilidade).isEmpty());

    }
}