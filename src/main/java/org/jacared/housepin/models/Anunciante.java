package org.jacared.housepin.models;

import org.jacared.housepin.utils.EnumLogico;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "anunciante")
public class Anunciante {
    @Id
    @Column(name = "anunciante_cpf", length = 14)
    @NotEmpty(message = "Insira o seu CPF.")
    @NotBlank(message = "Insira um CPF válido.")
    private String cpf;

    @NotEmpty(message = "Insira o seu CRECI.")
    @NotBlank(message = "Insira um CRECI válido.")
    @Column(name = "creci", unique = true, nullable = false, length = 20)
    private String creci;

    @Email(message = "Insira um email válido")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "Escolha a sua senha.")
    @Min(value = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    @Column(name = "senha", nullable = false)
    @Transient
    private String senha;

    @NotEmpty(message = "Insira o seu nome.")
    @NotBlank(message = "Insira um nome válido.")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotEmpty(message = "Insira o seu telefone comercial.")
    @NotBlank(message = "Insira um telefone válido.")
    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    private EnumLogico situacao;

    @Column(name = "cnpj", length = 18)
    private String cnpj;

    @Column(name = "razao_social")
    private String razaoSocial;

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

    public String getCreci() {
        return creci;
    }

    public void setCreci(String creci) {
        this.creci = creci;
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
