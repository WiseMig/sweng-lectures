package pt.ufp.info.esof.lectures.project.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.lectures.project.models.Disponibilidade;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
