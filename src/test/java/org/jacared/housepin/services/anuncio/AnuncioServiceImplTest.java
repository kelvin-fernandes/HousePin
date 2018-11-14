package org.jacared.housepin.services.anuncio;

import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.models.Endereco;
import org.jacared.housepin.repositories.AnuncioRepository;
import org.jacared.housepin.utils.Busca;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AnuncioServiceImplTest {

    @Autowired
    public TestEntityManager testEntityManager;

    @Autowired
    public AnuncioRepository anuncioRepository;

    @Before
    public void setUp() throws Exception {
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Afonso Pena");
        endereco.setBairro("Centro");
        endereco.setCidade("Campo Grande");

        Anuncio anuncio = new Anuncio();
        anuncio.setEndereco(endereco);

        testEntityManager.persist(anuncio);
    }

    @Test
    public void deveDevolverApenasUmRegistroQuandoReceb() { //deveDevolverMaiorLanceQuandoRecebeUmLance
//        List<String> centro = anuncioRepository.findAllByEndereco("centro");


    }
}