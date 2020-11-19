package pt.ufp.info.esof.lectures.models;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Utilizador {
    private String nome;
}
