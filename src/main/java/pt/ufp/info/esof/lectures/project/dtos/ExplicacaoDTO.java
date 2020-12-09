package pt.ufp.info.esof.lectures.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExplicacaoDTO {
    private LocalDateTime hora;
    private String explicadorNome;
    private String alunoNome;

}
