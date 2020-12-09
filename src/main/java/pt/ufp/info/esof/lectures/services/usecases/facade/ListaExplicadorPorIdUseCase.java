package pt.ufp.info.esof.lectures.services.usecases.facade;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.util.Optional;

@Service
public class ListaExplicadorPorIdUseCase {

    private final ExplicadorRepository explicadorRepository;

    public ListaExplicadorPorIdUseCase(ExplicadorRepository explicadorRepository) {
        this.explicadorRepository = explicadorRepository;
    }

    public Optional<Explicador> findById(Long id) {
        return explicadorRepository.findById(id);
    }
}
