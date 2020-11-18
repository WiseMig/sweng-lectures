package pt.ufp.info.esof.lectures.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Explicador extends Utilizador{
    private Long id;
    private String email;
    private List<Cadeira> cadeiras=new ArrayList<>();
    private List<Disponibilidade> disponibilidades=new ArrayList<>();
    private List<Explicacao> explicacoes=new ArrayList<>();

    public Explicacao adicionarExplicacao(Explicacao explicacao){
        if(estaDisponivel(explicacao)&&!temMarcacaoPrevia(explicacao)){
            this.explicacoes.add(explicacao);
            explicacao.setExplicador(this);
            return explicacao;
        }
        return null;
    }

    public void adicionaCadeira(Cadeira cadeira){
        if(!this.cadeiras.contains(cadeira)){
            this.cadeiras.add(cadeira);
            cadeira.adicionaExplicador(this);
        }
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
