package org.jacared.housepin.models;

import org.jacared.housepin.utils.EnumCondicao;
import org.jacared.housepin.utils.EnumFinalidade;
import org.jacared.housepin.utils.EnumLogico;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "anuncio")
public class Anuncio {
    @Id
    @GeneratedValue
    @Column(name = "anuncio_id")
    private int id;

//    @NotNull(message = "Escolha a situação atual do anúncio.")
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    private EnumLogico situacao;

    @Column(name = "data_insercao", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP()")
    private Timestamp dataInsercao;

    @Column(name = "data_expiracao")
    private Date dataExpiracao;

//    @NotEmpty(message = "Insira um valor para o anúncio.")
//    @NotBlank(message = "Insira um valor válido.")
    @Column(name = "valor", nullable = false)
    private double valor;

    @Column(name = "condominio")
    private double condominio;

    @Column(name = "iptu")
    private double iptu;

    @Column(name = "observacoes")
    private String observacoes;

//    @Min(value = 50, message = "A descrição deve ter pelo menos 50 caractesres.")
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "condicao", nullable = false)
    private EnumCondicao condicao;

    @Enumerated(EnumType.STRING)
    @Column(name = "finalidade", nullable = false)
    private EnumFinalidade finalidade;

    @Column(name = "area_total")
    private double areaTotal;

    @Column(name = "area_util")
    private double areaUtil;

    @ManyToOne
    @JoinColumn(name="endereco_id", nullable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name="usuario_cpf", nullable = false)
    private Anunciante anunciante;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumLogico getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumLogico situacao) {
        this.situacao = situacao;
    }

    public Timestamp getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(Timestamp dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getCondominio() {
        return condominio;
    }

    public void setCondominio(double condominio) {
        this.condominio = condominio;
    }

    public double getIptu() {
        return iptu;
    }

    public void setIptu(double iptu) {
        this.iptu = iptu;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EnumCondicao getCondicao() {
        return condicao;
    }

    public void setCondicao(EnumCondicao condicao) {
        this.condicao = condicao;
    }

    public EnumFinalidade getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(EnumFinalidade finalidade) {
        this.finalidade = finalidade;
    }

    public double getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public double getAreaUtil() {
        return areaUtil;
    }

    public void setAreaUtil(double areaUtil) {
        this.areaUtil = areaUtil;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Anunciante getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }
}
