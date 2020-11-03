package pt.ufp.info.esof.lectures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.info.esof.lectures.models.*;
import pt.ufp.info.esof.lectures.repositories.AlunoRepository;
import pt.ufp.info.esof.lectures.repositories.CadeiraRepository;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;
import pt.ufp.info.esof.lectures.repositories.FaculdadeRepository;

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
    @Autowired
    private FaculdadeRepository faculdadeRepository;
    @Autowired
    private CadeiraRepository cadeiraRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("\n\n\nInicializou\n\n\n");

        Faculdade fct=new Faculdade();
        fct.setNome("FCT");

        Curso engenhariaInformatica=new Curso();
        engenhariaInformatica.setNome("Engenharia Informática");

        Cadeira matematicaInformatica=new Cadeira();
        matematicaInformatica.setNome("Matemática");

        Cadeira fisicaInformatica=new Cadeira();
        fisicaInformatica.setNome("Física");

        Cadeira esof=new Cadeira();
        esof.setNome("Engenharia de Software");

        engenhariaInformatica.addCadeira(matematicaInformatica);
        engenhariaInformatica.addCadeira(fisicaInformatica);
        engenhariaInformatica.addCadeira(esof);

        Curso engenhariaCivil=new Curso();
        engenhariaCivil.setNome("Engenharia Civil");

        Cadeira matematica=new Cadeira();
        matematica.setNome("Matemática");

        Cadeira fisica=new Cadeira();
        fisica.setNome("Física");

        engenhariaCivil.addCadeira(matematica);
        engenhariaCivil.addCadeira(fisica);

        fct.addCurso(engenhariaInformatica);
        fct.addCurso(engenhariaCivil);

        this.faculdadeRepository.save(fct);

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

        explicador.adicionaCadeira(esof);

        explicadorRepository.save(explicador);
        cadeiraRepository.save(esof);


    }
}
