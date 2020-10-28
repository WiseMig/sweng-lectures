package pt.ufp.info.esof.lectures.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Curso {
    @Id
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "curso")
    private List<Cadeira> cadeiras=new ArrayList<>();
    @ManyToOne
    private Faculdade faculdade;
}
