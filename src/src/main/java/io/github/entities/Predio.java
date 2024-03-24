package io.github.entities;

import java.util.List;

public class Predio {

    private String nome;

    //Atributos de Relação
    private List<SalaReuniao> salas;
    private Campus campus;

    public Predio() {
    
    }

    public Predio(String nome, List<SalaReuniao> salas) {
        this.nome = nome;
        this.salas = salas;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<SalaReuniao> getSalas() {
        return salas;
    }

    public void setSalas(List<SalaReuniao> salas) {
        this.salas = salas;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
    //</editor-fold>

}
