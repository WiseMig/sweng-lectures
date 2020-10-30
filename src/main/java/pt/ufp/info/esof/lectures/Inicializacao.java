package pt.ufp.info.esof.lectures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.info.esof.lectures.models.Aluno;
import pt.ufp.info.esof.lectures.models.Disponibilidade;
import pt.ufp.info.esof.lectures.models.Explicacao;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.AlunoRepository;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ExplicadorRepository explicadorRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("\n\n\nInicializou\n\n\n");

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
        alunoRepository.save(aluno);

        aluno.addExplicacao(explicacao);
        explicador.adicionarExplicacao(explicacao);

        explicadorRepository.save(explicador);

    }
}
