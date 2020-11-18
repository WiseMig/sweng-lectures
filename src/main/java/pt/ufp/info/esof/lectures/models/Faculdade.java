package pt.ufp.info.esof.lectures.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Faculdade {
    private Long id;
    private String nome;
    private List<Curso> cursos=new ArrayList<>();

    public void adicionaCurso(Curso curso){
        if(!this.cursos.contains(curso)){
            cursos.add(curso);
            curso.setFaculdade(this);
        }
    }

}
