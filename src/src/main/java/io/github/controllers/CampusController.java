package io.github.controllers;

import io.github.entities.Campus;
import io.github.entities.SalaReuniao;
import io.github.enums.Periodo;
import io.github.reserva.GerenciadorReserva;
import io.github.reserva.Reserva;
import io.github.util.DataReserva;
import java.util.List;

public class CampusController {

    private List<Campus> campusLista;
    private Campus campus;

    public List<Campus> getCampusLista() {
        return campusLista;
    }

    public void setCampusLista(List<Campus> campusLista) {
        this.campusLista = campusLista;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public List<Reserva> obterReservasDeCampusPorPeriodo(Periodo periodo) {
        List<Reserva> reservas = campus.getReservas();
        GerenciadorReserva gerenciadorReserva = new GerenciadorReserva(reservas);

        switch (periodo) {
            case DIA:
                return gerenciadorReserva.retornarListaDeReservasDoDia();
            case SEMANA:
                return gerenciadorReserva.retornarListaDeReservasDaSemana();
            case MES:
                return gerenciadorReserva.retornarListaDeReservasDoMes();
            default:
                throw new IllegalArgumentException("Período inválido: " + periodo);
        }
    }

    public List<SalaReuniao> obterSalasLivres(DataReserva dataReserva) {

        return null;
    }

}
