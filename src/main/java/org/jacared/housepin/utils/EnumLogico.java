package org.jacared.housepin.utils;

public enum EnumLogico {
    ATIVO ("Ativo"),

    INATIVO ("Inativo");

    private final String name;

    private EnumLogico(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString(){
        return this.name;
    }
}
