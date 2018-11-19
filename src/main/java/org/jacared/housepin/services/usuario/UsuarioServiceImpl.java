package org.jacared.housepin.services.usuario;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.models.Usuario;
import org.jacared.housepin.repositories.AnuncioRepository;
import org.jacared.housepin.repositories.RoleRepository;
import org.jacared.housepin.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    @Qualifier("usuarioRepository")
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Qualifier("anuncioRepository")
    @Autowired
    private AnuncioRepository anuncioRepository;

    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario buscarUsuarioPorCpf(String cpf) {
        return usuarioRepository.findById(cpf).isPresent() ? usuarioRepository.findById(cpf).get() : null;
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public void adicionar(Usuario usuario) {
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        usuario.setSituacao(1);
        usuario.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByRole("USER"))));
        usuarioRepository.saveAndFlush(usuario);
    }

    @Override
    public void atualizar(Usuario usuario) {

    }

    @Override
    public void deletar(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public void adicionarAnuncioFavorito(int anuncio_id, Usuario usuario) {
        //usuario.setFavoritos(new HashSet<>(Collections.singletonList(anuncioRepository.findById(anuncio_id).get())));
        Set favoritos = usuario.getFavoritos();
        if (favoritos != null && favoritos.size() > 0) {
            favoritos.add(anuncioRepository.findById(anuncio_id).get());
            usuario.setFavoritos(favoritos);
        } else {
            usuario.setFavoritos(new HashSet<>(Collections.singletonList(anuncioRepository.findById(anuncio_id).get())));
        }
        usuarioRepository.saveAndFlush(usuario);
    }
}