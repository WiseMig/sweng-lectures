package pt.ufp.info.esof.lectures.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Explicador extends Utilizador{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @ManyToMany(mappedBy = "explicadores")
    private List<Cadeira> cadeiras=new ArrayList<>();
    @OneToMany(mappedBy = "explicador",cascade = CascadeType.ALL)
    private List<Disponibilidade> disponibilidades=new ArrayList<>();
    @OneToMany(mappedBy = "explicador",cascade = CascadeType.ALL)
    private List<Explicacao> explicacoes=new ArrayList<>();

    public void setDisponibilidades(List<Disponibilidade> disponibilidades) {
        for(Disponibilidade disponibilidade:disponibilidades){
            this.disponibilidades.add(disponibilidade);
            disponibilidade.setExplicador(this);
        }
    }

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
