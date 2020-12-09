package pt.ufp.info.esof.lectures.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.ufp.info.esof.lectures.dtos.DTOStaticFactory;
import pt.ufp.info.esof.lectures.dtos.MarcarAtendimentoDTO;
import pt.ufp.info.esof.lectures.models.Explicacao;
import pt.ufp.info.esof.lectures.services.ExplicacaoService;

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
