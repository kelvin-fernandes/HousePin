package org.jacared.housepin.repositories;

import org.jacared.housepin.models.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("anuncioRepository")
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
    @Query(value =  "SELECT * FROM ANUNCIO a " +
                    "JOIN ENDERECO e ON e.ENDERECO_ID = a.ENDERECO_ID " +
                    "WHERE e.LOGRADOURO ILIKE %?1% OR " +
                    "e.BAIRRO ILIKE %?1% OR " +
                    "e.CIDADE ILIKE %?1%", nativeQuery = true)
    List<Anuncio> findAnunciosBySearch(String search);

    @Query("SELECT a FROM Anuncio a ORDER BY a.dataInsercao desc")
    List<Anuncio> findAllOrderByDescDataInsercao();
}
