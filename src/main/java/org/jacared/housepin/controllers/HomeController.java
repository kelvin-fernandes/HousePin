package org.jacared.housepin.controllers;

import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.services.anuncio.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    @Autowired
    private AnuncioService anuncioService;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView home( @PageableDefault(value=1, page=0) Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView();
        Page<Anuncio> anuncios = anuncioService.buscarTodosOrdenadoPorDataDeInsercao(pageable);
        modelAndView.addObject("anuncios", anuncios);
        modelAndView.setViewName("home");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);

        if (anuncios.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, anuncios.getTotalPages() - 1).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }

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
