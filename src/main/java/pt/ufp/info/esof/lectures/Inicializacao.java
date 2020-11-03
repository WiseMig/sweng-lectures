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
    private CadeiraRepository cadeiraRepository;
    @Autowired
    private FaculdadeRepository faculdadeRepository;

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

        engenhariaInformatica.adicionaCadeira(matematicaInformatica);
        engenhariaInformatica.adicionaCadeira(fisicaInformatica);
        engenhariaInformatica.adicionaCadeira(esof);

        Curso engenhariaCivil=new Curso();
        engenhariaCivil.setNome("Engenharia Civil");

        Cadeira matematica=new Cadeira();
        matematica.setNome("Matemática");

        Cadeira fisica=new Cadeira();
        fisica.setNome("Física");

        engenhariaCivil.adicionaCadeira(matematica);
        engenhariaCivil.adicionaCadeira(fisica);

        fct.adicionaCurso(engenhariaInformatica);
        fct.adicionaCurso(engenhariaCivil);

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
        this.alunoRepository.save(aluno);

        aluno.addExplicacao(explicacao);
        explicador.adicionarExplicacao(explicacao);

        explicador.adicionaCadeira(esof);
        explicador.adicionaCadeira(matematicaInformatica);

        this.explicadorRepository.save(explicador);
        this.cadeiraRepository.save(esof);
        this.cadeiraRepository.save(matematicaInformatica);

    }
}
