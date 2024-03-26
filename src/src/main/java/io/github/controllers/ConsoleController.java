package io.github.controllers;

import io.github.entities.Campus;
import io.github.enums.Periodo;
import io.github.reserva.Reserva;
import io.github.util.DataReserva;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleController {

    private CampusController campusController;
    private Scanner sc;

    public ConsoleController(CampusController campusController) {
        this.campusController = campusController;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        try {
            String finalizarSessao = "n";
            System.out.println("#######################################################");
            System.out.println("##################  Bem Vindo(a)!!!  ##################");
            while (true) {
                if (finalizarSessao.equals("n")) {
                    this.selecionarCampus();
                    this.menuPrincipal();
                } else {
                    break;
                }
                System.out.println("Finalizar sessao? [s/n]");
                finalizarSessao = this.sc.nextLine();
            }

            System.out.println("################  Sessao finalizada.  #################");
            System.out.println("#######################################################");
        } catch (Exception e) {
            System.err.println("Ocorreu um erro durante a execução:");
        }
    }

    private void selecionarCampus() {
        int opcao;
        int i = 0;

        System.out.println("Selecione campus que deseja realizar reserva: ");
        for (Campus campus : campusController.getCampusLista()) {
            System.out.println("[ " + i + " ] " + " - " + campus.getNome());
            i++;
        }

        while (true) {
            try {
                System.out.println("Informe campus: ");
                opcao = this.sc.nextInt();

                if (opcao < 0 || opcao >= campusController.getCampusLista().size()) {
                    throw new IllegalArgumentException("Opcao invalida.");
                } else {
                    campusController.setCampus(campusController.getCampusLista().get(opcao));
                    System.out.println("Campus Selecionado: " + campusController.getCampus().getNome());
                    break;
                }
            } catch (InputMismatchException e) {
                this.sc.nextLine();
                System.out.println("Por favor, informe uma opcao valida.");

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void menuPrincipal() {

        String opcao;
        this.sc.nextLine();
        while (true) {
            this.opcoesMenuPrincipal();
            System.out.println("Informe opcao: ");
            opcao = this.sc.nextLine();
            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                this.menuRealizarReserva();
            } else if (opcao.equals("2")) {
                this.menuVisualizarReservas();
            }
        }
    }

    private void opcoesMenuPrincipal() {
        System.out.println("Seleciona opcao desejada: ");
        System.out.println("1 - Reservar Sala.");
        System.out.println("2 - Visualizar Ocupacao das salas.");
        System.out.println("0 - Sair de menu principal.");

    }

    private void opcoesMenuVisualizarReservas() {
        System.out.println("Seleciona opcao desejada: ");
        System.out.println("1 - Visualizar reservas do dia.");
        System.out.println("2 - Visualizar reservas da semana.");
        System.out.println("3 - Visualizar reservas do mes.");
        System.out.println("0 - Retornar menu principal.");

    }

    private void menuRealizarReserva() {

        DataReserva dataReserva = new DataReserva();
        System.out.println("Para realizar reserva informe dados a seguir.");
        System.out.println("Informe data que deseja realizar reserva no formato (YYYY-MM-DD): ");
        String dataInput = this.sc.nextLine();
        System.out.println("Informe hora de inicio no formato (HH:MM): ");
        String horaInicioInput = this.sc.nextLine();
        System.out.println("Informe hora de fim no formato (HH:MM): ");
        String horaFimInput = this.sc.nextLine();

        try {
            dataReserva.setDataAlocacao(LocalDate.parse(dataInput));
            dataReserva.setHoraInicio(LocalTime.parse(horaInicioInput));
            dataReserva.setHoraFim(LocalTime.parse(horaFimInput));
            dataReserva.validarDataHora();
            System.out.println("Data e hora da reserva sao validas.");
            System.out.println("Data informada: " + dataReserva.getDataAlocacao());
            System.out.println("Horario informado: " + dataReserva.getHoraInicio() + "h  - " + dataReserva.getHoraFim()+"h");
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data ou hora invalido. Certifique-se de usar o formato correto.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        /*Continuar aqui*/
    }

    private void menuVisualizarReservas() {
        String opcao;

        while (true) {
            this.opcoesMenuVisualizarReservas();
            System.out.println("Informe opcao: ");
            opcao = this.sc.nextLine();

            if (opcao.equals("1")) {
                visualizarReservasDoDia();
            } else if (opcao.equals("2")) {
                visualizarReservasDaSemana();
            } else if (opcao.equals("3")) {
                visualizarReservasDoMes();
            } else if (opcao.equals("0")) {
                break; // sair do loop
            } else {
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    private void visualizarReservasDoDia() {
        try {
            List<Reserva> reservas = campusController.obterReservasDeCampusPorPeriodo(Periodo.DIA);

            if (reservas.isEmpty()) {
                System.out.println("Não há reservas para o dia de hoje.");
            } else {
                System.out.println("Lista de Reservas do Dia: ");
                imprimirReservas(reservas);
            }
        } catch (NullPointerException e) {
            System.out.println("Lista de reservas de campus está nula.");
        }
    }

    private void visualizarReservasDaSemana() {
        try {
            List<Reserva> reservas = campusController.obterReservasDeCampusPorPeriodo(Periodo.SEMANA);

            if (reservas.isEmpty()) {
                System.out.println("Não há reservas nesta semana.");
            } else {
                System.out.println("Lista de Reservas da Semana: ");
                imprimirReservas(reservas);
            }
        } catch (NullPointerException e) {
            System.out.println("Lista de reservas de campus está nula.");
        }
    }

    private void visualizarReservasDoMes() {
        try {
            List<Reserva> reservas = campusController.obterReservasDeCampusPorPeriodo(Periodo.MES);

            if (reservas.isEmpty()) {
                System.out.println("Não há reservas para este mes.");
            } else {
                System.out.println("Lista de Reservas do Mês: ");
                imprimirReservas(reservas);
            }
        } catch (NullPointerException e) {
            System.out.println("Lista de reservas de campus está nula.");
        }
    }

    private void imprimirReservas(List<Reserva> reservas) {
        int i = 0;

        for (Reserva reserva : reservas) {
            System.out.println("[ " + i + " ]" + " - " + reserva.toString());
            i++;
        }

    }

}
