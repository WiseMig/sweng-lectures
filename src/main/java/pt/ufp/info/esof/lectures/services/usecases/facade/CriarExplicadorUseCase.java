package pt.ufp.info.esof.lectures.services.usecases.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.lectures.models.Cadeira;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.CadeiraRepository;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CriarExplicadorUseCase {

    private final ExplicadorRepository explicadorRepository;
    private final CadeiraRepository cadeiraRepository;

    @Autowired
    public CriarExplicadorUseCase(ExplicadorRepository explicadorRepository, CadeiraRepository cadeiraRepository) {
        this.explicadorRepository = explicadorRepository;
        this.cadeiraRepository = cadeiraRepository;
    }

    public Optional<Explicador> createExplicador(Explicador explicador) {
        Optional<Explicador> optionalExplicador=explicadorRepository.findByEmail(explicador.getEmail());
        if(optionalExplicador.isEmpty()){
            explicadorRepository.save(explicador);
            List<Cadeira> cadeiras=new ArrayList<>();
            explicador.getCadeiras().forEach(cadeira -> {
                Optional<Cadeira> optionalCadeira=cadeiraRepository.findByNome(cadeira.getNome());
                if(optionalCadeira.isPresent()){
                    cadeiras.add(cadeira);
                    cadeira.adicionaExplicador(explicador);
                    cadeiraRepository.save(optionalCadeira.get());
                }
            });
            explicador.setCadeiras(cadeiras);
            return Optional.of(explicadorRepository.save(explicador));
        }
        return Optional.empty();
    }
}
