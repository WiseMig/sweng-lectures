package pt.ufp.info.esof.lectures.builder;

import java.util.ArrayList;

public class CadeiraBuilder {

    private Cadeira cadeira;

    public CadeiraBuilder(/*String nome, int ano, int semestre*/) {
        this.cadeira = new Cadeira();
        //this.cadeira.setNome(nome);
        //this.cadeira.setAno(ano);
        //this.cadeira.setSemestre(semestre);
    }

    //fluent design

    public CadeiraBuilder setEcts(int ects){
        this.cadeira.setEcts(ects);
        return this;
    }

    public Cadeira build(){
        return this.cadeira;
    }

    public CadeiraBuilder adicionaAluno(String aluno1) {

        if(this.cadeira.getNomesAluno()==null){
            this.cadeira.setNomesAluno(new ArrayList<>());
        }

        this.cadeira.getNomesAluno().add(aluno1);
        return this;
    }

    public static void main(String[] args) {
        Cadeira cadeira=new CadeiraBuilder().setEcts(5).adicionaAluno("aluno1").adicionaAluno("aluno2").build();
        //Cadeira cadeira= Cadeira.builder().build();

    }
}
