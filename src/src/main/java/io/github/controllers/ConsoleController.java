package io.github.controllers;

import io.github.entities.Equipamento;
import io.github.entities.Funcionario;
import io.github.entities.SalaReuniao;
import io.github.enums.Assunto;
import io.github.enums.Periodo;
import io.github.reserva.Reserva;
import io.github.util.DataReserva;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleController {

    private final CampusController campusController;
    private final Scanner sc;
    private final Funcionario funcionarioLogado;

    public ConsoleController(CampusController campusController) {
        this.campusController = campusController;
        this.sc = new Scanner(System.in);
        // Mudar Funcionário
        this.funcionarioLogado = campusController.getCampusLista().get(0).getFuncionarios().get(0);
    }

    public void iniciar() {
        try {
            String opcao;
            System.out.println("#######################################################");
            System.out.println("################### Bem Vindo(a)!!! ###################");
            this.imprimirUsuario(); // Imprimir informações do usuário logado
            this.campusController.setCampus(this.funcionarioLogado.getCampus()); // Define o campus do funcionário logado
            while (true) {
                this.opcoesMenuPrincipal();
                System.out.print("--> Informe opcao: ");
                opcao = this.sc.nextLine();
                if (opcao.equals("0")) {
                    break;
                } else if (opcao.equals("1")) {
                    this.menuRealizarReserva();
                } else if (opcao.equals("2")) {
                    this.menuVisualizarReservas();
                }
            }

            System.out.println("################  Sessao finalizada.  #################");
            System.out.println("#######################################################");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a execução: " + e.getLocalizedMessage());
        }
    }

    private void imprimirUsuario() {
        System.out.println("");
        System.out.println("Funcionario Logado: " + this.funcionarioLogado.getNome());
        System.out.println("Funcionario do Campus: " + this.funcionarioLogado.getCampus().getNome());
        System.out.println("");
    }

    private void opcoesMenuPrincipal() {
        System.out.println("\nSeleciona opcao desejada: ");
        System.out.println("1 - Reservar Sala.");
        System.out.println("2 - Visualizar Ocupacao das salas.");
        System.out.println("0 - Finalizar Sessao.");
    }

    private void opcoesMenuVisualizarReservas() {
        System.out.println("\nSeleciona opcao desejada: ");
        System.out.println("1 - Visualizar reservas do dia.");
        System.out.println("2 - Visualizar reservas da semana.");
        System.out.println("3 - Visualizar reservas do mes.");
        System.out.println("0 - Retornar menu principal.");

    }

    private Assunto escolherAssunto() {
        while (true) {
            for (Assunto assunto : Assunto.values()) {
                System.out.println("[ " + (assunto.ordinal() + 1) + " ] - " + assunto.getDescription());
            }

            System.out.print("--> Informe o numero correspondente ao assunto desejado: ");
            int opcaoAssunto = this.sc.nextInt();

            // Verifica se a opção está dentro do intervalo válido
            if (opcaoAssunto >= 1 && opcaoAssunto <= Assunto.values().length) {
                return Assunto.values()[opcaoAssunto - 1];
            } else {
                System.out.println("Opcao invalida. Por favor, escolha uma opcao valida.");
            }
        }
    }

    private void menuRealizarReserva() {
        DataReserva dataReserva = new DataReserva();
        System.out.println("Para realizar reserva, informe os dados a seguir.");
        System.out.println("--> Informe a data que deseja realizar a reserva no formato (YYYY-MM-DD): ");
        String dataInput = this.sc.nextLine();
        System.out.println("--> Informe a hora de inicio no formato (HH:MM): ");
        String horaInicioInput = this.sc.nextLine();
        System.out.println("--> Informe a hora de fim no formato (HH:MM): ");
        String horaFimInput = this.sc.nextLine();

        try {
            dataReserva.setDataAlocacao(LocalDate.parse(dataInput));
            dataReserva.setHoraInicio(LocalTime.parse(horaInicioInput));
            dataReserva.setHoraFim(LocalTime.parse(horaFimInput));

            if (dataReserva.validarDataHora()) {
                System.out.println("Data informada: " + dataReserva.getDataAlocacao());
                System.out.println("Horario informado: " + dataReserva.getHoraInicio() + "h  - " + dataReserva.getHoraFim() + "h");

                if (this.campusController.verificarConflitoReservaFuncionario(dataReserva, funcionarioLogado) == false) {

                    List<SalaReuniao> salasLivres = campusController.obterSalasLivres(dataReserva);

                    // Verificar se a lista de salas livres está vazia
                    if (salasLivres.isEmpty()) {
                        throw new NullPointerException("Nao ha salas disponíveis para reserva na data e horario informados!!!");
                    }

                    System.out.println("Salas disponiveis para reserva: ");
                    imprimirLista(salasLivres);
                    System.out.print("--> Informe o numero da lista correspondente a sala que deseja reservar: ");
                    int numSala = this.sc.nextInt();

                    this.sc.nextLine();  // Limpar o buffer de entrada

                    // Verificar se o número da sala é válido
                    if (numSala >= 0 && numSala < salasLivres.size()) {
                        SalaReuniao salaSelecionada = salasLivres.get(numSala);
                        System.out.println("Sala selecionada: " + salaSelecionada.toString());

                        System.out.println("--> Informe o assunto da reserva: ");

                        Assunto assunto = this.escolherAssunto();
                        this.sc.nextLine();  // Limpar o buffer de entrada

                        if (this.campusController.validarReservaParaAula(assunto, dataReserva)) {

                            System.out.println("--> Deseja adicionar equipamentos a reserva? (s/n)");
                            String adicionarEquipamentos = this.sc.nextLine();
                            List<Equipamento> equipamentosSelecionados = null;
                            if (adicionarEquipamentos.equals("s")) {
                                equipamentosSelecionados = this.menuAdcionarEquipamentos(dataReserva);
                            } else {
                                equipamentosSelecionados = new ArrayList<>();// Atribuindo uma lista vazia
                            }

                            Reserva reservaCriada = campusController.reservarSala(dataReserva, assunto, salaSelecionada, this.funcionarioLogado, equipamentosSelecionados);

                            if (reservaCriada != null) {
                                System.out.println("Reserva realizada com sucesso!!!");
                                this.imprimirReserva(reservaCriada);
                            }
                        } else {
                            System.out.println("\n!!!!!!!!!! ALERTA !!!!!!!!!!\nReservas de salas para reuniao ou palestra nao podem ser feitas entre 7:20h e 18:40h, salas nesse horario sao disponiveis apenas para reserva de aulas.\n");
                        }

                    } else {
                        throw new IllegalArgumentException("Numero de sala invalido!");
                    }

                } else {
                    System.out.println("CONFLITO: voce ja possui uma reserva ativa de sala no horario informado.");
                }
            }

        } catch (DateTimeParseException e) {
            System.out.println("Formato de data ou hora inválido. Certifique-se de usar o formato correto.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void imprimirReserva(Reserva reserva) {
        // Verificar se a reserva é nula
        if (reserva == null) {
            throw new NullPointerException("A reserva nao pode ser nula!");
        }

        System.out.println("--------------- RESERVA ---------------");
        if (reserva.getDataAlocacao() != null) {
            System.out.println("-- Data: " + reserva.getDataAlocacao());
        } else {
            System.out.println("-- Data: [Data nao especificada]");
        }

        if (reserva.getHoraInicio() != null) {
            System.out.println("-- Horario de inicio: " + reserva.getHoraInicio());
        } else {
            System.out.println("-- Horario de inicio: [Horario nao especificado]");
        }

        if (reserva.getHoraFim() != null) {
            System.out.println("-- Horario de fim: " + reserva.getHoraFim());
        } else {
            System.out.println("-- Horario de fim: [Horário nao especificado]");
        }

        if (reserva.getAssunto() != null && !reserva.getAssunto().getDescription().isEmpty()) {
            System.out.println("-- Assunto: " + reserva.getAssunto());
        } else {
            System.out.println("-- Assunto: [Assunto nao especificado]");
        }

        if (reserva.getFuncionario() != null && reserva.getFuncionario().getNome() != null) {
            System.out.println("-- Funcionario: " + reserva.getFuncionario().getNome());
        } else {
            System.out.println("-- Funcionario: [Funcionario nao especificado]");
        }

        if (reserva.getSala() != null && reserva.getSala().getNumero() != null) {
            System.out.println("-- Sala: " + reserva.getSala().getNumero());
        } else {
            System.out.println("-- Sala: [Sala nao especificada]");
        }

        if (reserva.getSala() != null && reserva.getSala().getPredio() != null && reserva.getSala().getPredio().getNome() != null) {
            System.out.println("-- Predio: " + reserva.getSala().getPredio().getNome());
        } else {
            System.out.println("-- Predio: [Predio nao especificado]");
        }

        if (reserva.getEquipamentos() != null && !reserva.getEquipamentos().isEmpty()) {
            System.out.println("-- Equipamentos adicionados a reserva: ");
            this.imprimirLista(reserva.getEquipamentos());
        } else {
            System.out.println("-- Equipamentos reservados: [Nenhum equipamento reservado]");
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
            System.out.print("--> Informe opcao: ");
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
            Map<Boolean, List<Reserva>> reservasDoDiaAgrupadas = campusController.obterReservasDeCampusPorPeriodo(Periodo.DIA);

            if (reservasDoDiaAgrupadas.isEmpty()) {
                System.out.println("Nao ha reservas para o dia de hoje.");
            } else {
                System.out.println("\n----- > LISTA DE RESERVAS DO DIA < ----- ");
                for (Map.Entry<Boolean, List<Reserva>> entry : reservasDoDiaAgrupadas.entrySet()) {
                    boolean ativa = entry.getKey();
                    List<Reserva> reservas = entry.getValue();

                    if (ativa) {
                        System.out.println("\n--> Reservas ativas:");
                    } else {
                        System.out.println("\n--> Reservas inativas:");
                    }

                    this.imprimirListaReservas(reservas);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Lista de reservas de campus está nula.");
        }
    }

    private void visualizarReservasDaSemana() {
        try {
            Map<Boolean, List<Reserva>> reservasDaSemanaAgrupadas = campusController.obterReservasDeCampusPorPeriodo(Periodo.SEMANA);

            if (reservasDaSemanaAgrupadas.isEmpty()) {
                System.out.println("Nao ha reservas para nesta semana.");
            } else {
               System.out.println("\n----- > LISTA DE RESERVAS DA SEMANA < ----- ");
                for (Map.Entry<Boolean, List<Reserva>> entry : reservasDaSemanaAgrupadas.entrySet()) {
                    boolean ativa = entry.getKey();
                    List<Reserva> reservas = entry.getValue();

                    if (ativa) {
                        System.out.println("\n--> Reservas ativas:");
                    } else {
                        System.out.println("\n--> Reservas inativas:");
                    }

                    this.imprimirListaReservas(reservas);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Lista de reservas de campus está nula.");
        }
    }

    private void visualizarReservasDoMes() {
        try {
            Map<Boolean, List<Reserva>> reservasDoMesAgrupadas = campusController.obterReservasDeCampusPorPeriodo(Periodo.MES);

            if (reservasDoMesAgrupadas.isEmpty()) {
                System.out.println("Nao ha reservas para este mes.");
            } else {
                System.out.println("\n----- > LISTA DE RESERVAS DO MES < ----- ");
                for (Map.Entry<Boolean, List<Reserva>> entry : reservasDoMesAgrupadas.entrySet()) {
                    boolean ativa = entry.getKey();
                    List<Reserva> reservas = entry.getValue();

                    if (ativa) {
                        System.out.println("\n--> Reservas ativas:");
                    } else {
                        System.out.println("\n--> Reservas inativas:");
                    }

                    this.imprimirListaReservas(reservas);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Lista de reservas de campus está nula.");
        }
    }

    private <T> void imprimirLista(List<T> lista) {

        if (lista == null || lista.isEmpty()) {
            System.out.println("LISTA VAZIA.");
        } else {
            int i = 0;
            for (T item : lista) {
                System.out.println("[ " + i + " ]" + " - " + item.toString());
                i++;
            }
        }
    }

    private void imprimirListaReservas(List<Reserva> reservas) {

        if (reservas == null || reservas.isEmpty()) {
            System.out.println("LISTA VAZIA.");
        } else {
            for (Reserva reserva : reservas) {
                System.out.println("  -------- Reserva -------- ");
                System.out.println("  | Data: " + reserva.getDataAlocacao());
                System.out.println("  | Horario: " + reserva.getHoraInicio() + "h - " + reserva.getHoraFim() + "h");
                System.out.println("  | Assunto: " + reserva.getAssunto().getDescription());
                System.out.println("  | Sala: " + reserva.getSala().getNumero());
                System.out.println("  | Predio: " + reserva.getSala().getPredio().getNome());
                System.out.println("  | Funcionario: " + reserva.getFuncionario().getNome());
                System.out.println("");

            }
        }
    }

}
