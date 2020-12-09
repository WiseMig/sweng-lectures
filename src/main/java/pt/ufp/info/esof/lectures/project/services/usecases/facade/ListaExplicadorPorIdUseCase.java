package pt.ufp.info.esof.lectures.project.services.usecases.facade;

import org.springframework.stereotype.Service;
import pt.ufp.info.esof.lectures.project.models.Explicador;
import pt.ufp.info.esof.lectures.project.repositories.ExplicadorRepository;

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
