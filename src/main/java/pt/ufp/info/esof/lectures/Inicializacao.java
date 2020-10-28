package pt.ufp.info.esof.lectures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.info.esof.lectures.models.Disponibilidade;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.time.LocalTime;
import java.util.Collections;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ExplicadorRepository explicadorRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("\n\n\nInicializou\n\n\n");

        Explicador explicador=new Explicador();
        explicador.setEmail("explicador@gmail.com");

        Disponibilidade disponibilidade=new Disponibilidade();

        disponibilidade.setHoraInicio(LocalTime.now());
        disponibilidade.setHoraFim(LocalTime.now().plusHours(3));

        explicador.adicionaDisponibilidade(disponibilidade);
        //explicador.setDisponibilidades(Collections.singletonList(disponibilidade));
        explicadorRepository.save(explicador);

    }
}
