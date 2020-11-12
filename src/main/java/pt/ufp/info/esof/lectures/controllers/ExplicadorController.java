package pt.ufp.info.esof.lectures.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.lectures.models.Disponibilidade;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.util.Optional;

@Controller
@RequestMapping("/explicador")
public class ExplicadorController {
    private final ExplicadorRepository explicadorRepository;

    public ExplicadorController(ExplicadorRepository explicadorRepository) {
        this.explicadorRepository = explicadorRepository;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Explicador>> getAllExplicador(){
        return ResponseEntity.ok(explicadorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Explicador> getExplicadorById(@PathVariable Long id){
        Optional<Explicador> optionalExplicador=explicadorRepository.findById(id);
        if(optionalExplicador.isPresent()){
            return ResponseEntity.ok(optionalExplicador.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Explicador> createExplicador(@RequestBody Explicador explicador){
        if(explicadorRepository.findByEmail(explicador.getEmail())==null){
           return ResponseEntity.ok(explicadorRepository.save(explicador));
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{explicadorId}")
    public ResponseEntity<Explicador> adicionaDisponibilidade(@PathVariable Long explicadorId, @RequestBody Disponibilidade disponibilidade){
        Optional<Explicador> optionalExplicador=this.explicadorRepository.findById(explicadorId);
        if(optionalExplicador.isPresent()){
            Explicador explicador=optionalExplicador.get();
            int quantidadeDeDisponibilidadesAntes=explicador.getDisponibilidades().size();
            explicador.adicionaDisponibilidade(disponibilidade);
            int quantidadedeDisponibilidadesDepois=explicador.getDisponibilidades().size();
            if(quantidadeDeDisponibilidadesAntes!=quantidadedeDisponibilidadesDepois) {
                return ResponseEntity.ok(explicador);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
