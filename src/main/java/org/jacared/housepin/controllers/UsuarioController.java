package org.jacared.housepin.controllers;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.services.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value="/anunciante/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastroAnunciante(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("anunciante", new Anunciante());
        modelAndView.setViewName("/anunciante/cadastro");
        return modelAndView;
    }

    @RequestMapping(value = "/anunciante/cadastro", method = RequestMethod.POST)
    public ModelAndView cadastroAnunciante(@Valid Anunciante anunciante, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Anunciante anuncianteExists = (Anunciante) usuarioService.buscarUsuarioPorEmail(anunciante.getEmail());
        if (anuncianteExists != null) {
            bindingResult
                    .rejectValue("email", "error.usuario",
                            "Já existe um usuário registrado com o email fornecido.");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/anunciante/cadastro");
        } else {
            usuarioService.adicionar(anunciante);
            modelAndView.addObject("successMessage", "Anunciante foi registrado com sucesso");
            modelAndView.addObject("anunciante", new Anunciante());
            modelAndView.setViewName("/anunciante/cadastro");
        }
        return modelAndView;
    }
}
