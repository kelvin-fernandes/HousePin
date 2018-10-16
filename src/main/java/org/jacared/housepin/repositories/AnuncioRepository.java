package org.jacared.housepin.repositories;

import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.utils.Busca;
import org.jacared.housepin.utils.EnumFinalidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("anuncioRepository")
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
    @Query(value =  "SELECT * FROM ANUNCIO a " +
                    "JOIN ENDERECO e ON e.ENDERECO_ID = a.ENDERECO_ID " +
                    "WHERE e.LOGRADOURO ILIKE %?1% OR " +
                    "e.BAIRRO ILIKE %?1% OR " +
                    "e.CIDADE ILIKE %?1%", nativeQuery = true)
    Page<Anuncio> findAnunciosBySearch(String search, Pageable pageable);

    @Query("SELECT a FROM Anuncio a ORDER BY a.dataInsercao desc")
    Page<Anuncio> findAllOrderByDescDataInsercao(Pageable pageable);

    @Query(value =  "SELECT LOGRADOURO AS valor, CONCAT('RUA', '') as tipo FROM ENDERECO e WHERE LOGRADOURO ILIKE %?1% UNION " +
                    "SELECT BAIRRO AS valor, CONCAT('BAIRRO', '') as tipo FROM ENDERECO e WHERE BAIRRO ILIKE %?1% UNION " +
                    "SELECT CIDADE AS valor, CONCAT('CIDADE', '') as tipo FROM ENDERECO e WHERE CIDADE ILIKE %?1%", nativeQuery = true)
    String[][] findAllByEndereco(String search);

    @Query("SELECT a FROM Anuncio a WHERE a.anunciante.email = ?1 AND a.finalidade = ?2")
    List<Anuncio> findAllByEmailAnuncianteAndFinalidade(String emailAnunciante, EnumFinalidade finalidade);
}
