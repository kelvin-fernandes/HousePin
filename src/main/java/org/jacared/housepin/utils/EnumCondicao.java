package org.jacared.housepin.utils;

public enum EnumCondicao {

    NOVO("Novo"),

    USADO("Usado");

    private final String name;

    private EnumCondicao(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString(){
        return this.name;
    }
}
