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
    private final List<Cadeira> cadeiras=new ArrayList<>();
    @OneToMany(mappedBy = "explicador",cascade = CascadeType.ALL)
    private List<Disponibilidade> disponibilidades=new ArrayList<>();
    @OneToMany(mappedBy = "explicador")
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

    public void setDisponibilidades(List<Disponibilidade> asList) {
        this.disponibilidades=asList;
    }
}
