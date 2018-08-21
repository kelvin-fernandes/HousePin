package org.jacared.housepin.repositories;

import org.jacared.housepin.models.Anunciante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("anuncianteRepository")
public interface AnuncianteRepository extends JpaRepository<Anunciante, String> {
    Anunciante findByEmail(String email);
}