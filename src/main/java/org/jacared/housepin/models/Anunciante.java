package org.jacared.housepin.models;

import org.jacared.housepin.models.relatorio.Relatorio;
import org.jacared.housepin.utils.EnumFinalidade;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "anunciante")
    @Column()
    private Set<Anuncio> anuncios = new LinkedHashSet<>();

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

    public Set<Anuncio> getAnuncios() { return anuncios; }

    public void setAnuncios(Set<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    @Transactional()
    public Relatorio getRelatorioDeAnunciosDeVendas() {
        return new Relatorio(this.getAnuncios().stream()
                .filter((a) -> a.getFinalidade() == EnumFinalidade.VENDA)
                .collect(Collectors.toList()), EnumFinalidade.VENDA, this);
    }

    @Transactional()
    public Relatorio getRelatorioDeAnunciosDeAluguel() {
        return new Relatorio(this.getAnuncios().stream()
                .filter((a) -> a.getFinalidade() == EnumFinalidade.ALUGUEL)
                .collect(Collectors.toList()), EnumFinalidade.ALUGUEL, this);
    }
}
