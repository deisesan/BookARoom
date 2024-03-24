package io.github.controllers;

import io.github.entities.Campus;
import io.github.enums.Periodo;
import io.github.reserva.Reserva;
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
        System.out.println("#######################################################");
        System.out.println("##################  Bem Vindo(a)!!!  ##################");
        this.selecionarCampus();
        this.menuPrincipal();
        System.out.println("################  Sessao finalizada.  #################");
        System.out.println("#######################################################");
    }

    private void selecionarCampus() {
        int opcao;
        int i = 0;

        System.out.println("Selecione campus que deseja realizar reserva: ");
        for (Campus campus : campusController.getCampusLista()) {
            System.out.println("[" + i + "] " + campus.getNome());
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
                System.out.println("Por favor, informe um numero valido.");

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
            } else if (opcao.equals("2")) {
                this.menuVisualizarReservas();
            }
        }
    }

    private void opcoesMenuPrincipal() {
        System.out.println("Seleciona opcao desejada: ");
        System.out.println("0 - Sair.");
        System.out.println("1 - Reservar Sala.");
        System.out.println("2 - Visualizar Ocupacao das salas.");

    }

    private void opcoesMenuVisualizarReservas() {
        System.out.println("Seleciona opcao desejada: ");
        System.out.println("1 - Visualizar reservas do dia.");
        System.out.println("2 - Visualizar reservas da semana.");
        System.out.println("3 - Visualizar reservas do mes.");
        System.out.println("0 - Retornar menu principal.");

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
