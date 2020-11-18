package pt.ufp.info.esof.lectures.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.lectures.models.Aluno;
import pt.ufp.info.esof.lectures.models.Explicacao;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.AlunoRepository;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.util.Optional;

@Service
public class ExplicacaoServiceImpl implements ExplicacaoService{
    private final ExplicadorRepository explicadorRepository;
    private final AlunoRepository alunoRepository;

    @Autowired
    public ExplicacaoServiceImpl(ExplicadorRepository explicadorRepository, AlunoRepository alunoRepository) {
        this.explicadorRepository = explicadorRepository;
        this.alunoRepository = alunoRepository;
    }

    @Override
    public Optional<Explicacao> marcarAtendimento(Explicacao explicacao) {
        Optional<Explicador> optionalExplicador=explicadorRepository
                .findById(explicacao.getExplicador().getId());
        Optional<Aluno> optionalAluno=alunoRepository.findById(explicacao.getAluno().getId());
        if(optionalAluno.isPresent()&&optionalExplicador.isPresent()){
            Explicador explicador=optionalExplicador.get();
            Aluno aluno=optionalAluno.get();
            if(explicador.adicionarExplicacao(explicacao)!=null){
                aluno.addExplicacao(explicacao);
                explicadorRepository.save(explicador);
                alunoRepository.save(aluno);
                return Optional.of(explicacao);
            }
        }
        return Optional.empty();

    }
}
