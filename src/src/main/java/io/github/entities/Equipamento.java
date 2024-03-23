package io.github.entities;


public class Equipamento {
    
    private String nome;
    private String patrimonio;

    public Equipamento() {
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Equipamento(String nome, String patrimonio) {
        this.nome = nome;
        this.patrimonio = patrimonio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }
     //</editor-fold>
    
}
