package pt.ufp.info.esof.lectures.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ufp.info.esof.lectures.models.Cadeira;
import pt.ufp.info.esof.lectures.models.Explicador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExplicadorCreateDTO implements CreateDTO<Explicador> {
    Logger logger= LoggerFactory.getLogger(this.getClass());

    private String email;
    private List<DisponibilidadeCreateDTO> disponibilidades=new ArrayList<>();
    private List<String> cadeiras=new ArrayList<>();

    @Override
    public Explicador converter(){
        logger.info("Convertido para Modelo");
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
