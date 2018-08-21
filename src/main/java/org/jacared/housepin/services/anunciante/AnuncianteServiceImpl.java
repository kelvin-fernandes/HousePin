package org.jacared.housepin.services.anunciante;

import java.util.Arrays;
import java.util.HashSet;

import org.jacared.housepin.models.Role;
import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.repositories.RoleRepository;
import org.jacared.housepin.repositories.AnuncianteRepository;
import org.jacared.housepin.services.usuario.AnuncianteService;
import org.jacared.housepin.utils.EnumLogico;
import org.jacared.housepin.utils.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("anuncianteService")
public class AnuncianteServiceImpl implements AnuncianteService {


    @Qualifier("anuncianteRepository")
    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Anunciante buscarAnunciantePorEmail(String email) {
        return anuncianteRepository.findByEmail(email);
    }

    @Override
    public void salvarAnunciante(Anunciante anunciante) {
        anunciante.setSenha(bCryptPasswordEncoder.encode(anunciante.getSenha()));
        anuncianteRepository.save(anunciante);
    }

}