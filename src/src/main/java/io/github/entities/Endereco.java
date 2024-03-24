package io.github.entities;

import io.github.enums.SiglaEstado;

public class Endereco {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private SiglaEstado siglaEstado;

    public Endereco() {

    }

    public Endereco(String rua, String numero, String bairro, String cidade, SiglaEstado siglaEstado) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.siglaEstado = siglaEstado;
    }
    

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public SiglaEstado getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(SiglaEstado siglaEstado) {
        this.siglaEstado = siglaEstado;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Endereco{" + "rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade + ", siglaEstado=" + siglaEstado + '}';
    }

}
