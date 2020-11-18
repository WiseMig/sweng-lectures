package pt.ufp.info.esof.lectures.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Utilizador{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    private final List<Explicacao> explicacoes=new ArrayList<>();

    public void addExplicacao(Explicacao explicacao){
        if(!this.explicacoes.contains(explicacao)){
            this.explicacoes.add(explicacao);
            explicacao.setAluno(this);
        }
    }
}
