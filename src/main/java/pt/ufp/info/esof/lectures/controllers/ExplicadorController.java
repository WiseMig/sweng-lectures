package pt.ufp.info.esof.lectures.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

import java.util.Optional;

@Controller
@RequestMapping("/explicador")
public class ExplicadorController {
    private final ExplicadorRepository explicadorRepository;

    @Autowired
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
        //return optionalExplicador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        if(optionalExplicador.isPresent()){
            return ResponseEntity.ok(optionalExplicador.get());
        }
        return ResponseEntity.notFound().build();
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<Explicador> criarExplicador(@RequestBody Explicador explicador){
        if(explicador.getEmail()==null || explicador.getEmail().isEmpty() ){
            return ResponseEntity.badRequest().build();
        }
        Optional<Explicador> optionalExplicador=explicadorRepository.findByEmail(explicador.getEmail());
        if(optionalExplicador.isEmpty()){
            return ResponseEntity.ok(explicadorRepository.save(explicador));
        }
        return ResponseEntity.badRequest().build();
    }

}
