package pt.ufp.info.esof.lectures.services;

import pt.ufp.info.esof.lectures.models.Explicacao;

import java.util.Optional;

public interface ExplicacaoService {
    Optional<Explicacao> marcarAtendimento(Explicacao explicacao);
}
