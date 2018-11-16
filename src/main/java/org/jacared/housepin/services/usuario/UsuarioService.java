package org.jacared.housepin.services.usuario;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.models.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario buscarUsuarioPorEmail(String email);
    Usuario buscarUsuarioPorCpf(String cpf);
    List<Usuario> buscarTodos();
    void adicionar(Usuario usuario);
    void atualizar(Usuario usuario);
    void deletar(Usuario usuario);
    void adicionarAnuncioFavorito(int anuncio_id, Usuario usuario);
}