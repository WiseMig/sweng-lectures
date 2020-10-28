package pt.ufp.info.esof.lectures.models;

import edu.princeton.cs.algorithms.EdgeWeightedDigraph;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Disponibilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DayOfWeek diaDaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    @ManyToOne
    private Explicador explicador;

    protected boolean estaDisponivel(Explicacao explicacao){

        if(!explicacao.getHora().getDayOfWeek().equals(diaDaSemana)){
            return false;
        }
        LocalTime horaInicioExplicacao=explicacao.getHora().toLocalTime();
        LocalTime horaFimExplicacao=horaInicioExplicacao.plusHours(1);
        return (
                horaInicioExplicacao.equals(horaInicio)||
                        horaInicioExplicacao.isAfter(horaInicio)
                )&&(
                        horaFimExplicacao.equals(horaFim)||
                                horaFimExplicacao.isBefore(horaFim)
                );

    }
}
