package io.github.reserva;

import io.github.cargo.Funcionario;
import io.github.equipamento.Equipamento;
import io.github.reuniao.SalaReuniao;
import java.time.LocalDate;

public class Reserva {
    
   private LocalDate dataAlocacao;
   private LocalDate horaInicio;
   private LocalDate horaFim;
   private String assunto;
   
   //Atributos das relações
   private SalaReuniao sala;
   private Funcionario func;
   
   private boolean disponivel;

    public Reserva() {
    }

    public Reserva(LocalDate dataAlocacao, LocalDate horaInicio, LocalDate horaFim, String assunto) {
        this.dataAlocacao = dataAlocacao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.assunto = assunto;
    }

    public LocalDate getDataAlocacao() {
        return dataAlocacao;
    }

    public void setDataAlocacao(LocalDate dataAlocacao) {
        this.dataAlocacao = dataAlocacao;
    }

    public LocalDate getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDate horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDate getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalDate horaFim) {
        this.horaFim = horaFim;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
   
   
   
}
