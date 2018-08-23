package org.jacared.housepin.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="usuario_tipo", discriminatorType = DiscriminatorType.STRING)
public class Usuario {
    @Id
    @Column(name = "usuario_cpf", length = 14)
    @NotEmpty(message = "Insira o seu CPF.")
    @NotBlank(message = "Insira um CPF válido.")
    private String cpf;

    @Email(message = "Insira um email válido")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "Escolha a sua senha.")
//    @Min(value = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    @Column(name = "senha", nullable = false)
    @org.springframework.data.annotation.Transient
    private String senha;

    @NotEmpty(message = "Insira o seu nome.")
    @NotBlank(message = "Insira um nome válido.")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotEmpty(message = "Insira o seu telefone.")
    @NotBlank(message = "Insira um telefone válido.")
    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "situacao")
    private boolean situacao;

    @Column(name = "endereco_numero")
    private int numeroEndereco;

    @ManyToOne
    @JoinColumn(name="cep")
    private Endereco endereco;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
