package org.jacared.housepin.services.anunciante;

import org.jacared.housepin.models.Anunciante;

import java.util.List;

public interface AnuncianteService {
    Anunciante buscarAnunciantePorEmail(String email);
    Anunciante buscarAnunciantePorCpf(String cpf) throws RuntimeException;
    Anunciante buscarAnunciantePorCreci(String creci);
    List<Anunciante> buscarTodos();
    void adicionar(Anunciante Anunciante);
    void atualizar(Anunciante Anunciante);
    void deletar(Anunciante Anunciante);
}