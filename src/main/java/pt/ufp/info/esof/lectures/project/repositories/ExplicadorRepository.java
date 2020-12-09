package pt.ufp.info.esof.lectures.project.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.ufp.info.esof.lectures.project.models.Explicador;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExplicadorRepository extends CrudRepository<Explicador,Long> {
    Optional<Explicador> findByEmail(String email);

    //https://www.baeldung.com/spring-data-jpa-null-parameters
    @Query("SELECT e FROM Explicador e join e.disponibilidades d join e.cadeiras c where (:dia is null or d.diaDaSemana=:dia) and (:hora is null or d.horaInicio<= :hora) and (:hora is null or d.horaFim>= :hora) and (:cadeira is null or c.nome=:cadeira)")
    List<Explicador> pesquisaExplicadores(@Param("dia") DayOfWeek dia, @Param("hora") LocalTime horaInicio, @Param("cadeira")String nomeCadeira);
}
