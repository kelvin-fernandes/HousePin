package org.jacared.housepin.services.usuario;

import org.jacared.housepin.models.Anunciante;

public interface AnuncianteService {
    Anunciante buscarAnunciantePorEmail(String email);
    void salvarAnunciante(Anunciante user);
}