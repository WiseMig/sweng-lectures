package pt.ufp.info.esof.lectures.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.lectures.models.Cadeira;

import java.util.Optional;

@Repository
public interface CadeiraRepository extends CrudRepository<Cadeira,Long> {
    Optional<Cadeira> findByNome(String nome);
}
