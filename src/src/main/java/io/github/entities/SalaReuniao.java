package io.github.entities;

import io.github.reserva.Reserva;
import java.util.ArrayList;
import java.util.List;

public class SalaReuniao {

    private Integer numero;
    private Integer qtdLugares;
    private Predio predio;
    private List<Reserva> reservas = new ArrayList<>();

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

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    //</editor-fold> 
    
    @Override
    public String toString() {
        return "SalaReuniao{" + "numero=" + numero + ", qtdLugares=" + qtdLugares + ", predio=" + predio.getNome() + '}';
    }

}
