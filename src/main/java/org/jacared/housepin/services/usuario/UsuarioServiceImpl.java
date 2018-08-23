package org.jacared.housepin.services.usuario;

import org.jacared.housepin.models.Usuario;
import org.jacared.housepin.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    @Qualifier("usuarioRepository")
    @Autowired
    private UsuarioRepository usuarioRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorCpf(String cpf) {
        return usuarioRepository.findById(cpf);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public void adicionar(Usuario usuario) {
//        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
    }

    @Override
    public void atualizar(Usuario usuario) {

    }

    @Override
    public void deletar(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}