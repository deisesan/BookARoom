package io.github.reserva;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorReserva {

    private List<Reserva> reservasCampus;

    public GerenciadorReserva(List<Reserva> reservasCampus) {
        this.reservasCampus = reservasCampus;
    }

    public List<Reserva> retornarListaDeReservasDoDia() {
        List<Reserva> reservasDoDia = new ArrayList<>();

        LocalDate hoje = LocalDate.now();

        for (Reserva reserva : reservasCampus) {
            if (reserva.getDataAlocacao().isEqual(hoje)) {
                reservasDoDia.add(reserva);
            }
        }

        return reservasDoDia;
    }

    public List<Reserva> retornarListaDeReservasDaSemana() {
        List<Reserva> reservasDaSemana = new ArrayList<>();

        LocalDate hoje = LocalDate.now();
        LocalDate inicioDaSemana = hoje.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate fimDaSemana = inicioDaSemana.plusDays(6);

        for (Reserva reserva : reservasCampus) {
            LocalDate dataDaReserva = reserva.getDataAlocacao();
            if (dataDaReserva.isEqual(inicioDaSemana) || (dataDaReserva.isAfter(inicioDaSemana) && dataDaReserva.isBefore(fimDaSemana))) {
                reservasDaSemana.add(reserva);
            }
        }

        return reservasDaSemana;
    }

    public List<Reserva> retornarListaDeReservasDoMes() {
        List<Reserva> reservasDoMes = new ArrayList<>();

        YearMonth mesAtual = YearMonth.now();

        for (Reserva reserva : reservasCampus) {
            YearMonth anoMesReserva = YearMonth.from(reserva.getDataAlocacao());
            if (anoMesReserva.equals(mesAtual)) {
                reservasDoMes.add(reserva);
            }
        }

        return reservasDoMes;
    }
}
