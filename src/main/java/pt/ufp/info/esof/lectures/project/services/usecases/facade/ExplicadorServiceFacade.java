package pt.ufp.info.esof.lectures.project.services.usecases.facade;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.lectures.project.models.Disponibilidade;
import pt.ufp.info.esof.lectures.project.models.Explicador;
import pt.ufp.info.esof.lectures.project.services.ExplicadorService;

import java.util.List;
import java.util.Optional;

@Service
public class ExplicadorServiceFacade implements ExplicadorService {

    private final AdicionaDisponibilidadeUseCase adicionaDisponibilidadeUseCase;
    private final CriarExplicadorUseCase criarExplicadorUseCase;
    private final ListaExplicadorPorIdUseCase listaExplicadorPorIdUseCase;
    private final ListTodosExplicadoresUseCase listTodosExplicadoresUseCase;

    public ExplicadorServiceFacade(AdicionaDisponibilidadeUseCase adicionaDisponibilidadeUseCase, CriarExplicadorUseCase criarExplicadorUseCase, ListaExplicadorPorIdUseCase listaExplicadorPorIdUseCase, ListTodosExplicadoresUseCase listTodosExplicadoresUseCase) {
        this.adicionaDisponibilidadeUseCase = adicionaDisponibilidadeUseCase;
        this.criarExplicadorUseCase = criarExplicadorUseCase;
        this.listaExplicadorPorIdUseCase = listaExplicadorPorIdUseCase;
        this.listTodosExplicadoresUseCase = listTodosExplicadoresUseCase;
    }

    @Override
    public List<Explicador> findAll() {
        return listTodosExplicadoresUseCase.findAll();
    }

    @Override
    public Optional<Explicador> findById(Long id) {
        return listaExplicadorPorIdUseCase.findById(id);
    }

    @Override
    public Optional<Explicador> createExplicador(Explicador converter) {
        return criarExplicadorUseCase.createExplicador(converter);
    }

    @Override
    public Optional<Explicador> adicionaDisponibilidade(Long explicadorId, Disponibilidade disponibilidade) {
        return adicionaDisponibilidadeUseCase.adicionaDisponibilidade(explicadorId,disponibilidade);
    }
}
