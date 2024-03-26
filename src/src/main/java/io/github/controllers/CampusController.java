package io.github.controllers;

import io.github.entities.Campus;
import io.github.entities.Predio;
import io.github.entities.SalaReuniao;
import io.github.enums.Periodo;
import io.github.reserva.GerenciadorReserva;
import io.github.reserva.Reserva;
import java.util.ArrayList;
import java.util.List;

public class CampusController {

    private List<Campus> campusLista;
    private Campus campus;
    private GerenciadorReserva gerenciadorReserva;

    public CampusController() {
        this.gerenciadorReserva = new GerenciadorReserva();
    }

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
        this.obterTodasReservasDoCampus();
    }

    private void obterTodasReservasDoCampus() {

        List<Reserva> reservas = new ArrayList<>();

        for (Predio predio : this.campus.getPredios()) {
            for (SalaReuniao sala : predio.getSalas()) {

                for (Reserva reserva : sala.getReservas()) {
                    reservas.add(reserva);
                }
            }

        }
        gerenciadorReserva.setReservasCampus(reservas);

    }

    public List<Reserva> obterReservasDeCampusPorPeriodo(Periodo periodo) {
        //List<Reserva> reservas = campus.getReservas();
        //GerenciadorReserva gerenciadorReserva = new GerenciadorReserva(reservas);

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

    /* public List<SalaReuniao> obterSalasLivres(DataReserva dataReserva) {
        List<SalaReuniao> salasLivres = new ArrayList<>();

        // Iterar sobre todas as salas disponíveis no campus
        for (Predio predio : campus.getPredios()) {
            for (SalaReuniao sala : predio.getSalas()) {
                // Verificar se a sala está livre na data e horário especificados
                boolean salaLivre = true;
                for (Reserva reserva : sala.getReservas().) {
                    // Verificar se a reserva está na mesma data da reserva fornecida
                    if (reserva.getDataAlocacao().isEqual(dataReserva.getDataAlocacao())) {
                        // Verificar se há conflito de horário
                        if (dataReserva.getHoraFim().isAfter(reserva.getHoraInicio()) && dataReserva.getHoraInicio().isBefore(reserva.getHoraFim())) {
                            // Se houver conflito de horário, a sala não está livre
                            salaLivre = false;
                            break;
                        }
                    }
                }
                // Se a sala estiver livre, adicioná-la à lista de salas livres
                if (salaLivre) {
                    salasLivres.add(sala);
                }
            }
        }

        return salasLivres;
    }*/
}
