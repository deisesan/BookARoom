package io.github.entities;

public class SalaReuniao {

    private Integer numero;
    private Integer qtdLugares;

    public SalaReuniao() {

    }

    public SalaReuniao(Integer numero, Integer qtdLugares) {
        this.numero = numero;
        this.qtdLugares = qtdLugares;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getQtdLugares() {
        return qtdLugares;
    }

    public void setQtdLugares(Integer qtdLugares) {
        this.qtdLugares = qtdLugares;
    }
    //</editor-fold> 

    @Override
    public String toString() {
        return "SalaReuniao{" + "numero=" + numero + ", qtdLugares=" + qtdLugares + '}';
    }

}
