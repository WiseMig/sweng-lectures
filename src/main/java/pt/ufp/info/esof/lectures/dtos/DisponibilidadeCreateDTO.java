package pt.ufp.info.esof.lectures.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import pt.ufp.info.esof.lectures.models.Disponibilidade;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class DisponibilidadeCreateDTO implements CreateDTO<Disponibilidade> {
    private DayOfWeek dia;
    @JsonFormat(pattern = "HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalTime horaInicio;
    @JsonFormat(pattern = "HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalTime horaFim;

    @Override
    public Disponibilidade converter() {
        Disponibilidade disponibilidade=new Disponibilidade();
        disponibilidade.setDiaDaSemana(dia);
        disponibilidade.setHoraInicio(horaInicio);
        disponibilidade.setHoraFim(horaFim);
        return disponibilidade;
    }
}
