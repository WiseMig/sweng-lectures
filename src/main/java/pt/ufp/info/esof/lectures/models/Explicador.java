package pt.ufp.info.esof.lectures.models;

import java.util.ArrayList;
import java.util.List;

public class Explicador extends Utilizador{
    private String email;
    private final List<Cadeira> cadeiras=new ArrayList<>();
    private final List<Disponibilidade> disponibilidades=new ArrayList<>();
    private final List<Explicacao> explicacoes=new ArrayList<>();

    public Explicacao adicionarExplicacao(Explicacao explicacao){
        return null;
    }

    private boolean estaDisponivel(Explicacao explicacao){
        return false;
    }

    private boolean temMarcacaoPrevia(Explicacao explicacao){
        return false;
    }

}
