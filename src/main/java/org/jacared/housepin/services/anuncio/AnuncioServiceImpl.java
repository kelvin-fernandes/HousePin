package org.jacared.housepin.services.anuncio;

import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.repositories.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("anuncioService")
public class AnuncioServiceImpl implements AnuncioService {

    @Qualifier("anuncioRepository")
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
    public List<Anuncio> buscarTodosOrdenadoPorDataDeInsercao() {
        return anuncioRepository.findAllOrderByDescDataInsercao();
    }

    @Override
    public void adicionar(Anuncio anuncio) {
        anuncioRepository.saveAndFlush(anuncio);
    }

    @Override
    public void atualizar(Anuncio anuncio) {
        //TODO atualizar anuncio
    }

    @Override
    public void deletar(Anuncio anuncio) {
        anuncioRepository.delete(anuncio);
    }
}
