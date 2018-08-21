package org.jacared.housepin.models;

import org.jacared.housepin.utils.EnumCategoriaTipoImovel;

import javax.persistence.*;

@Entity
@Table(name = "tipo_imovel")
public class TipoImovel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tipo_imovel_id")
    private int id;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "descricao", nullable = false, length = 15)
    private EnumCategoriaTipoImovel descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnumCategoriaTipoImovel getDescricao() {
        return descricao;
    }

    public void setDescricao(EnumCategoriaTipoImovel descricao) {
        this.descricao = descricao;
    }
}
