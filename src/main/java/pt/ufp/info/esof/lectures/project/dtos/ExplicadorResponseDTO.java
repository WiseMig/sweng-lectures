package pt.ufp.info.esof.lectures.project.dtos;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExplicadorResponseDTO{
    private String email;
    private List<DisponibilidadeCreateDTO> disponibilidades=new ArrayList<>();
    private List<String> cadeiras=new ArrayList<>();
    private List<ExplicacaoDTO> explicacoes=new ArrayList<>();
}
