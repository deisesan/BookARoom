package io.github.util;

import java.time.LocalDate;
import java.time.LocalTime;

public class DataReserva {

    private LocalDate dataAlocacao;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    public DataReserva() {
    }

    public DataReserva(LocalDate dataAlocacao, LocalTime horaInicio, LocalTime horaFim) {
        this.dataAlocacao = dataAlocacao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public LocalDate getDataAlocacao() {
        return dataAlocacao;
    }

    public void setDataAlocacao(LocalDate dataAlocacao) {
        this.dataAlocacao = dataAlocacao;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }
    
    /*public boolean isDataHoraValida() {
        LocalDate hoje = LocalDate.now();
        LocalTime agora = LocalTime.now();

        if (dataAlocacao.isBefore(hoje)) {
            return false;
        }

        if (dataAlocacao.equals(hoje) && horaInicio.isBefore(agora)) {
            return false;
        }

        return true;
    }*/
    public void validarDataHora() throws IllegalArgumentException {
        LocalDate hoje = LocalDate.now();
        LocalTime agora = LocalTime.now();

        // Verifica se a data da reserva é no futuro em relação ao dia atual
        if (dataAlocacao.isBefore(hoje)) {
            throw new IllegalArgumentException("A data da reserva nao pode ser no passado.");
        }

        // Se a data da reserva for igual ao dia atual, verifica se o horário de início ainda não passou
        if (dataAlocacao.equals(hoje) && horaInicio.isBefore(agora)) {
            throw new IllegalArgumentException("O horario de inicio da reserva ja passou.");
        }

        // Verifica se a hora de término é maior que a hora de início
        if (horaFim.isBefore(horaInicio)) {
            throw new IllegalArgumentException("A hora de termino deve ser posterior a hora de inicio.");
        }
    }
}
