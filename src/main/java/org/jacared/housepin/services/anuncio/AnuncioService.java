package org.jacared.housepin.services.anuncio;

import org.jacared.housepin.models.Anuncio;

import java.util.List;
import java.util.Optional;

public interface AnuncioService {
    Optional<Anuncio> buscarAnuncioPorId(int id);
    List<Anuncio> buscarTodos();
    List<Anuncio> buscarTodosOrdenadoPorDataDeInsercao();
    void adicionar(Anuncio anuncio);
    void atualizar(Anuncio anuncio);
    void deletar(Anuncio anuncio);
}
