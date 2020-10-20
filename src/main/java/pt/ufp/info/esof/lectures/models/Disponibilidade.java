package pt.ufp.info.esof.lectures.models;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Disponibilidade {
    private DayOfWeek diaDaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    protected boolean estaDisponivel(Explicacao explicacao){
        return false;
    }
}
