package org.jacared.housepin.repositories;

import org.jacared.housepin.models.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("anuncioRepository")
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
    @Query("SELECT a FROM Anuncio a ORDER BY a.dataInsercao desc")
    List<Anuncio> findAllOrderByDescDataInsercao();
}
