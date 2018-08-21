package org.jacared.housepin.config;

import org.jacared.housepin.models.Privilege;
import org.jacared.housepin.models.Role;
import org.jacared.housepin.models.Usuario;
import org.jacared.housepin.repositories.PrivilegeRepository;
import org.jacared.housepin.repositories.RoleRepository;
import org.jacared.housepin.repositories.UsuarioRepository;
import org.jacared.housepin.utils.EnumLogico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Qualifier("usuarioRepository")
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRepository;

    @Qualifier("privilegeRepository")
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
//        Privilege readPrivilege
//                = createPrivilegeIfNotFound("READ_PRIVILEGE");
//        Privilege writePrivilege
//                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

//        List<Privilege> adminPrivileges = Arrays.asList(
//                readPrivilege, writePrivilege);
        createRoleIfNotFound("USER");

        Role adminRole = roleRepository.findByName("USER");
        Usuario usuario = new Usuario();
        usuario.setCpf("059.732.891-92");
        usuario.setNome("ADMIN");
        usuario.setEmail("admin@admin.com");
        usuario.setSenha(passwordEncoder.encode("admin"));
        usuario.setTelefone("5567992711765");
        usuario.setRoles(Arrays.asList(adminRole));
        usuario.setSituacao(true);
        usuarioRepository.save(usuario);

        alreadySetup = true;
    }

//    @Transactional
//    public Privilege createPrivilegeIfNotFound(String name) {
//        Privilege privilege = privilegeRepository.findByName(name);
//        if (privilege == null) {
//            privilege = new Privilege(name);
//            privilegeRepository.save(privilege);
//        }
//        return privilege;
//    }

    @Transactional
    public Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}
