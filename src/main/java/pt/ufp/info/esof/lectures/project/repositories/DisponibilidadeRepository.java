package pt.ufp.info.esof.lectures.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.lectures.project.models.Disponibilidade;

@Repository
public interface DisponibilidadeRepository extends CrudRepository<Disponibilidade,Long> {

}
