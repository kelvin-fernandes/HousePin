package org.jacared.housepin.controllers;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.services.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value="/usuario/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastroAnunciante(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("anunciante", new Anunciante());
        modelAndView.setViewName("usuario/cadastro");
        return modelAndView;
    }

    @RequestMapping(value = "/usuario/cadastro", method = RequestMethod.POST)
    public ModelAndView cadastroAnunciante(@ModelAttribute Anunciante anunciante) {
        ModelAndView modelAndView = new ModelAndView();
        Anunciante anuncianteExists = (Anunciante) usuarioService.buscarUsuarioPorEmail(anunciante.getEmail());
        if (anuncianteExists != null) {
            modelAndView.addObject("errorMessage", "Já existe um usuário registrado com o email fornecido.");
        }
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("usuario/cadastro");
//        } else {
            usuarioService.adicionar(anunciante);
            modelAndView.addObject("successMessage", "Anunciante foi registrado com sucesso");
            modelAndView.addObject("anunciante", new Anunciante());
            modelAndView.setViewName("/usuario/cadastro");
//        }
        return modelAndView;
    }
}
