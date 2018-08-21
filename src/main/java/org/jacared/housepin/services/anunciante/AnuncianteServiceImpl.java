package org.jacared.housepin.services.anunciante;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.repositories.AnuncianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("anuncianteService")
public class AnuncianteServiceImpl implements AnuncianteService {
    @Qualifier("anuncianteRepository")
    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Anunciante buscarAnunciantePorEmail(String email) {
        return anuncianteRepository.findByEmail(email);
    }

    @Override
    public Optional<Anunciante> buscarAnunciantePorCpf(String cpf) {
        return anuncianteRepository.findById(cpf);
    }

    @Override
    public List<Anunciante> buscarTodos() {
        return anuncianteRepository.findAll();
    }

    @Override
    public void adicionar(Anunciante anunciante) {
        anunciante.setSenha(bCryptPasswordEncoder.encode(anunciante.getSenha()));
        anuncianteRepository.save(anunciante);
    }

    @Override
    public void atualizar(Anunciante anunciante) {

    }

    @Override
    public void deletar(Anunciante anunciante) {
        anuncianteRepository.delete(anunciante);
    }
}