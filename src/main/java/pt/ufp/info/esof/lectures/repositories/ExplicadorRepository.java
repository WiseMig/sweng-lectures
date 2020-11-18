package pt.ufp.info.esof.lectures.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.lectures.models.Explicador;

import java.util.Optional;

@Repository
public interface ExplicadorRepository extends CrudRepository<Explicador,Long> {
    Optional<Explicador> findByEmail(String email);
}
