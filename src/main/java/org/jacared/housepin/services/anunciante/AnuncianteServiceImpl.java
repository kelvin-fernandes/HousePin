package org.jacared.housepin.services.anunciante;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.repositories.AnuncianteRepository;
import org.jacared.housepin.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service("anuncianteService")
public class AnuncianteServiceImpl implements AnuncianteService {

    @Qualifier("anuncianteRepository")
    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Anunciante buscarAnunciantePorEmail(String email) {
        return anuncianteRepository.findByEmail(email);
    }

    @Override
    public Anunciante buscarAnunciantePorCpf(String cpf) throws RuntimeException{
        Optional<Anunciante> anunciante = anuncianteRepository.findById(cpf);
        return anunciante.orElseThrow(RuntimeException::new);
    }

    @Override
    public Anunciante buscarAnunciantePorCreci(String creci) {
        return anuncianteRepository.findByCreci(creci);
    }

    @Override
    public List<Anunciante> buscarTodos() {
        return anuncianteRepository.findAll();
    }

    @Override
    public void adicionar(Anunciante anunciante) {
        anunciante.setSenha(bCryptPasswordEncoder.encode(anunciante.getSenha()));
        anunciante.setSituacao(1);
        anunciante.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByRole("USER"))));
        anuncianteRepository.saveAndFlush(anunciante);
    }

    @Override
    public void atualizar(Anunciante anunciante) {

    }

    @Override
    public void deletar(Anunciante anunciante) {
        anuncianteRepository.delete(anunciante);
    }
}