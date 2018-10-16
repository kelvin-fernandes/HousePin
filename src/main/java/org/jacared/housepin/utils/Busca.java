package org.jacared.housepin.utils;

public class Busca {
    private String valor;
    private String tipo;

    public Busca(String valor, String tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
