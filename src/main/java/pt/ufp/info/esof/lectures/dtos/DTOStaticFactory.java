package pt.ufp.info.esof.lectures.dtos;

import pt.ufp.info.esof.lectures.models.Cadeira;
import pt.ufp.info.esof.lectures.models.Explicacao;
import pt.ufp.info.esof.lectures.models.Explicador;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Fábrica estática para criação de DTO's
 * É implementada em conjunto com o padrão Singleton
 */
public class DTOStaticFactory {

    /**
     *
     * Implementa a lógica necessária para garantir uma única instância da fábrica estática
     */
    private static DTOStaticFactory dtoAbstractFactory;

    private DTOStaticFactory() {
    }

    public static DTOStaticFactory getInstance(){
        if(dtoAbstractFactory==null){
            dtoAbstractFactory=new DTOStaticFactory();
        }
        return dtoAbstractFactory;
    }

    public ExplicacaoDTO explicacaoDTO(Explicacao explicacao){
        return ExplicacaoDTO.builder()
                .alunoNome(explicacao.getAluno().getNome())
                .explicadorNome(explicacao.getExplicador().getNome())
                .hora(explicacao.getHora())
                .build();
    }

    public ExplicadorResponseDTO explicadorResponseDTO(Explicador explicador){
        List<String> cadeiras=explicador.getCadeiras().stream().map(Cadeira::getNome).collect(Collectors.toList());
        List<DisponibilidadeCreateDTO> createDTOList= explicador.getDisponibilidades().stream().map(disponibilidade ->
                DisponibilidadeCreateDTO.builder()
                        .dia(disponibilidade.getDiaDaSemana())
                        .horaInicio(disponibilidade.getHoraInicio())
                        .horaInicio(disponibilidade.getHoraFim()).build()
        ).collect(Collectors.toList());

        List<ExplicacaoDTO> explicadorResponseDTOS= explicador.getExplicacoes().stream().map(explicacao ->
                DTOStaticFactory.getInstance().explicacaoDTO(explicacao)
        ).collect(Collectors.toList());

        return ExplicadorResponseDTO.builder()
                .email(explicador.getEmail())
                .cadeiras(cadeiras)
                .disponibilidades(createDTOList)
                .explicacoes(explicadorResponseDTOS)
                .build();
    }

    public MarcarAtendimentoDTO marcarAtendimentoDTO(Explicacao explicacao) {
        return MarcarAtendimentoDTO
                .builder()
                .alunoId(explicacao.getAluno().getId())
                .explicadorId(explicacao.getExplicador().getId())
                .hora(explicacao.getHora())
                .build();
    }
}
