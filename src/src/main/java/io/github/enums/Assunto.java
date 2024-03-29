package io.github.enums;

public enum Assunto {

    AULA("Aula"),
    PALESTRA("Palestra"),
    REUNIAO("Reuniao");

    private final String descricao;

    Assunto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescription() {
        return descricao;
    }
}
