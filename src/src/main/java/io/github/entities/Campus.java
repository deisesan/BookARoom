package io.github.entities;

import io.github.reserva.Reserva;
import java.util.List;

public class Campus {

    private String nome;
    private Endereco endereco;

    //Atributos de Relação
    private List<Predio> predios;
    private List<Equipamento> equipamentos;
    private List<Funcionario> funcionarios;

    public Campus() {
    }

    public Campus(String nome, Endereco endereco, List<Predio> predios, List<Equipamento> equipamentos, List<Funcionario> funcionarios) {
        this.nome = nome;
        this.endereco = endereco;
        this.predios = predios;
        this.equipamentos = equipamentos;
        this.funcionarios = funcionarios;
        //this.reservas = reservas;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Predio> getPredios() {
        return predios;
    }

    public void setPredios(List<Predio> predios) {
        this.predios = predios;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    //</editor-fold>

    @Override
    public String toString() {
        return "Campus{" + "nome=" + nome + ", endereco=" + endereco + '}';
    }
    
    
}
