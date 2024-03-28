package io.github.reserva;

import io.github.entities.Equipamento;
import io.github.entities.Funcionario;
import io.github.entities.SalaReuniao;
import io.github.util.DataReserva;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class GerenciadorReserva {

    private List<Reserva> reservasCampus;

    public List<Reserva> getReservasCampus() {
        return reservasCampus;
    }

    public void setReservasCampus(List<Reserva> reservasCampus) {
        this.reservasCampus = reservasCampus;
    }

    public Map<Boolean, List<Reserva>> retornarReservasDoDiaAgrupadas() {
        Map<Boolean, List<Reserva>> reservasAgrupadas = new HashMap<>();
        List<Reserva> reservasAtivas = new ArrayList<>();
        List<Reserva> reservasInativas = new ArrayList<>();

        LocalDate hoje = LocalDate.now();

        for (Reserva reserva : reservasCampus) {
            if (reserva.getDataAlocacao().isEqual(hoje)) {
                if (reserva.getAtiva()) {
                    reservasAtivas.add(reserva);
                } else {
                    reservasInativas.add(reserva);
                }
            }
        }

        reservasAgrupadas.put(true, reservasAtivas);
        reservasAgrupadas.put(false, reservasInativas);

        return reservasAgrupadas;
    }

    public Map<Boolean, List<Reserva>> retornarReservasDaSemanaAgrupadas() {
        Map<Boolean, List<Reserva>> reservasAgrupadas = new HashMap<>();
        List<Reserva> reservasAtivas = new ArrayList<>();
        List<Reserva> reservasInativas = new ArrayList<>();

        LocalDate hoje = LocalDate.now();
        LocalDate inicioDaSemana = hoje.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate fimDaSemana = inicioDaSemana.plusDays(6);

        for (Reserva reserva : reservasCampus) {
            LocalDate dataDaReserva = reserva.getDataAlocacao();
            if (dataDaReserva.isEqual(inicioDaSemana) || (dataDaReserva.isAfter(inicioDaSemana) && dataDaReserva.isBefore(fimDaSemana))) {
                if (reserva.getAtiva()) {
                    reservasAtivas.add(reserva);
                } else {
                    reservasInativas.add(reserva);
                }
            }
        }

        reservasAgrupadas.put(true, reservasAtivas);
        reservasAgrupadas.put(false, reservasInativas);

        return reservasAgrupadas;
    }

    public Map<Boolean, List<Reserva>> retornarReservasDoMesAgrupadas() {
        Map<Boolean, List<Reserva>> reservasAgrupadas = new HashMap<>();
        List<Reserva> reservasAtivas = new ArrayList<>();
        List<Reserva> reservasInativas = new ArrayList<>();

        YearMonth mesAtual = YearMonth.now();

        for (Reserva reserva : reservasCampus) {
            YearMonth anoMesReserva = YearMonth.from(reserva.getDataAlocacao());
            if (anoMesReserva.equals(mesAtual)) {
                if (reserva.getAtiva()) {
                    reservasAtivas.add(reserva);
                } else {
                    reservasInativas.add(reserva);
                }
            }
        }

        reservasAgrupadas.put(true, reservasAtivas);
        reservasAgrupadas.put(false, reservasInativas);

        return reservasAgrupadas;
    }

//    public List<Reserva> retornarListaDeReservasDoDia() {
//        List<Reserva> reservasDoDia = new ArrayList<>();
//
//        LocalDate hoje = LocalDate.now();
//
//        for (Reserva reserva : reservasCampus) {
//            if (reserva.getDataAlocacao().isEqual(hoje)) {
//                reservasDoDia.add(reserva);
//            }
//        }
//
//        return reservasDoDia;
//    }
//
//    public List<Reserva> retornarListaDeReservasDaSemana() {
//        List<Reserva> reservasDaSemana = new ArrayList<>();
//
//        LocalDate hoje = LocalDate.now();
//        LocalDate inicioDaSemana = hoje.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
//        LocalDate fimDaSemana = inicioDaSemana.plusDays(6);
//
//        for (Reserva reserva : reservasCampus) {
//            LocalDate dataDaReserva = reserva.getDataAlocacao();
//            if (dataDaReserva.isEqual(inicioDaSemana) || (dataDaReserva.isAfter(inicioDaSemana) && dataDaReserva.isBefore(fimDaSemana))) {
//                reservasDaSemana.add(reserva);
//            }
//        }
//
//        return reservasDaSemana;
//    }
//
//    public List<Reserva> retornarListaDeReservasDoMes() {
//        List<Reserva> reservasDoMes = new ArrayList<>();
//
//        YearMonth mesAtual = YearMonth.now();
//
//        for (Reserva reserva : reservasCampus) {
//            YearMonth anoMesReserva = YearMonth.from(reserva.getDataAlocacao());
//            if (anoMesReserva.equals(mesAtual)) {
//                reservasDoMes.add(reserva);
//            }
//        }
//
//        return reservasDoMes;
//    }
    public Reserva criarReserva(DataReserva dataReserva, String assunto, SalaReuniao sala, Funcionario funcionario, List<Equipamento> equipamentos) {

        if (dataReserva == null) {
            throw new NullPointerException("O objeto DataReserva não pode ser nulo!");
        }

        if (assunto == null || assunto.isEmpty()) {
            throw new IllegalArgumentException("O assunto da reserva não pode ser nulo ou vazio!");
        }

        if (sala == null) {
            throw new NullPointerException("A sala não pode ser nula!");
        }

        if (funcionario == null) {
            throw new NullPointerException("O funcionário não pode ser nulo!");
        }

        if (equipamentos == null) {
            throw new NullPointerException("A lista de equipamentos não pode ser nula!");
        }

        Reserva reserva = new Reserva(dataReserva.getDataAlocacao(), dataReserva.getHoraInicio(), dataReserva.getHoraFim(), assunto, sala, funcionario, equipamentos);

        this.reservasCampus.add(reserva);

        return reserva;
    }

}
