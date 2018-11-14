package org.jacared.housepin.services.anuncio;

import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.utils.Busca;
import org.jacared.housepin.utils.EnumFinalidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AnuncioService {
    Optional<Anuncio> buscarAnuncioPorId(int id);
    String[][] buscarAnunciosPorPesquisa(String search);
    List<Anuncio> buscarAnunciosDoAnunciantePorFinalidade(String emailAnunciante, EnumFinalidade finalidade);
    Page<Anuncio> buscarAnunciosPorPesquisaPaginado(String search, Pageable pageable);
    List<Anuncio> buscarTodos();
    Page<Anuncio> buscarTodosOrdenadoPorDataDeInsercao(Pageable pageable);
    void adicionar(Anuncio anuncio);
    void atualizar(Anuncio anuncio);
    void deletar(Anuncio anuncio);
}
