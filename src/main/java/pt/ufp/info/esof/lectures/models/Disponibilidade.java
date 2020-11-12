package pt.ufp.info.esof.lectures.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Disponibilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DayOfWeek diaDaSemana;

    @JsonFormat(pattern = "HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalTime horaInicio;

    @JsonFormat(pattern = "HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalTime horaFim;

    @ManyToOne
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
