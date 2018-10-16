package org.jacared.housepin.controllers;

import org.jacared.housepin.models.Anuncio;
import org.jacared.housepin.models.Endereco;
import org.jacared.housepin.services.anuncio.AnuncioService;
import org.jacared.housepin.utils.Busca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    @Autowired
    private AnuncioService anuncioService;

    @GetMapping(value = {"/", "/home"})
    public ModelAndView home( @PageableDefault(value=12) Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView();
        Page<Anuncio> anuncios = anuncioService.buscarTodosOrdenadoPorDataDeInsercao(pageable);
        modelAndView.addObject("anuncios", anuncios);
        modelAndView.setViewName("home");

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        System.out.println(currentPrincipalName);

        if (anuncios.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, anuncios.getTotalPages() - 1).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }

        return modelAndView;
    }

    @RequestMapping(value = {"/pesquisa"}, method = RequestMethod.GET)
    public ModelAndView pesquisa(@RequestParam("search") String search, @PageableDefault(value=12) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView();
        Page<Anuncio> anuncios = anuncioService.buscarAnunciosPorPesquisaPaginado(search, pageable);
        modelAndView.addObject("anuncios", anuncios);
        modelAndView.setViewName("home");

        if (anuncios.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(0, anuncios.getTotalPages() - 1).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/pesquisa-ac", method = RequestMethod.GET)
    public @ResponseBody String[][] pesquisa(@RequestParam("search") String search) {
        String[][] strings = anuncioService.buscarAnunciosPorPesquisa(search);
        return strings;
    }
}
