package pt.ufp.info.esof.lectures.project.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Utilizador{
    private Long id;
    private List<Explicacao> explicacoes=new ArrayList<>();

    public void addExplicacao(Explicacao explicacao){
        if(!this.explicacoes.contains(explicacao)){
            this.explicacoes.add(explicacao);
            explicacao.setAluno(this);
        }
    }
}
