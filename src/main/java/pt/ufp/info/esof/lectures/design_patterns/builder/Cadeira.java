package pt.ufp.info.esof.lectures.design_patterns.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cadeira {


    private String nome;
    private int ano;
    private int semestre;
    private String curso;
    private int ects;
    private List<String> nomesAluno;


}
