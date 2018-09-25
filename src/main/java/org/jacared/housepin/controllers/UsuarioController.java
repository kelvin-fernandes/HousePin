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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value="/usuario/cadastro", method = RequestMethod.GET)
    public ModelAndView cadastroAnunciante(@RequestParam("camponome") String nome, @RequestParam("campoemail") String email){
        ModelAndView modelAndView = new ModelAndView();
        Anunciante anunciante = new Anunciante();
        anunciante.setNome(nome);
        anunciante.setEmail(email);
        modelAndView.addObject("anunciante", anunciante);
        modelAndView.setViewName("usuario/cadastro");
        return modelAndView;
    }

    @RequestMapping(value = "/usuario/cadastro", method = RequestMethod.POST)
    public ModelAndView cadastroAnunciante(@ModelAttribute Anunciante anunciante) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/usuario/cadastro");
        Anunciante anuncianteExists = (Anunciante) usuarioService.buscarUsuarioPorEmail(anunciante.getEmail());
        if (anuncianteExists != null) {
            modelAndView.addObject("errorMessage", "Já existe um usuário registrado com o email fornecido.");
            modelAndView.addObject("anunciante", anunciante);
            return modelAndView;
        }
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("usuario/cadastro");
//        } else {
            usuarioService.adicionar(anunciante);
            modelAndView.addObject("successMessage", "Anunciante foi registrado com sucesso");
            modelAndView.addObject("anunciante", new Anunciante());
//        }
        return modelAndView;
    }
}
