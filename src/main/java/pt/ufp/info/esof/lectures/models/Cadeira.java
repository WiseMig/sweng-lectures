package pt.ufp.info.esof.lectures.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cadeira {
    @Id
    private Long id;

    private String nome;
    @ManyToOne
    private Curso curso;
    @ManyToMany
    private List<Explicador> explicadores=new ArrayList<>();
}
