package pt.ufp.info.esof.lectures.dtos.conversores;

import pt.ufp.info.esof.lectures.dtos.DisponibilidadeCreateDTO;
import pt.ufp.info.esof.lectures.dtos.ExplicacaoDTO;
import pt.ufp.info.esof.lectures.dtos.ExplicadorResponseDTO;
import pt.ufp.info.esof.lectures.models.Cadeira;
import pt.ufp.info.esof.lectures.models.Explicador;

import java.util.stream.Collectors;

public class ConverterExplicadorParaDTO implements Conversor<ExplicadorResponseDTO,Explicador>{

    @Override
    public ExplicadorResponseDTO converter(Explicador explicador) {
        ExplicadorResponseDTO responseDTO=new ExplicadorResponseDTO();
        responseDTO.setEmail(explicador.getEmail());
        responseDTO.setCadeiras(explicador.getCadeiras().stream().map(Cadeira::getNome).collect(Collectors.toList()));
        responseDTO.setDisponibilidades(explicador.getDisponibilidades().stream().map(disponibilidade -> {
            DisponibilidadeCreateDTO disponibilidadeDTO=new DisponibilidadeCreateDTO();
            disponibilidadeDTO.setDia(disponibilidade.getDiaDaSemana());
            disponibilidadeDTO.setHoraInicio(disponibilidade.getHoraInicio());
            disponibilidadeDTO.setHoraFim(disponibilidade.getHoraFim());
            return disponibilidadeDTO;
        }).collect(Collectors.toList()));
        responseDTO.setExplicacoes(explicador.getExplicacoes().stream().map(explicacao -> {
            ExplicacaoDTO explicacaoDTO=new ExplicacaoDTO();
            explicacaoDTO.setAlunoNome(explicacao.getAluno().getNome());
            explicacaoDTO.setExplicadorNome(explicacao.getExplicador().getNome());
            return explicacaoDTO;
        }).collect(Collectors.toList()));
        return responseDTO;
    }
}
