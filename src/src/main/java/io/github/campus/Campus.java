package io.github.campus;

import io.github.predio.Predio;

public class Campus {
    
    private String nome;
    private String endereco;
    
    //Atributos de Relação
    private Predio predios;

    public Campus() {
    }

    public Campus(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
}
