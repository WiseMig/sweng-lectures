package pt.ufp.info.esof.lectures.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cadeira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne
    private Curso curso;
    @ManyToMany
    private List<Explicador> explicadores=new ArrayList<>();

    public void adicionaExplicador(Explicador explicador) {
        if(!this.explicadores.contains(explicador)){
            this.explicadores.add(explicador);
        }
    }
}
