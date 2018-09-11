package org.jacared.housepin.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Anunciante")
public class Anunciante extends Usuario implements Serializable {
//    @NotEmpty(message = "Insira o seu CRECI.")
//    @NotBlank(message = "Insira um CRECI válido.")
    @Column(name = "creci", unique = true, length = 20)
    private String creci;

    @Column(name = "isImobiliaria")
    private Boolean isImobiliaria;

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

    public Boolean getImobiliaria() {
        return isImobiliaria;
    }

    public void setImobiliaria(Boolean imobiliaria) {
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
