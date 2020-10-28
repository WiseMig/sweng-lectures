package pt.ufp.info.esof.lectures.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.lectures.models.Explicador;

import java.util.List;

@Repository
public interface ExplicadorRepository extends CrudRepository<Explicador,Long> {
    Explicador findByEmail(String email);
}
