package org.jacared.housepin.models;

import org.springframework.data.annotation.Transient;

import javax.persistence.*;

@Entity
@Table(name = "anunciante")
public class Usuario {
    @Id
    @Column(name = "anunciante_cpf")
    private String cpf;

    @Column(name = "creci", unique = true)
    private String creci;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "senha")
    @Transient
    private String senha;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "endereco_numero")
    private int numeroEndereco;

    @ManyToOne
    @JoinColumn(name="endereco_id", nullable=false)
    private Endereco endereco;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "role", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles;

}
