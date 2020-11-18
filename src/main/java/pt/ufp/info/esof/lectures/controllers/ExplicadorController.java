package pt.ufp.info.esof.lectures.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.ufp.info.esof.lectures.dtos.conversores.ConverterExplicadorParaDTO;
import pt.ufp.info.esof.lectures.dtos.DisponibilidadeCreateDTO;
import pt.ufp.info.esof.lectures.dtos.ExplicadorCreateDTO;
import pt.ufp.info.esof.lectures.dtos.ExplicadorResponseDTO;
import pt.ufp.info.esof.lectures.models.Explicador;
import pt.ufp.info.esof.lectures.services.ExplicadorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/explicador")
public class ExplicadorController {
    private final ExplicadorService explicadorService;
    private final ConverterExplicadorParaDTO converterExplicadorParaDTO=new ConverterExplicadorParaDTO();

    public ExplicadorController(ExplicadorService explicadorService) {
        this.explicadorService = explicadorService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<ExplicadorResponseDTO>> getAllExplicador(){
        List<ExplicadorResponseDTO> responseDTOS=new ArrayList<>();
        explicadorService.findAll().forEach(explicador -> responseDTOS.add(converterExplicadorParaDTO.converter(explicador)));
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExplicadorResponseDTO> getExplicadorById(@PathVariable Long id){
        Optional<Explicador> optionalExplicador=explicadorService.findById(id);
        return optionalExplicador.map(explicador -> {
            ExplicadorResponseDTO explicadorResponseDTO=converterExplicadorParaDTO.converter(explicador);
            return ResponseEntity.ok(explicadorResponseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ExplicadorResponseDTO> createExplicador(@RequestBody ExplicadorCreateDTO explicador){
        Optional<Explicador> optionalExplicador=explicadorService.createExplicador(explicador.converter());
        return optionalExplicador.map(value -> ResponseEntity.ok(converterExplicadorParaDTO.converter(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{explicadorId}")
    public ResponseEntity<ExplicadorResponseDTO> adicionaDisponibilidade(@PathVariable Long explicadorId, @RequestBody DisponibilidadeCreateDTO disponibilidade){
        Optional<Explicador> optionalExplicador=explicadorService.adicionaDisponibilidade(explicadorId,disponibilidade.converter());
        return optionalExplicador.map(explicador -> ResponseEntity.ok(converterExplicadorParaDTO.converter(explicador))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
