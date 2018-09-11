package org.jacared.housepin.controllers;

import org.jacared.housepin.models.Anunciante;
import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.models.Usuario;
import org.jacared.housepin.services.anuncio.AnuncioService;
import org.jacared.housepin.services.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = {"/anuncio/cadastro"}, method = RequestMethod.GET)
    public ModelAndView cadastro() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("anuncio",new Anuncio());
        modelAndView.setViewName("anuncio/cadastro");
        return modelAndView;
    }

//    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
//    public ModelAndView home(){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Usuario usuario = usuarioService.buscarUsuarioPorEmail(auth.getName());
//        modelAndView.addObject("userName", "Bem vindo" + usuario.getNome() +  " (" + usuario.getEmail() + ")");
//        modelAndView.setViewName("admin/home");
//        return modelAndView;
//    }

    @RequestMapping(value = {"/anuncio/cadastro"}, method = RequestMethod.POST)
    public String cadastro(@ModelAttribute Anuncio anuncio) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        Usuario anunciante = usuarioService.buscarUsuarioPorCpf("059.732.891-92").get();
        anuncio.setAnunciante((Anunciante) anunciante);
        anuncioService.adicionar(anuncio);
        return "redirect:/";
    }

//    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
//    public ModelAndView home1(){
//        ModelAndView modelAndView = new ModelAndView();
////        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////        Anunciante usuario = (Anunciante) usuarioService.buscarUsuarioPorEmail(auth.getName());
////        modelAndView.addObject("anuncianteName", "Welcome " + usuario.getNome() + " (" + usuario.getEmail() + ")");
//        modelAndView.addObject("adminMessage","Conteúdo disponível apenas para usuário com papel de usuario");
//        modelAndView.setViewName("admin/home");
//        return modelAndView;
//    }
}
