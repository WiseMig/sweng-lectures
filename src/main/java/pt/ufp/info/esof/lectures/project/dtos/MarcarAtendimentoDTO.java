package pt.ufp.info.esof.lectures.project.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.ufp.info.esof.lectures.project.models.Aluno;
import pt.ufp.info.esof.lectures.project.models.Explicacao;
import pt.ufp.info.esof.lectures.project.models.Explicador;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarcarAtendimentoDTO implements CreateDTO<Explicacao> {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm",shape = JsonFormat.Shape.STRING)
    private LocalDateTime hora;
    private Long explicadorId;
    private Long alunoId;

    @Override
    public Explicacao converter(){
        Explicacao explicacao=new Explicacao();
        explicacao.setHora(hora);
        Explicador explicador=new Explicador();
        explicador.setId(explicadorId);
        Aluno aluno=new Aluno();
        aluno.setId(alunoId);
        explicacao.setAluno(aluno);
        explicacao.setExplicador(explicador);
        return explicacao;
    }
}
