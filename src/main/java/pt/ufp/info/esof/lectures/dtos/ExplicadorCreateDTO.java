package pt.ufp.info.esof.lectures.dtos;

import lombok.Data;
import pt.ufp.info.esof.lectures.models.Cadeira;
import pt.ufp.info.esof.lectures.models.Explicador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ExplicadorCreateDTO implements CreateDTO<Explicador> {
    private String email;
    private List<DisponibilidadeCreateDTO> disponibilidades=new ArrayList<>();
    private List<String> cadeiras=new ArrayList<>();

    @Override
    public Explicador converter(){
        Explicador explicador=new Explicador();
        explicador.setEmail(this.getEmail());
        explicador.setDisponibilidades(disponibilidades.stream().map(DisponibilidadeCreateDTO::converter).collect(Collectors.toList()));
        explicador.setCadeiras(cadeiras.stream().map(nomeCadeira -> {
            Cadeira cadeira=new Cadeira();
            cadeira.setNome(nomeCadeira);
            return cadeira;
        }).collect(Collectors.toList()));
        return explicador;
    }
}
