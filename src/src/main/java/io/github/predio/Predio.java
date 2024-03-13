package io.github.predio;

import io.github.campus.Campus;
import io.github.reuniao.SalaReuniao;
import java.util.List;


public class Predio {
    
    private String nome;
    
    //Atributos de Relação
    private List<SalaReuniao> salas;
    private Campus campus;
    

    public Predio() {
    }

    public Predio(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
