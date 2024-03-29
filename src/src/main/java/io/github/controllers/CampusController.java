package io.github.controllers;

import io.github.entities.Campus;
import io.github.entities.Equipamento;
import io.github.entities.Funcionario;
import io.github.entities.Predio;
import io.github.entities.SalaReuniao;
import io.github.enums.Assunto;
import io.github.enums.Periodo;
import io.github.reserva.GerenciadorReserva;
import io.github.reserva.Reserva;
import io.github.util.DataReserva;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public Map<Boolean, List<Reserva>> obterReservasDeCampusPorPeriodo(Periodo periodo) {

        switch (periodo) {
            case DIA -> {
                return gerenciadorReserva.retornarReservasDoDiaAgrupadas();
            }
            case SEMANA -> {
                return gerenciadorReserva.retornarReservasDaSemanaAgrupadas();
            }
            case MES -> {
                return gerenciadorReserva.retornarReservasDoMesAgrupadas();
            }
            default ->
                throw new IllegalArgumentException("Período inválido: " + periodo);
        }
    }

    public List<SalaReuniao> obterSalasLivres(DataReserva dataReserva) {
        List<SalaReuniao> salasLivres = new ArrayList<>();

        for (Predio predio : campus.getPredios()) {
            for (SalaReuniao sala : predio.getSalas()) {
                boolean salaLivre = true;
                // Verifica se a lista de reservas da sala é nula ou vazia
                if (sala.getReservas().isEmpty()) {
                    salasLivres.add(sala);
                } else {
                    for (Reserva reserva : sala.getReservas()) {
                        // Verifica se a reserva é para o mesmo dia da data de reserva informada
                        if (reserva.getDataAlocacao().equals(dataReserva.getDataAlocacao())) {
                            // Verifica os horários das reservas
                            // Casos de conflito de horário
                            if (reserva.getAtiva()
                                    && (dataReserva.getHoraInicio().isBefore(reserva.getHoraFim())
                                    && dataReserva.getHoraFim().isAfter(reserva.getHoraInicio()))) {
                                salaLivre = false;
                                break;
                            }
                        }
                    }
                }

                // Se a sala ainda estiver livre após a verificação das reservas, adicionar à lista de salas livres
                // Se a sala já estiver na lista, não adiciona de novo
                if (salaLivre && !salasLivres.contains(sala)) {
                    salasLivres.add(sala);
                }
            }
        }

        return salasLivres;
    }

    public List<Equipamento> obterEquipamentosDisponiveis(DataReserva dataReserva) {
        List<Equipamento> equipamentosDisponiveis = new ArrayList<>();
        List<Reserva> reservasCampus = this.gerenciadorReserva.getReservasCampus();

        for (Equipamento equipamento : this.campus.getEquipamentos()) {
            boolean equipamentoLivre = true;

            for (Reserva reserva : reservasCampus) {
                // Verifica se a reserva é para a mesma data
                if (reserva.getDataAlocacao().equals(dataReserva.getDataAlocacao())) {
                    // Verifica se a lista de equipamentos não é nula e se o equipamento está presente nessa reserva
                    if (reserva.getEquipamentos() != null && reserva.getEquipamentos().contains(equipamento)) {
                        // Se a reserva estiver ativa e houver sobreposição de horário com a reserva atual
                        // o equipamento não está disponível
                        if (reserva.getAtiva()
                                && dataReserva.getHoraInicio().isBefore(reserva.getHoraFim())
                                && dataReserva.getHoraFim().isAfter(reserva.getHoraInicio())) {

                            equipamentoLivre = false;
                            break;
                        }
                    }
                }
            }

            // Se o equipamento estiver livre após verificar todas as reservas
            if (equipamentoLivre) {
                equipamentosDisponiveis.add(equipamento);
            }
        }

        return equipamentosDisponiveis;
    }

    public boolean verificarConflitoReservaFuncionario(DataReserva dataReserva, Funcionario funcionario) {
        // Obtém a lista de reservas do campus
        List<Reserva> reservasCampus = this.gerenciadorReserva.getReservasCampus();

        // Verifica cada reserva
        for (Reserva reserva : reservasCampus) {
            // Verifica se a reserva é na mesma data da reserva fornecida e se o funcionário está presente nela
            if (reserva.getDataAlocacao().isEqual(dataReserva.getDataAlocacao()) && reserva.getFuncionario().equals(funcionario)) {
                // Se a reserva está ativa e há sobreposição de horário
                if (reserva.getAtiva() && dataReserva.getHoraInicio().isBefore(reserva.getHoraFim()) && dataReserva.getHoraFim().isAfter(reserva.getHoraInicio())) {
                    // Há um conflito de reserva
                    return true;
                }
            }
        }

        // Não há conflito de reserva para o funcionário na data e hora fornecidas
        return false;
    }

    public Reserva reservarSala(DataReserva dataReserva, Assunto assunto, SalaReuniao sala, Funcionario funcionario, List<Equipamento> equipamentos) {

        Reserva reserva = this.gerenciadorReserva.criarReserva(dataReserva, assunto, sala, funcionario, equipamentos);

        for (Predio predio : this.campus.getPredios()) {
            for (SalaReuniao salaReuniao : predio.getSalas()) {
                if (salaReuniao.equals(sala)) {
                    salaReuniao.getReservas().add(reserva);
                }

            }

        }

        return reserva;
    }

    public boolean validarReservaParaAula(Assunto assunto, DataReserva dataReserva) {
        // Se o assunto não for uma aula e o horário estiver dentro do intervalo restrito,
        // então não é permitido reservar para aula
        if (!assunto.equals(Assunto.AULA)
                && (dataReserva.getHoraInicio().isBefore(LocalTime.of(18, 40))
                && dataReserva.getHoraFim().isAfter(LocalTime.of(7, 20)))) {
            return false;
        }

        // Se não for o caso acima, então a reserva pode ser para aula ou o horário está fora do intervalo restrito
        return true;
    }

}
