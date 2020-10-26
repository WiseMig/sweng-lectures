package pt.ufp.info.esof.lectures.models;

import edu.princeton.cs.algorithms.EdgeWeightedDigraph;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
public class Disponibilidade {
    private DayOfWeek diaDaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
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
