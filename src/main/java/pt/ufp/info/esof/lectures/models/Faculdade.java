package pt.ufp.info.esof.lectures.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Faculdade {
    @Id
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "faculdade")
    private List<Curso> cursos=new ArrayList<>();

}
