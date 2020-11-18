package pt.ufp.info.esof.lectures.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ufp.info.esof.lectures.models.Cadeira;
import pt.ufp.info.esof.lectures.models.Disponibilidade;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.CadeiraRepository;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExplicadorServiceImpl implements ExplicadorService{
    private final ExplicadorRepository explicadorRepository;
    private final CadeiraRepository cadeiraRepository;

    @Autowired
    public ExplicadorServiceImpl(ExplicadorRepository explicadorRepository,CadeiraRepository cadeiraRepository) {
        this.explicadorRepository = explicadorRepository;
        this.cadeiraRepository=cadeiraRepository;
    }

    @Override
    public List<Explicador> findAll() {
        List<Explicador> explicadores=new ArrayList<>();
        explicadorRepository.findAll().forEach(explicadores::add);
        return explicadores;
    }

    @Override
    public Optional<Explicador> findById(Long id) {
        return explicadorRepository.findById(id);
    }

    @Override
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

    @Override
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
