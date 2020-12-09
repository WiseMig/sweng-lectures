package pt.ufp.info.esof.lectures.project.services;

import pt.ufp.info.esof.lectures.project.models.Disponibilidade;
import pt.ufp.info.esof.lectures.project.models.Explicador;

import java.util.List;
import java.util.Optional;

public interface ExplicadorService {
    List<Explicador> findAll();

    Optional<Explicador> findById(Long id);

    Optional<Explicador> createExplicador(Explicador converter);

    Optional<Explicador> adicionaDisponibilidade(Long explicadorId, Disponibilidade disponibilidade);
}
