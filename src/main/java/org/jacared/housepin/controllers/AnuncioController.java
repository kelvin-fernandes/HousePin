package org.jacared.housepin.controllers;

import org.jacared.housepin.services.anuncio.AnuncioService;
import org.jacared.housepin.services.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//        modelAndView.addObject("username", name);
        ArrayList anuncios = (ArrayList) anuncioService.buscarTodosOrdenadoPorDataDeInsercao();
        modelAndView.addObject("anuncios", anuncios);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home1(){
        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Anunciante usuario = (Anunciante) usuarioService.buscarUsuarioPorEmail(auth.getName());
//        modelAndView.addObject("anuncianteName", "Welcome " + usuario.getNome() + " (" + usuario.getEmail() + ")");
        modelAndView.addObject("adminMessage","Conteúdo disponível apenas para usuário com papel de usuario");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
}
