package org.jacared.housepin.controllers;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.models.Usuario;
import org.jacared.housepin.services.anunciante.AnuncianteService;
import org.jacared.housepin.services.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AnuncianteService anuncianteService;

    @RequestMapping(value = "/usuario/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastroAnunciante(@RequestParam("camponome") String nome, @RequestParam("campoemail") String email) {
        ModelAndView modelAndView = new ModelAndView();
        Anunciante anunciante = new Anunciante();
        anunciante.setNome(nome);
        anunciante.setEmail(email);

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);

        modelAndView.addObject("anunciante", anunciante);
        modelAndView.addObject("usuario", usuario);
        modelAndView.setViewName("/usuario/cadastro");
        return modelAndView;
    }

    @RequestMapping(value = "/cadastro/usuario", method = RequestMethod.POST)
    public ModelAndView cadastroUsuario(@ModelAttribute Usuario usuario) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/usuario/cadastro");
        Usuario usuarioExists = usuarioService.buscarUsuarioPorCpf(usuario.getCpf());
        if (usuarioExists != null) {
            modelAndView.addObject("errorMessage", "Já existe um usuário registrado com o CPF fornecido.");
            modelAndView.addObject("usuario", usuario);
            return modelAndView;
        }

        usuarioExists = usuarioService.buscarUsuarioPorEmail(usuario.getEmail());
        if(usuarioExists != null) {
            modelAndView.addObject("errorMessage", "Já existe um usuário registrado com o email fornecido.");
            modelAndView.addObject("usuario", usuario);
            return modelAndView;
        }

        usuarioService.adicionar(usuario);
        modelAndView.addObject("successMessage", "Usuário foi registrado com sucesso");
        modelAndView.addObject("usuario", new Usuario());
        modelAndView.addObject("anunciante", new Anunciante());

        return modelAndView;
    }

    @RequestMapping(value = "/cadastro/anunciante", method = RequestMethod.POST)
    public ModelAndView cadastroAnunciante(@ModelAttribute Anunciante anunciante) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/usuario/cadastro");
        Anunciante anuncianteExists = anuncianteService.buscarAnunciantePorCpf(anunciante.getCpf());
        if (anuncianteExists != null) {
            modelAndView.addObject("errorMessage", "Já existe um usuário registrado com o CPF fornecido.");
            modelAndView.addObject("anunciante", anunciante);
            return modelAndView;
        }

        anuncianteExists = anuncianteService.buscarAnunciantePorEmail(anunciante.getEmail());
        if(anuncianteExists != null) {
            modelAndView.addObject("errorMessage", "Já existe um usuário registrado com o email fornecido.");
            modelAndView.addObject("anunciante", anunciante);
            return modelAndView;
        }

        anuncianteExists = anuncianteService.buscarAnunciantePorCreci(anunciante.getCreci());
        if(anuncianteExists != null) {
            modelAndView.addObject("errorMessage", "Já existe um usuário registrado com o CRECI fornecido.");
            modelAndView.addObject("anunciante", anunciante);
            return modelAndView;
        }

        anuncianteService.adicionar(anunciante);
        modelAndView.addObject("successMessage", "Anunciante foi registrado com sucesso");
        modelAndView.addObject("usuario", new Usuario());
        modelAndView.addObject("anunciante", new Anunciante());

        return modelAndView;
    }
}
