package org.jacared.housepin.services.anuncio;

import org.jacared.housepin.models.Anuncio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AnuncioService {
    Optional<Anuncio> buscarAnuncioPorId(int id);
    List<Anuncio> buscarAnunciosPorPesquisa(String search);
    List<Anuncio> buscarTodos();
    Page<Anuncio> buscarTodosOrdenadoPorDataDeInsercao(Pageable pageable);
    void adicionar(Anuncio anuncio);
    void atualizar(Anuncio anuncio);
    void deletar(Anuncio anuncio);
}
