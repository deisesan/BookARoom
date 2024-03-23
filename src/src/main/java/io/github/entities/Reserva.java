package io.github.entities;

import io.github.entities.Funcionario;
import io.github.entities.Equipamento;
import java.time.LocalDate;

public class Reserva {
    
   private LocalDate dataAlocacao;
   private LocalDate horaInicio;
   private LocalDate horaFim;
   private String assunto;
   
   //Atributos das relações
   private SalaReuniao sala;
   private Funcionario funcionario;
   
   private Boolean ativa;

    public Reserva() {
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

    public SalaReuniao getSala() {
        return sala;
    }

    public void setSala(SalaReuniao sala) {
        this.sala = sala;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }
   

   
}
