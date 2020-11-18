package pt.ufp.info.esof.lectures.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cadeira {
    private Long id;
    private String nome;
    private Curso curso;
    private List<Explicador> explicadores=new ArrayList<>();

    public void adicionaExplicador(Explicador explicador) {
        if(!this.explicadores.contains(explicador)){
            this.explicadores.add(explicador);
        }
    }
}
