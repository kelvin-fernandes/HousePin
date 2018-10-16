package org.jacared.housepin.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "endereco_id")
    private Integer id;

    @Column(name = "cep", length = 9)
//    @NotEmpty(message = "Insira o seu CEP.")
//    @NotBlank(message = "Insira um CEP válido.")
    private String cep;

    @Column(name = "logradouro", nullable = false, length = 100)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "complemento", length = 100)
    private String complemento;

    @Column(name = "bairro", nullable = false, length = 100)
    private String bairro;

    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @Column(name = "uf", nullable = false, length = 2)
    private String uf;

    @Column(name = "latitude")
    @Transient
    private Double latitude;

    @Column(name = "longitude")
    @Transient
    private Double longitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return String.format("%s, %d - %s - %s/%s", logradouro, numero, bairro, cidade, uf);
    }
}
