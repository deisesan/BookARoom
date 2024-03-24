package io.github.entities;

import io.github.entities.Equipamento;
import io.github.reserva.Reserva;


public class EquipamentoReserva {
    private Reserva reserva;
    private Equipamento equi;
    
    private boolean tem;

    public EquipamentoReserva() {
    }

    public EquipamentoReserva(Reserva reserva, Equipamento equi, boolean tem) {
        this.reserva = reserva;
        this.equi = equi;
        this.tem = tem;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Equipamento getEqui() {
        return equi;
    }

    public void setEqui(Equipamento equi) {
        this.equi = equi;
    }

    public boolean isTem() {
        return tem;
    }

    public void setTem(boolean tem) {
        this.tem = tem;
    }
    
    
}
