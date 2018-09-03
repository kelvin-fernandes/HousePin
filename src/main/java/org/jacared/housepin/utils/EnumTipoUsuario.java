package org.jacared.housepin.utils;

public enum EnumTipoUsuario {

    ANUNCIANTE("Anunciante");

    private final String name;

    private EnumTipoUsuario(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString(){
        return this.name;
    }
}
