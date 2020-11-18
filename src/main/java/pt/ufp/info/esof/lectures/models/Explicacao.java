package pt.ufp.info.esof.lectures.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@EqualsAndHashCode
public class Explicacao {
    private Long id;
    private LocalDateTime hora;
    private Explicador explicador;
    private Aluno aluno;

    public boolean temMarcacaoPrevia(Explicacao explicacao){
        if(!explicacao.getHora().toLocalDate().equals(this.getHora().toLocalDate())){
            return false;
        }
        LocalTime horaInicioExplicacao1=this.hora.toLocalTime();
        LocalTime horaFimExplicacao1=horaInicioExplicacao1.plusHours(1);
        LocalTime horaInicioExplicacao2=explicacao.hora.toLocalTime();
        LocalTime horaFimExplicacao2=horaInicioExplicacao2.plusHours(1);

        return !(
                    horaFimExplicacao2.equals(horaInicioExplicacao1)||
                    horaFimExplicacao2.isBefore(horaInicioExplicacao1)||
                    horaInicioExplicacao2.equals(horaFimExplicacao1)||
                            horaInicioExplicacao2.isAfter(horaFimExplicacao1)
                );
    }

}
