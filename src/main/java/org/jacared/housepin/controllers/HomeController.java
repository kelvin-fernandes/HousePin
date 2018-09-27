package org.jacared.housepin.controllers;

import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.services.anuncio.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AnuncioService anuncioService;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        ArrayList anuncios = (ArrayList) anuncioService.buscarTodosOrdenadoPorDataDeInsercao();
        modelAndView.addObject("anuncios", anuncios);
        modelAndView.setViewName("home");

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        System.out.println(currentPrincipalName);

        return modelAndView;
    }

    @RequestMapping(value = {"/pesquisa"}, method = RequestMethod.GET)
    public ModelAndView pesquisa(@RequestParam("search") String search) {
        ModelAndView modelAndView = new ModelAndView();
        List<Anuncio> anuncios = anuncioService.buscarAnunciosPorPesquisa(search);
        modelAndView.addObject("anuncios", anuncios);
        modelAndView.setViewName("home");

        return modelAndView;
    }
}
