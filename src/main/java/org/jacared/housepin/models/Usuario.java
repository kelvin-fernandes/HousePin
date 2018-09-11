package org.jacared.housepin.models;

import org.jacared.housepin.utils.EnumLogico;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="usuario_tipo", discriminatorType = DiscriminatorType.STRING)
public class Usuario implements Serializable {
    @Id
    @Column(name = "usuario_cpf", length = 14)
//    @NotEmpty(message = "Insira o seu CPF.")
//    @NotBlank(message = "Insira um CPF válido.")
    private String cpf;

//    @Email(message = "Insira um email válido")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

//    @NotEmpty(message = "Escolha a sua senha.")
//    @Min(value = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    @Column(name = "senha", nullable = false)
    private String senha;

//    @NotEmpty(message = "Insira o seu nome.")
//    @NotBlank(message = "Insira um nome válido.")
    @Column(name = "nome", nullable = false)
    private String nome;

//    @NotEmpty(message = "Insira o seu telefone.")
//    @NotBlank(message = "Insira um telefone válido.")
    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "situacao")
    private Integer situacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="endereco_id")
    private Endereco endereco;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario_cpf"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

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

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
