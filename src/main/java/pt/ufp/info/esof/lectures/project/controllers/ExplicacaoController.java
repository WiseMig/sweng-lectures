package pt.ufp.info.esof.lectures.project.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ufp.info.esof.lectures.project.dtos.DTOStaticFactory;
import pt.ufp.info.esof.lectures.project.dtos.MarcarAtendimentoDTO;
import pt.ufp.info.esof.lectures.project.models.Explicacao;
import pt.ufp.info.esof.lectures.project.services.ExplicacaoService;

import java.util.Optional;

@Controller
@RequestMapping("/explicacao")
public class ExplicacaoController {
    private final ExplicacaoService explicacaoService;
    private final DTOStaticFactory dtoStaticFactory=DTOStaticFactory.getInstance();

    public ExplicacaoController(ExplicacaoService explicacaoService) {
        this.explicacaoService = explicacaoService;
    }

    @PostMapping
    public ResponseEntity<MarcarAtendimentoDTO> marcaAtendimento(@RequestBody MarcarAtendimentoDTO explicacao){
        Optional<Explicacao> optionalExplicacao=explicacaoService.marcarAtendimento(explicacao.converter());
        return optionalExplicacao.map(value -> ResponseEntity.ok(dtoStaticFactory.marcarAtendimentoDTO(value))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
