package pt.ufp.info.esof.lectures.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExplicadorResponseDTO {
    private String email;
    private List<DisponibilidadeCreateDTO> disponibilidades=new ArrayList<>();
    private List<String> cadeiras=new ArrayList<>();
    private List<ExplicacaoDTO> explicacoes=new ArrayList<>();
}
