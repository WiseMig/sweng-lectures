package pt.ufp.info.esof.lectures.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.lectures.models.Faculdade;

@Repository
public interface FaculdadeRepository extends CrudRepository<Faculdade,Long> {
}
