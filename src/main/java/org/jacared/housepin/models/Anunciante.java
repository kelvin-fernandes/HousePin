package org.jacared.housepin.models;

import org.jacared.housepin.utils.EnumLogico;
import org.jacared.housepin.utils.EnumTipoUsuario;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.constraints.*;

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
