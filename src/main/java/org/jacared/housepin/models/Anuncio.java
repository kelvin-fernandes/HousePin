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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "anuncio_id")
    private int id;

    @NotNull(message = "Escolha a situação atual do anúncio.")
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    private EnumLogico situacao;

    @Column(name = "data_insercao", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP()")
    private Timestamp dataInsercao;

    @Column(name = "data_expiracao")
    private Date dataExpiracao;

    @NotEmpty(message = "Insira um valor para o anúncio.")
    @NotBlank(message = "Insira um valor válido.")
    @Column(name = "valor", nullable = false)
    private double valor;

    @Column(name = "condominio")
    private double condominio;

    @Column(name = "iptu")
    private double iptu;

    @Column(name = "observacoes")
    private String observacoes;

    @Min(value = 50, message = "A descrição deve ter pelo menos 50 caractesres.")
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

    @NotEmpty(message = "Insira o número do ímóvel.")
    @NotBlank(message = "Insira um número válido.")
    @Column(name = "endereco_numero", nullable = false)
    private int numeroEndereco;

    @ManyToOne
    @JoinColumn(name="cep", nullable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name="usuario_cpf", nullable = false)
    private Anunciante anunciante;

    @ManyToOne
    @JoinColumn(name="tipo_imovel_id", nullable = false)
    private TipoImovel tipoImovel;

    @ManyToMany(mappedBy = "anuncios")
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "anuncio")
    Set<Imagem> imagens = new HashSet<>();
}
