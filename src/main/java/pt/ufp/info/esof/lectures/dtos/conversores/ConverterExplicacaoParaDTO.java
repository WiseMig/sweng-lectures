package pt.ufp.info.esof.lectures.dtos.conversores;

import org.springframework.stereotype.Component;
import pt.ufp.info.esof.lectures.dtos.MarcarAtendimentoDTO;
import pt.ufp.info.esof.lectures.models.Explicacao;

@Component
public class ConverterExplicacaoParaDTO implements Conversor<MarcarAtendimentoDTO, Explicacao>{
    @Override
    public MarcarAtendimentoDTO converter(Explicacao explicacao) {
        MarcarAtendimentoDTO marcarAtendimentoDTO=new MarcarAtendimentoDTO();
        marcarAtendimentoDTO.setHora(explicacao.getHora());
        marcarAtendimentoDTO.setExplicadorId(explicacao.getExplicador().getId());
        marcarAtendimentoDTO.setAlunoId(explicacao.getAluno().getId());
        return marcarAtendimentoDTO;
    }
}
