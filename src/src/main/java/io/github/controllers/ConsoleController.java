package io.github.controllers;

import io.github.entities.Equipamento;
import io.github.entities.Funcionario;
import io.github.entities.SalaReuniao;
import io.github.enums.Periodo;
import io.github.reserva.Reserva;
import io.github.util.DataReserva;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
                System.out.println("--> Finalizar sessao? [s/n]");
                finalizarSessao = this.sc.nextLine();
            }

            System.out.println("################  Sessao finalizada.  #################");
            System.out.println("#######################################################");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a execução:" + e.getLocalizedMessage());
        }
    }

    private void selecionarCampus() {
        int opcao;
        int i = 0;

        System.out.println("Selecione campus que deseja realizar reserva: ");
        this.imprimirLista(campusController.getCampusLista());

        while (true) {
            try {
                System.out.println("--> Informe campus: ");
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
        this.sc.nextLine();  //não excluir
        while (true) {
            this.opcoesMenuPrincipal();
            System.out.println("--> Informe opcao: ");
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
        System.out.println("--> Informe data que deseja realizar reserva no formato (YYYY-MM-DD): ");
        String dataInput = this.sc.nextLine();
        System.out.println("--> Informe hora de inicio no formato (HH:MM): ");
        String horaInicioInput = this.sc.nextLine();
        System.out.println("--> Informe hora de fim no formato (HH:MM): ");
        String horaFimInput = this.sc.nextLine();

        try {
            dataReserva.setDataAlocacao(LocalDate.parse(dataInput));
            dataReserva.setHoraInicio(LocalTime.parse(horaInicioInput));
            dataReserva.setHoraFim(LocalTime.parse(horaFimInput));

            if (dataReserva.validarDataHora()) {
                System.out.println("Data e hora da reserva sao validas.");
                System.out.println("Data informada: " + dataReserva.getDataAlocacao());
                System.out.println("Horario informado: " + dataReserva.getHoraInicio() + "h  - " + dataReserva.getHoraFim() + "h");
                List<SalaReuniao> salasLivres = campusController.obterSalasLivres(dataReserva);

                // Verificar se a lista de salas livres está nula
                if (salasLivres == null) {
                    throw new NullPointerException("Nao ha salas disponiveis para reserva em data e horario informado!!!");
                }

                System.out.println("Salas disponiveis para reserva: ");
                imprimirLista(salasLivres);
                System.out.println("--> Informe numero de sala que deseja realizar reserva: ");
                int numSala = this.sc.nextInt();

                this.sc.nextLine();  //não excluir

                // Verificar se o número da sala é válido
                if (numSala >= 0 && numSala < salasLivres.size()) {
                    SalaReuniao salaSelecionada = salasLivres.get(numSala);
                    System.out.println("Sala selecionada: " + salaSelecionada.toString());
                    System.out.println("--> Lista de Funcionarios: ");
                    this.imprimirLista(campusController.getCampus().getFuncionarios());
                    System.out.println("--> Informe funcionario a qual sera criada a reserva: ");
                    int numFuncionario = this.sc.nextInt();

                    this.sc.nextLine();  //não excluir

                    if (numFuncionario >= 0 && numFuncionario < campusController.getCampus().getFuncionarios().size()) {
                        Funcionario funcionarioSelecionado = campusController.getCampus().getFuncionarios().get(numFuncionario);
                        System.out.println("Funcionario selecionado: " + funcionarioSelecionado.toString());

                        System.out.println("--> Informe assunto de reserva: ");
                        String assunto = this.sc.nextLine();

                        if (assunto == null || assunto.isEmpty()) {
                            throw new IllegalArgumentException("Assunto da reserva nao pode ser vazio!");
                        }

                        System.out.println("--> Deseja adicionar equipamentos a reserva: (s/n)");
                        String adicionarEquipamentos = this.sc.nextLine();
                        List<Equipamento> equipamentosSelecionados = null;
                        if (adicionarEquipamentos.equals("s")) {

                            equipamentosSelecionados = this.menuAdcionarEquipamentos(dataReserva);
                        }

                        Reserva reservaCriada = campusController.reservarSala(dataReserva, assunto, salaSelecionada, funcionarioSelecionado, equipamentosSelecionados);

                        if (reservaCriada != null) {
                            System.out.println("Reserva realizada com sucesso!!!");
                            this.imprimirReserva(reservaCriada);
                        }

                    } else {
                        throw new IllegalArgumentException("Numero de funcionario invalido!");
                    }

                } else {
                    throw new IllegalArgumentException("Numero de sala invalido!");
                }

            }
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data ou hora invalido. Certifique-se de usar o formato correto.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void imprimirReserva(Reserva reserva) {
        // Verificar se a reserva é nula
        if (reserva == null) {
            throw new NullPointerException("A reserva não pode ser nula!");
        }

        System.out.println("--------------- Dados de reserva ---------------");
        if (reserva.getDataAlocacao() != null) {
            System.out.println("--> Data: " + reserva.getDataAlocacao());
        } else {
            System.out.println("--> Data: [Data não especificada]");
        }

        if (reserva.getHoraInicio() != null) {
            System.out.println("--> Horario de inicio: " + reserva.getHoraInicio());
        } else {
            System.out.println("--> Horario de inicio: [Horário não especificado]");
        }

        if (reserva.getHoraFim() != null) {
            System.out.println("--> Horario de fim: " + reserva.getHoraFim());
        } else {
            System.out.println("--> Horario de fim: [Horário não especificado]");
        }

        if (reserva.getAssunto() != null && !reserva.getAssunto().isEmpty()) {
            System.out.println("--> Assunto: " + reserva.getAssunto());
        } else {
            System.out.println("--> Assunto: [Assunto não especificado]");
        }

        if (reserva.getFuncionario() != null && reserva.getFuncionario().getNome() != null) {
            System.out.println("--> Funcionario: " + reserva.getFuncionario().getNome());
        } else {
            System.out.println("--> Funcionario: [Funcionário não especificado]");
        }

        if (reserva.getSala() != null && reserva.getSala().getNumero() != null) {
            System.out.println("--> Sala: " + reserva.getSala().getNumero());
        } else {
            System.out.println("--> Sala: [Sala não especificada]");
        }

        if (reserva.getSala() != null && reserva.getSala().getPredio() != null && reserva.getSala().getPredio().getNome() != null) {
            System.out.println("--> Predio: " + reserva.getSala().getPredio().getNome());
        } else {
            System.out.println("--> Predio: [Prédio não especificado]");
        }

        if (reserva.getEquipamentos() != null && !reserva.getEquipamentos().isEmpty()) {
            System.out.println("--> Equipamentos adicionados a reserva: ");
            this.imprimirLista(reserva.getEquipamentos());
        } else {
            System.out.println("--> Equipamentos reservados: [Nenhum equipamento reservado]");
        }
        System.out.println("------------------------------------------------");
    }

    private List<Equipamento> menuAdcionarEquipamentos(DataReserva dataReserva) {
        List<Equipamento> equipamentosSelecionados = new ArrayList<>();
        List<Equipamento> equipamentosDisponiveis = campusController.obterEquipamentosDisponiveis(dataReserva);

        if (equipamentosDisponiveis == null || equipamentosDisponiveis.isEmpty()) {
            throw new NullPointerException("Nao ha equipamentos disponíveis para reserva na data e horário informados!!!");
        }

        System.out.println("Lista de equipamentos disponiveis: ");
        this.imprimirLista(equipamentosDisponiveis);

        for (Equipamento equipamento : equipamentosDisponiveis) {
            System.out.println("Adicionar " + equipamento.getNome() + " a reserva? [s/n]");
            String adicionar = this.sc.nextLine();

            if (adicionar.equals("s")) {
                equipamentosSelecionados.add(equipamento);
            }
        }

        return equipamentosSelecionados;
    }

    private void menuVisualizarReservas() {
        String opcao;

        while (true) {
            this.opcoesMenuVisualizarReservas();
            System.out.println("--> Informe opcao: ");
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
                imprimirLista(reservas);
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
                imprimirLista(reservas);
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
                imprimirLista(reservas);
            }
        } catch (NullPointerException e) {
            System.out.println("Lista de reservas de campus está nula.");
        }
    }

    private <T> void imprimirLista(List<T> lista) {
        int i = 0;
        for (T item : lista) {
            System.out.println("[ " + i + " ]" + " - " + item.toString());
            i++;
        }
    }

}
