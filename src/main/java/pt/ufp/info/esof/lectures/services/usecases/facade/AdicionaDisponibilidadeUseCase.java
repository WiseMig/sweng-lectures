package pt.ufp.info.esof.lectures.services.usecases.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.lectures.models.Disponibilidade;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.util.Optional;

@Service
public class AdicionaDisponibilidadeUseCase {

    private final ExplicadorRepository explicadorRepository;

    @Autowired
    public AdicionaDisponibilidadeUseCase(ExplicadorRepository explicadorRepository) {
        this.explicadorRepository = explicadorRepository;
    }

    public Optional<Explicador> adicionaDisponibilidade(Long explicadorId, Disponibilidade disponibilidade) {
        Optional<Explicador> optionalExplicador=this.explicadorRepository.findById(explicadorId);
        if(optionalExplicador.isPresent()){
            Explicador explicador=optionalExplicador.get();
            int quantidadeDeDisponibilidadesAntes=explicador.getDisponibilidades().size();
            explicador.adicionaDisponibilidade(disponibilidade);
            int quantidadedeDisponibilidadesDepois=explicador.getDisponibilidades().size();
            if(quantidadeDeDisponibilidadesAntes!=quantidadedeDisponibilidadesDepois) {
                return Optional.of(explicador);
            }
        }
        return Optional.empty();
    }
}
