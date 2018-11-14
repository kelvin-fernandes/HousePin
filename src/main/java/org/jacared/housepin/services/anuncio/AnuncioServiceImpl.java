package org.jacared.housepin.services.anuncio;

import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.repositories.AnuncioRepository;
import org.jacared.housepin.utils.Busca;
import org.jacared.housepin.utils.EnumFinalidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

//    @Override
//    public List<Anuncio> buscarAnunciosPorPesquisa(String search) {
//        return anuncioRepository.findAnunciosBySearch(search);
//    }

    @Override
    public String[][] buscarAnunciosPorPesquisa(String search) {
        return anuncioRepository.findAllByEndereco(search);
    }

    @Override
    public List<Anuncio> buscarAnunciosDoAnunciantePorFinalidade(String emailAnunciante, EnumFinalidade finalidade) {
        return anuncioRepository.findAllByEmailAnuncianteAndFinalidade(emailAnunciante, finalidade);
    }

    @Override
    public Page<Anuncio> buscarAnunciosPorPesquisaPaginado(String search, Pageable pageable) {
        return anuncioRepository.findAnunciosBySearch(search, pageable);
    }

    @Override
    public List<Anuncio> buscarTodos() {
        return anuncioRepository.findAll();
    }

    @Override
    public Page<Anuncio> buscarTodosOrdenadoPorDataDeInsercao(Pageable pageable) {
        return anuncioRepository.findAllOrderByDescDataInsercao(pageable);
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
