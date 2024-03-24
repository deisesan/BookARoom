package io.github.reserva;

import io.github.entities.Equipamento;
import io.github.entities.Funcionario;
import io.github.entities.SalaReuniao;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Reserva {

    private LocalDate dataAlocacao;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String assunto;

    //Atributos das relações
    private SalaReuniao sala;
    private Funcionario funcionario;
    private List<Equipamento> equipamentos;

    private Boolean ativa;

    public Reserva() {
    }

    public Reserva(LocalDate dataAlocacao, LocalTime horaInicio, LocalTime horaFim, String assunto, SalaReuniao sala, Funcionario funcionario, List<Equipamento> equipamentos) {
        this.dataAlocacao = dataAlocacao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.assunto = assunto;
        this.sala = sala;
        this.funcionario = funcionario;
        this.equipamentos = equipamentos;
        setAtiva();
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

        setAtiva();
        return ativa;
    }

    private void setAtiva() {
        LocalDate hoje = LocalDate.now();
        LocalTime agora = LocalTime.now();

        this.ativa = !(hoje.isAfter(dataAlocacao) || (hoje.isEqual(dataAlocacao) && agora.isAfter(horaFim)));
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    @Override
    public String toString() {
        return "Reserva{" + "dataAlocacao=" + dataAlocacao + ", horaInicio=" + horaInicio + ", horaFim=" + horaFim + ", assunto=" + assunto + ", sala=" + sala + ", funcionario=" + funcionario + ", ativa=" + this.getAtiva() + '}';
    }

}
