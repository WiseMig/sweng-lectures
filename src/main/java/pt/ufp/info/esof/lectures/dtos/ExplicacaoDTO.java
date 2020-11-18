package pt.ufp.info.esof.lectures.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExplicacaoDTO {
    private LocalDateTime hora;
    private String explicadorNome;
    private String alunoNome;

}
