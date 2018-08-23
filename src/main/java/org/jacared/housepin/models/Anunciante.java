package org.jacared.housepin.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@DiscriminatorValue("Anunciante")
public class Anunciante extends Usuario {
    @NotEmpty(message = "Insira o seu CRECI.")
    @NotBlank(message = "Insira um CRECI válido.")
    @Column(name = "creci", unique = true, length = 20)
    private String creci;

    @Column(name = "isImobiliaria")
    private boolean isImobiliaria;

    @Column(name = "cnpj", length = 18)
    private String cnpj;

    @Column(name = "razao_social")
    private String razaoSocial;

    public String getCreci() {
        return creci;
    }

    public void setCreci(String creci) {
        this.creci = creci;
    }

    public boolean isImobiliaria() {
        return isImobiliaria;
    }

    public void setImobiliaria(boolean imobiliaria) {
        isImobiliaria = imobiliaria;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}
