package org.jacared.housepin.repositories;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByEmail(String email);
}