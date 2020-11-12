package pt.ufp.info.esof.lectures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ufp.info.esof.lectures.models.Aluno;
import pt.ufp.info.esof.lectures.models.Explicacao;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.AlunoRepository;
import pt.ufp.info.esof.lectures.repositories.ExplicacaoRepository;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.util.Optional;

@Controller
@RequestMapping("/explicacao")
public class ExplicacaoController {

    private final ExplicadorRepository explicadorRepository;
    private final ExplicacaoRepository explicacaoRepository;
    private final AlunoRepository alunoRepository;

    @Autowired
    public ExplicacaoController(ExplicadorRepository explicadorRepository, ExplicacaoRepository explicacaoRepository, AlunoRepository alunoRepository) {
        this.explicadorRepository = explicadorRepository;
        this.explicacaoRepository = explicacaoRepository;
        this.alunoRepository = alunoRepository;
    }

    @PostMapping
    public ResponseEntity<Explicacao> marcaAtendimento(@RequestBody Explicacao explicacao){
        Optional<Explicador> optionalExplicador=explicadorRepository.findById(explicacao.getExplicador().getId());
        Optional<Aluno> optionalAluno=alunoRepository.findById(explicacao.getAluno().getId());
        if(optionalAluno.isPresent()&&optionalExplicador.isPresent()){
            Explicador explicador=optionalExplicador.get();
            Aluno aluno=optionalAluno.get();
            if(explicador.adicionarExplicacao(explicacao)!=null){
                aluno.addExplicacao(explicacao);
                explicadorRepository.save(explicador);
                alunoRepository.save(aluno);
                return ResponseEntity.ok(explicacao);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
