package org.jacared.housepin.services.anunciante;

import org.jacared.housepin.models.Anunciante;

import java.util.List;
import java.util.Optional;

public interface AnuncianteService {
    Anunciante buscarAnunciantePorEmail(String email);
    Optional<Anunciante> buscarAnunciantePorCpf(String cpf);
    List<Anunciante> buscarTodos();
    void adicionar(Anunciante anunciante);
    void atualizar(Anunciante anunciante);
    void deletar(Anunciante anunciante);
}