package org.jacared.housepin.services.anuncio;

import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AnuncioServiceImpl implements AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Override
    public Optional<Anuncio> buscarAnuncioPorId(int id) {
        return anuncioRepository.findById(id);
    }

    @Override
    public List<Anuncio> buscarTodos() {
        return anuncioRepository.findAll();
    }

    @Override
    public void adicionar(Anuncio anuncio) {
        anuncioRepository.save(anuncio);
    }

    @Override
    public void atualizar(Anuncio anuncio) {

    }

    @Override
    public void deletar(Anuncio anuncio) {
        anuncioRepository.delete(anuncio);
    }
}
