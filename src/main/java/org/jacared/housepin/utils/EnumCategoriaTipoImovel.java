package org.jacared.housepin.utils;

public enum EnumCategoriaTipoImovel {

    RESIDENCIAL("Residencial"),

    COMERCIAL("Comercial"),

    RURAL("Rural");

    private final String name;

    private EnumCategoriaTipoImovel(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString(){
        return this.name;
    }
}
