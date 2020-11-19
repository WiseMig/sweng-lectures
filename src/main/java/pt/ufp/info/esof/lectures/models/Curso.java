package pt.ufp.info.esof.lectures.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Curso {
    private Long id;
    private String nome;
    private List<Cadeira> cadeiras=new ArrayList<>();
    private Faculdade faculdade;

    public void adicionaCadeira(Cadeira cadeira){
        if(!this.cadeiras.contains(cadeira)){
            cadeiras.add(cadeira);
            cadeira.setCurso(this);
        }
    }

}
