package io.github.data;

import io.github.entities.Campus;
import io.github.entities.Endereco;
import io.github.entities.Equipamento;
import io.github.entities.Funcionario;
import io.github.entities.Predio;
import io.github.entities.SalaReuniao;
import io.github.enums.SiglaEstado;
import io.github.reserva.Reserva;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private static List<Campus> campusList = new ArrayList<Campus>();

    public DataSource() {

    }

    public static List<Campus> getCampusList() {
        return campusList;
    }

    public static void initializeDataSource() {

        List<SalaReuniao> salasLista1 = List.of(
                new SalaReuniao(1, 40),
                new SalaReuniao(2, 40),
                new SalaReuniao(3, 40),
                new SalaReuniao(4, 40),
                new SalaReuniao(5, 40),
                new SalaReuniao(6, 40),
                new SalaReuniao(7, 40),
                new SalaReuniao(8, 40),
                new SalaReuniao(9, 40),
                new SalaReuniao(10, 40)
        );

        List<SalaReuniao> salasLista2 = List.of(
                new SalaReuniao(1, 50),
                new SalaReuniao(2, 50),
                new SalaReuniao(3, 50),
                new SalaReuniao(4, 50),
                new SalaReuniao(5, 50),
                new SalaReuniao(6, 50),
                new SalaReuniao(7, 50),
                new SalaReuniao(8, 50),
                new SalaReuniao(9, 50),
                new SalaReuniao(10, 50)
        );

        List<Predio> prediosLista1 = List.of(
                new Predio("Predio 1", salasLista1),
                new Predio("Predio 2", salasLista2)
        );

        for (SalaReuniao sala : salasLista1) {
            sala.setPredio(prediosLista1.get(0));
        }

        for (SalaReuniao sala : salasLista2) {
            sala.setPredio(prediosLista1.get(1));
        }

        List<Funcionario> funcionariosLista1 = List.of(
                new Funcionario("Walter White", "Professor(a)", "Educação"),
                new Funcionario("Jennifer Honey", "Professor(a)", "Educação"),
                new Funcionario("Charles Xavier", "Professor(a)", "Educação"),
                new Funcionario("Minerva McGonagall", "Professor(a)", "Educação")
        );

        List<Equipamento> equipamentosLista1 = List.of(
                new Equipamento("Projetor A", "1111"),
                new Equipamento("Projetor B", "2222"),
                new Equipamento("Projetor C", "3333"),
                new Equipamento("Notebook Dell", "4444"),
                new Equipamento("Notebook Acer", "5555"),
                new Equipamento("Notebook Asus", "6666")
        );

        List<Reserva> reservasLista1 = List.of(
                new Reserva(LocalDate.of(2024, Month.MARCH, 23), LocalTime.of(11, 00), LocalTime.of(12, 40), "Reuniao Doscentes", salasLista1.get(0), funcionariosLista1.get(0), null),
                new Reserva(LocalDate.of(2024, Month.MARCH, 24), LocalTime.of(13, 20), LocalTime.of(15, 00), "Palestra 1", salasLista1.get(0), funcionariosLista1.get(0), null),
                new Reserva(LocalDate.of(2024, Month.MARCH, 25), LocalTime.of(13, 20), LocalTime.of(15, 00), "Reuniao com discentes", salasLista1.get(9), funcionariosLista1.get(0), null),
                new Reserva(LocalDate.of(2024, Month.MARCH, 26), LocalTime.of(13, 20), LocalTime.of(15, 00), "Reuniao com discentes", salasLista1.get(9), funcionariosLista1.get(0), null),
                new Reserva(LocalDate.of(2024, Month.MARCH, 27), LocalTime.of(13, 20), LocalTime.of(15, 00), "Reuniao com discentes", salasLista1.get(9), funcionariosLista1.get(0), null),
                new Reserva(LocalDate.of(2024, Month.MARCH, 28), LocalTime.of(13, 20), LocalTime.of(15, 00), "Reuniao com discentes", salasLista1.get(9), funcionariosLista1.get(0), null),
                new Reserva(LocalDate.of(2024, Month.APRIL, 6), LocalTime.of(9, 00), LocalTime.of(10, 50), "Palestra 2", salasLista1.get(1), funcionariosLista1.get(1), null),
                new Reserva(LocalDate.of(2024, Month.APRIL, 7), LocalTime.of(7, 20), LocalTime.of(9, 00), "Palestra 3", salasLista1.get(2), funcionariosLista1.get(2), null)
        );

        Endereco endereco1 = new Endereco("Dois", "300", "Village do Lago I", "Montes Claros", SiglaEstado.MG);

        Campus campus1 = new Campus("Campus Montes Claros", endereco1, prediosLista1, equipamentosLista1, funcionariosLista1, reservasLista1);
        campusList.add(campus1);

        List<SalaReuniao> salasLista3 = List.of(
                new SalaReuniao(1, 40),
                new SalaReuniao(2, 40),
                new SalaReuniao(3, 40),
                new SalaReuniao(4, 40),
                new SalaReuniao(5, 40),
                new SalaReuniao(6, 40),
                new SalaReuniao(7, 40),
                new SalaReuniao(8, 40),
                new SalaReuniao(9, 40),
                new SalaReuniao(10, 40)
        );

        List<SalaReuniao> salasLista4 = List.of(
                new SalaReuniao(1, 50),
                new SalaReuniao(2, 50),
                new SalaReuniao(3, 50),
                new SalaReuniao(4, 50),
                new SalaReuniao(5, 50),
                new SalaReuniao(6, 50),
                new SalaReuniao(7, 50),
                new SalaReuniao(8, 50),
                new SalaReuniao(9, 50),
                new SalaReuniao(10, 50)
        );

        List<Predio> prediosLista2 = List.of(
                new Predio("Predio 1", salasLista3),
                new Predio("Predio 2", salasLista4)
        );

        for (SalaReuniao sala : salasLista3) {
            sala.setPredio(prediosLista2.get(0));
        }

        for (SalaReuniao sala : salasLista4) {
            sala.setPredio(prediosLista2.get(1));
        }

        List<Funcionario> funcionariosLista2 = List.of(
                new Funcionario("Dewey Finn", "Professor(a)", "Educação"),
                new Funcionario("John Keating", "Professor(a)", "Educação"),
                new Funcionario("Severus Snape", "Professor(a)", "Educação"),
                new Funcionario("Muriel Stacy", "Professor(a)", "Educação")
        );

        List<Equipamento> equipamentosLista2 = List.of(
                new Equipamento("Projetor D", "1010"),
                new Equipamento("Projetor E", "2020"),
                new Equipamento("Projetor F", "3030"),
                new Equipamento("Notebook Lenovo", "4040"),
                new Equipamento("Notebook Samsung", "5050")
        );

        List<Reserva> reservasLista2 = List.of(
                new Reserva(LocalDate.of(2024, Month.MARCH, 22), LocalTime.of(11, 00), LocalTime.of(12, 40), "Reuniao", salasLista3.get(0), funcionariosLista2.get(3), null),
                new Reserva(LocalDate.of(2024, Month.MARCH, 24), LocalTime.of(13, 20), LocalTime.of(15, 00), "Palestra 1", salasLista3.get(0), funcionariosLista2.get(3), null),
                new Reserva(LocalDate.of(2024, Month.MARCH, 25), LocalTime.of(13, 20), LocalTime.of(15, 00), "Reuniao", salasLista4.get(9), funcionariosLista2.get(0), null),
                new Reserva(LocalDate.of(2024, Month.MARCH, 26), LocalTime.of(13, 20), LocalTime.of(15, 00), "Reuniao", salasLista4.get(9), funcionariosLista2.get(0), null),
                new Reserva(LocalDate.of(2024, Month.MARCH, 27), LocalTime.of(13, 20), LocalTime.of(15, 00), "Reuniao", salasLista4.get(9), funcionariosLista2.get(0), null),
                new Reserva(LocalDate.of(2024, Month.MARCH, 28), LocalTime.of(13, 20), LocalTime.of(15, 00), "Reuniao", salasLista4.get(9), funcionariosLista2.get(0), null),
                new Reserva(LocalDate.of(2024, Month.APRIL, 6), LocalTime.of(9, 00), LocalTime.of(10, 50), "Palestra 2", salasLista3.get(1), funcionariosLista2.get(1), null),
                new Reserva(LocalDate.of(2024, Month.APRIL, 7), LocalTime.of(7, 20), LocalTime.of(9, 00), "Palestra 3", salasLista3.get(2), funcionariosLista2.get(2), null)
        );

        Endereco endereco2 = new Endereco("Av. Humberto Mallard", "1355", "Santos Dumont", "Pirapora", SiglaEstado.MG);

        Campus campus2 = new Campus("Campus Pirapora", endereco2, prediosLista2, equipamentosLista2, funcionariosLista2, reservasLista2);

        campusList.add(campus2);
    }

}
