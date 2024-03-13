package io.github.reuniao;

public class SalaReuniao {
    
    private int numero;
    private int wtdLugares;

    public SalaReuniao() {
    }

    public SalaReuniao(int numero, int wtdLugares) {
        this.numero = numero;
        this.wtdLugares = wtdLugares;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getWtdLugares() {
        return wtdLugares;
    }

    public void setWtdLugares(int wtdLugares) {
        this.wtdLugares = wtdLugares;
    }
    
    
}
