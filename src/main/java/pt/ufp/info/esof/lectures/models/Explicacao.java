package pt.ufp.info.esof.lectures.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Explicacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //https://www.javacodemonk.com/java-8-date-time-json-formatting-with-jackson-5fe5ff13
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalDateTime hora;

    @ManyToOne
    private Explicador explicador;
    @ManyToOne
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
