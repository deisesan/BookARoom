package io.github.cargo;

import io.github.campus.Campus;

public class Funcionario {
    
    private String nome;
    private String cargo;
    private int ramal;
    
    //Atributos de Relação
    private Campus campuss;

    public Funcionario() {
    }

    
    public Funcionario(String nome, String cargo, int ramal) {
        this.nome = nome;
        this.cargo = cargo;
        this.ramal = ramal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getRamal() {
        return ramal;
    }

    public void setRamal(int ramal) {
        this.ramal = ramal;
    }
    
    
}
