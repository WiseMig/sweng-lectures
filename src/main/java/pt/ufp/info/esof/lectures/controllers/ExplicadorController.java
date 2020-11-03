package pt.ufp.info.esof.lectures.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.repositories.ExplicadorRepository;

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
}
