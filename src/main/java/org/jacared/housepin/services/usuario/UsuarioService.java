package org.jacared.housepin.services.usuario;

import org.jacared.housepin.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario buscarUsuarioPorEmail(String email);
    Optional<Usuario> buscarUsuarioPorCpf(String cpf);
    List<Usuario> buscarTodos();
    void adicionar(Usuario usuario);
    void atualizar(Usuario usuario);
    void deletar(Usuario usuario);
}