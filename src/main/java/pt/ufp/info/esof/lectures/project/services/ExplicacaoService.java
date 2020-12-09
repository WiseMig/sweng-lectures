package pt.ufp.info.esof.lectures.project.services;

import pt.ufp.info.esof.lectures.project.models.Explicacao;

import java.util.Optional;

public interface ExplicacaoService {
    Optional<Explicacao> marcarAtendimento(Explicacao explicacao);
}
