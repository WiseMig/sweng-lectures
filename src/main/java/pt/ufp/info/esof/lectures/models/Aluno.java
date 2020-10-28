package pt.ufp.info.esof.lectures.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Aluno extends Utilizador{
    @Id
    private Long id;
    @OneToMany(mappedBy = "aluno")
    private final List<Explicacao> explicacoes=new ArrayList<>();
}
