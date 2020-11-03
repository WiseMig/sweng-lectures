package pt.ufp.info.esof.lectures.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL)
    private List<Cadeira> cadeiras=new ArrayList<>();
    @ManyToOne
    private Faculdade faculdade;

    public void adicionaCadeira(Cadeira cadeira){
        if(!this.cadeiras.contains(cadeira)){
            cadeiras.add(cadeira);
            cadeira.setCurso(this);
        }
    }
}
