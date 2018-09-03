package org.jacared.housepin.controllers;

import org.jacared.housepin.services.anuncio.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    private AnuncioService anuncioService;

    @GetMapping("/")
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
}
