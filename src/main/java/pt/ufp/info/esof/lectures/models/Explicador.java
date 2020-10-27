package pt.ufp.info.esof.lectures.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Explicador extends Utilizador{
    private String email;
    private final List<Cadeira> cadeiras=new ArrayList<>();
    private final List<Disponibilidade> disponibilidades=new ArrayList<>();
    private final List<Explicacao> explicacoes=new ArrayList<>();

    public Explicacao adicionarExplicacao(Explicacao explicacao){
        if(estaDisponivel(explicacao)&&!temMarcacaoPrevia(explicacao)){
            this.explicacoes.add(explicacao);
            explicacao.setExplicador(this);
            return explicacao;
        }
        return null;
    }

    private boolean estaDisponivel(Explicacao explicacao){
        for(Disponibilidade disponibilidade:disponibilidades){
            if(disponibilidade.estaDisponivel(explicacao)){
                return true;
            }
        }
        return false;
    }

    private boolean temMarcacaoPrevia(Explicacao explicacao){
        for(Explicacao explicacaoExistente:explicacoes){
            if(explicacaoExistente.temMarcacaoPrevia(explicacao)){
                return true;
            }
        }
        return false;
    }

    public void adicionaDisponibilidade(Disponibilidade disponibilidade){
        if(!this.disponibilidades.contains(disponibilidade)){
            this.disponibilidades.add(disponibilidade);
            disponibilidade.setExplicador(this);
        }
    }

}
