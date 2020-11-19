package pt.ufp.info.esof.lectures.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@EqualsAndHashCode
public class Disponibilidade {
    private Long id;
    private DayOfWeek diaDaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    @EqualsAndHashCode.Exclude
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
