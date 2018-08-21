package org.jacared.housepin.utils;

public enum EnumFinalidade {

    VENDA("Venda"),

    ALUGUEL("Aluguel");

    private final String name;

    private EnumFinalidade(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString(){
        return this.name;
    }
}
