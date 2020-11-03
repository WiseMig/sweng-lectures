package pt.ufp.info.esof.lectures.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Faculdade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "faculdade",cascade = CascadeType.ALL)
    private List<Curso> cursos=new ArrayList<>();

    public void addCurso(Curso curso){
        if(!this.cursos.contains(curso)){
            cursos.add(curso);
            curso.setFaculdade(this);
        }
    }

}
